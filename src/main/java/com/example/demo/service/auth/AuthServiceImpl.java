package com.example.demo.service.auth;

import com.example.demo.base.BaseService;
import com.example.demo.base.StructureRS;
import com.example.demo.constant.MessageConstant;
import com.example.demo.db.entity.UserEntity;
import com.example.demo.db.reposiroty.AuthRepository;
import com.example.demo.exception.httpstatus.BadRequestException;
import com.example.demo.mail.Mail;
import com.example.demo.mail.MailService;
import com.example.demo.model.request.auth.*;
import com.example.demo.model.request.mail.VerifyRQ;
import com.example.demo.security.UserPrincipal;
import com.example.demo.service.user.UserService;
import com.example.demo.utils.TokenUtils;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.management.relation.RoleNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Sattya
 * create at 26/1/24 2:08 AM
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl extends BaseService implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final MailService mailService;
    private final AuthRepository authRepository;

    @Value("${spring.mail.username}")
    private String adminMail;

    @Value("${app.base-uri}")
    private String appBaseUri;

    @Value("${app.api-version}")
    private String apiVersion;
    @Transactional
    @Override
    public StructureRS login(LoginRQ request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication auth = authenticationManager.authenticate(authenticationToken);

        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();

        if (!userPrincipal.getStatus())
            throw new BadRequestException(MessageConstant.AUTH.ACCOUNT_DEACTIVATE);

        Map<String, Object> respond = new HashMap<>();

        respond.put("user", userPrincipal);
        respond.put("token", tokenUtils.generateToken(userPrincipal));

        return response(respond);
    }
    @Transactional
    @Override
    public StructureRS register(RegisterRQ request) throws RoleNotFoundException, MessagingException {
        userService.createNewUser(request);

        // Generate verification code and token
    //    String verificationCode = RandomUtil.generateCode();
        String verificationToken = UUID.randomUUID().toString();

        // Store verification details in the database
    //    authRepository.updateVerifiedCode(request.getUsername(),verificationCode);
        authRepository.updateIsVerifiedToken(request.getUsername(),verificationToken);

        // Prepare and send verification email
        String verificationLink = appBaseUri + apiVersion +"auth/verify?token=" + verificationToken;

//        // First email verification code 6 digits
//        Mail<String> verificationCdoeMail = createVerificationEmail(request.getEmail(),verificationCode,"auth/verify-mail");
//        log.info("Verification code: {}", verificationCode);
//        mailService.sendMail(verificationCdoeMail);

        // Second email verification link
        Mail<String> verificationLinkMail = createVerificationEmail(request.getEmail(),verificationLink);
        mailService.sendMail(verificationLinkMail);

        return response(HttpStatus.valueOf(201),MessageConstant.AUTH.REGISTER_SUCCESSFULLY, verificationLink);
    }

    @Transactional
    @Override
    public void verifyRQ(VerifyRQ request) {
        UserEntity userEntity = authRepository.findByEmailAndVerifiedCodeAndIsDeletedFalse(request.getEmail(),request.getVerifiedCode())
                .orElseThrow(() -> new BadRequestException(MessageConstant.AUTH.VERIFY_CODE_NOT_FOUND));

        userEntity.setIsVerified(true);
        userEntity.setVerifiedCode(null);
        userEntity.setVerifiedToken(null);
        authRepository.save(userEntity);
    }
    @Transactional
    @Override
    public void verifyUser(String token) {
        // Find the user with the matching token, ensure that it is valid
        UserEntity userEntity = authRepository.findByVerifiedTokenAndIsDeletedFalse(token)
                .orElseThrow(() -> new BadRequestException(MessageConstant.AUTH.VERIFY_TOKEN_NOT_FOUND));

        if (!userEntity.getIsVerified()){
            userEntity.setIsVerified(true);
            userEntity.setVerifiedCode(null);
            userEntity.setVerifiedToken(null);
            userEntity.setStatus(true);
            authRepository.save(userEntity);
        }
    }
    @Transactional
    @Override
    public StructureRS forgotPassword(ForgotPasswordRQ request) throws MessagingException {

       UserEntity userEntity = authRepository.findByEmailAndIsDeletedFalse(request.getEmail()).orElseThrow(
               () -> new ResponseStatusException(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()),MessageConstant.AUTH.EMAIL_NOT_FOUND)
       );

       String resetToken = UUID.randomUUID().toString();
       authRepository.updateIsVerifiedToken(userEntity.getUsername(),resetToken);

       String resetLink = appBaseUri + apiVersion + "auth/reset-password?token="+resetToken;
       Mail<?> resetMail = createPasswordResetEmail(userEntity.getEmail(),resetLink);

       mailService.sendMail(resetMail);
       log.info("reset-mail : {}",resetMail);

        return response(HttpStatus.CREATED,MessageConstant.SUCCESSFULLY,Map.of("message",resetLink,
                "reset-password-token",resetToken));
    }
    @Transactional
    @Override
    public StructureRS resetPassword(ResetPasswordRQ request) {

        String resetToken = authRepository.findByEmailAndIsDeletedFalse(request.getEmail())
                .map(UserEntity::getVerifiedToken)
                .orElseThrow(() -> new BadRequestException(MessageConstant.AUTH.RESET_TOKEN_NOT_FOUND));

        UserEntity userEntity = authRepository.findByVerifiedTokenAndIsDeletedFalseAndStatusTrue(resetToken)
                .orElseThrow(() -> new BadRequestException(MessageConstant.AUTH.RESET_TOKEN_NOT_MATCH));

        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setVerifiedToken(null);
        authRepository.save(userEntity);

        return response(HttpStatus.OK,MessageConstant.AUTH.RESET_PASSWORD_SUCCESSFULLY);
    }

    @Override
    public boolean verifyResetToken(String token) {
        return authRepository.existsByVerifiedTokenAndIsDeletedFalseAndStatusTrue(token);
    }
    @Transactional
    @Override
    public StructureRS changePassword(ChangePasswordRQ request) {

        UserEntity userEntity = authRepository.findByEmailAndIsDeletedFalse(request.getEmail())
                .orElseThrow(() -> new BadRequestException(MessageConstant.AUTH.EMAIL_NOT_FOUND));

        if (!passwordEncoder.matches(request.getOldPassword(),userEntity.getPassword())){
            throw new BadRequestException(MessageConstant.AUTH.PASSWORD_NOT_MATCH);
        }

        userEntity.setPassword(passwordEncoder.encode(request.getNewPassword()));
        authRepository.save(userEntity);
        return response(HttpStatus.OK,MessageConstant.SUCCESSFULLY);
    }

    private Mail<String> createPasswordResetEmail(String recipient, String resetLink) {
        Mail<String> mail = new Mail<>();
        mail.setSubject("Reset Password");
        mail.setSender(recipient);  //destination -> recipient is the email address of the receiver
        mail.setReceiver(adminMail); //source -> adminMail is the email address of the sender
        mail.setTemplate("auth/forgot-password-mail");
        mail.setMetaData(resetLink);
        return mail;
    }

    private Mail<String> createVerificationEmail(String recipient, String metaData) {
        Mail<String> mail = new Mail<>();
        mail.setSubject("Email Verification");
        mail.setSender(recipient);  //destination -> recipient is the email address of the receiver
        mail.setReceiver(adminMail); //source -> adminMail is the email address of the sender
        mail.setTemplate("auth/verify-token-mail");
        mail.setMetaData(metaData);
        return mail;
    }

}
