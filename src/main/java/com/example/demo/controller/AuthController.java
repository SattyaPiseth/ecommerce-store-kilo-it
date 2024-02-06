package com.example.demo.controller;

import com.example.demo.base.BaseController;
import com.example.demo.base.StructureRS;
import com.example.demo.constant.MessageConstant;
import com.example.demo.db.repository.PasswordResetTokenRepository;
import com.example.demo.exception.httpstatus.BadRequestException;
import com.example.demo.model.request.auth.*;
import com.example.demo.model.request.mail.VerifyRQ;
import com.example.demo.service.auth.AuthService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.Map;
/**
 * @author Sattya
 * create at 23/1/24 3:35 PM
 */

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthService authService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    @PostMapping("/login")
    public ResponseEntity<StructureRS> login(@Validated @RequestBody LoginRQ loginRQ) {
        return response(authService.login(loginRQ));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity<StructureRS> register(@Validated @RequestBody RegisterRQ registerRQ) throws MessagingException, RoleNotFoundException {
       StructureRS token = authService.register(registerRQ);
        return response(Map.of("message","Please check email and verify..!",
//                "verify via 6 digits",appBaseUri+"auth/verify?email="+registerRQ.getEmail(),
                "verify via Verify Email Address",token.getData()));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/verify-code")
    public ResponseEntity<StructureRS> verify(@RequestBody @Validated VerifyRQ verifyRQ){
        authService.verifyRQ(verifyRQ);
        return response(Map.of("message",MessageConstant.AUTH.EMAIL_VERIFIED));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/verify")
    public ResponseEntity<StructureRS> verify(@RequestParam("token") String token){
        authService.verifyUser(token);
        return response(Map.of("message",MessageConstant.AUTH.EMAIL_VERIFIED));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/forgot-password")
    public StructureRS forgotPassword(@RequestBody @Validated ForgotPasswordRQ request) throws MessagingException {
        return response(authService.forgotPassword(request)).getBody();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/change-password")
    public StructureRS changePassword(@RequestParam("token") String token,
                                     @RequestBody @Validated ResetPasswordRQ request){
        if (!authService.validatePasswordResetToken(token)){
            passwordResetTokenRepository.deleteByToken(token);
            throw new BadRequestException(MessageConstant.AUTH.TOKEN_EXPIRED);
        }
        return response(authService.changePassword(token,request)).getBody();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/resend-verify-code")
    public StructureRS resendVerifyCode(@RequestParam("email") @Validated String email) throws MessagingException {
        return response(authService.resendVerificationCodeViaEmail(email)).getBody();
    }
}



