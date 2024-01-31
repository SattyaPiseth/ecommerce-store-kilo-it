package com.example.demo.service.user;

import com.example.demo.base.BaseService;
import com.example.demo.base.StructureRS;
import com.example.demo.constant.MessageConstant;
import com.example.demo.db.entity.RoleEntity;
import com.example.demo.db.entity.UserEntity;
import com.example.demo.db.reposiroty.RoleRepository;
import com.example.demo.db.reposiroty.UserRepository;
import com.example.demo.exception.httpstatus.BadRequestException;
import com.example.demo.mapper.UserEntityMapper;
import com.example.demo.model.request.auth.ChangePasswordRQ;
import com.example.demo.model.request.auth.RegisterRQ;
import com.example.demo.model.response.user.UserProfileRS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Sattya
 * create at 1/29/2024 10:19 PM
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl extends BaseService implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public void createNewUser(RegisterRQ registerRQ) throws RoleNotFoundException {
        // TODO: Check if user already exist in database
        if (userRepository.existsByUsernameOrEmailAndIsDeletedFalse(registerRQ.getUsername(), registerRQ.getEmail())){
            throw new ResponseStatusException(HttpStatusCode.valueOf(409), "Username or email already exists.");
        }
      UserEntity userEntity = userEntityMapper.fromRegisterRQ(registerRQ);
      userEntity.setUuid(UUID.randomUUID().toString());
      userEntity.setPassword(passwordEncoder.encode(registerRQ.getPassword()));
      userEntity.setIsDeleted(false);
      userEntity.setIsVerified(false);

        // Validate and assign role
        Optional<RoleEntity> roleEntity = roleRepository.findById(registerRQ.getRoleIds().stream().findFirst().get());
        roleEntity.orElseThrow(() -> new RoleNotFoundException("Role not found with given ID"));
        userEntity.setRoleEntity(roleEntity.get());

        // Save user within transaction
        userRepository.save(userEntity);
    }
    @Transactional
    @Override
    public StructureRS changePassword(ChangePasswordRQ request) {

        log.info("email : {}", SecurityContextHolder.getContext().getAuthentication().getName());
        UserEntity userEntity = userRepository.findByUsernameAndIsDeletedFalseAndStatusTrue(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new BadRequestException(MessageConstant.AUTH.INVALID_TOKEN));

        if (!checkIfValidOldPassword(userEntity,request.getOldPassword())){
            throw new BadRequestException(MessageConstant.AUTH.PASSWORD_NOT_MATCH);
        }
        changeUserPassword(userEntity,request.getNewPassword());
        return response(HttpStatus.OK,MessageConstant.AUTH.CHANGE_PASSWORD_SUCCESSFULLY);
    }
    @Override
    public boolean checkIfValidOldPassword(UserEntity user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public void changeUserPassword(UserEntity user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        new StructureRS(HttpStatus.OK, "Password changed successfully");
    }

    @Override
    public UserProfileRS myProfile() {

        UserEntity userEntity = userRepository.findByUsernameAndIsDeletedFalseAndStatusTrue(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"User is not found."));
        return userEntityMapper.toUserProfileRS(userEntity);
    }
}
