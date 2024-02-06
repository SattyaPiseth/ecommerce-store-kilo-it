package com.example.demo.service.user;

import com.example.demo.base.BaseListingRQ;
import com.example.demo.base.BaseService;
import com.example.demo.base.StructureRS;
import com.example.demo.constant.MessageConstant;
import com.example.demo.db.entity.PasswordResetToken;
import com.example.demo.db.entity.PermissionEntity;
import com.example.demo.db.entity.RoleEntity;
import com.example.demo.db.entity.UserEntity;
import com.example.demo.db.repository.PasswordResetTokenRepository;
import com.example.demo.db.repository.PermissionRepository;
import com.example.demo.db.repository.RoleRepository;
import com.example.demo.db.repository.UserRepository;
import com.example.demo.exception.httpstatus.BadRequestException;
import com.example.demo.mapper.UserEntityMapper;
import com.example.demo.model.projection.user.UserEntityInfo;
import com.example.demo.model.projection.user.UserProfileByUuid;
import com.example.demo.model.request.auth.ChangePasswordRQ;
import com.example.demo.model.request.auth.RegisterRQ;
import com.example.demo.model.request.user.CustomizePermissionRQ;
import com.example.demo.model.request.user.UpdateProfileRQ;
import com.example.demo.model.request.user.UpdateRoleToUserRQ;
import com.example.demo.model.response.user.UserProfileRS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

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
    private final PermissionRepository permissionRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createNewUser(RegisterRQ registerRQ){
        // Check if user already exists in the database
        if (userRepository.existsByUsernameOrEmailAndIsDeletedFalse(registerRQ.getUsername(), registerRQ.getEmail())) {
            throw new BadRequestException(MessageConstant.AUTH.USERNAME_OR_EMAIL_ALREADY_EXISTS);
        }

        UserEntity userEntity = userEntityMapper.fromRegisterRQ(registerRQ);
        userEntity.setUuid(UUID.randomUUID().toString());
        userEntity.setPassword(passwordEncoder.encode(registerRQ.getPassword()));
        userEntity.setStatus(false);
        userEntity.setIsDeleted(false);
        userEntity.setDeletedAt(null);
//        userEntity.setIsVerified(false);

        // Validate and assign role
        Long roleId = registerRQ.getRoleIds().stream().findFirst().orElseThrow(() -> new BadRequestException(MessageConstant.ROLE.ROLE_NOT_FOUND));
        Optional<RoleEntity> roleEntity = roleRepository.findById(roleId);
        userEntity.setRoleEntity(roleEntity.orElseThrow(() -> new BadRequestException(MessageConstant.ROLE.ROLE_NOT_FOUND)));

        // Save user within the transaction
        userRepository.save(userEntity);
    }

    @Transactional
    @Override
    public StructureRS changePassword(ChangePasswordRQ request) {
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUsernameAndIsDeletedFalseAndStatusTrue(authenticatedUsername)
                .orElseThrow(() -> new BadRequestException(MessageConstant.AUTH.INVALID_TOKEN));

        if (!checkIfValidOldPassword(userEntity, request.getOldPassword())) {
            throw new BadRequestException(MessageConstant.AUTH.PASSWORD_NOT_MATCH);
        }

        // Check if old password and new password are the same
        if (passwordEncoder.matches(request.getNewPassword(), userEntity.getPassword())) {
            throw new BadRequestException(MessageConstant.AUTH.PASSWORD_SAME);
        }

        changeUserPassword(userEntity, request.getNewPassword());
        return response(HttpStatus.OK, MessageConstant.AUTH.CHANGE_PASSWORD_SUCCESSFULLY);
    }

    @Override
    public boolean checkIfValidOldPassword(UserEntity user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public void changeUserPassword(UserEntity user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        new StructureRS(HttpStatus.OK, MessageConstant.AUTH.CHANGE_PASSWORD_SUCCESSFULLY);
    }

    @Override
    public UserProfileRS myProfile() {

        UserEntity userEntity = userRepository.findByUsernameAndIsDeletedFalseAndStatusTrue(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() ->
                new BadRequestException(MessageConstant.AUTH.INVALID_TOKEN));
        return userEntityMapper.toUserProfileRS(userEntity);
    }

    @Override
    public StructureRS getUserByUuid(String uuid) {
        UserProfileByUuid userProfileByUuid = userRepository.findByUuidFetchRolePermission(uuid);
        if (userProfileByUuid == null) throw new BadRequestException(MessageConstant.USER.USER_NOT_FOUND);
        return response(userProfileByUuid);
    }

    @Transactional
    @Override
    public StructureRS deleteByUuid(String uuid) {
        UserEntity userEntity = userRepository.findByUuidAndStatusTrueAndIsDeletedFalse(uuid)
                .orElseThrow(() -> new BadRequestException(MessageConstant.USER.USER_NOT_FOUND));

        userEntity.setIsDeleted(true);
        userEntity.setStatus(false);
        userEntity.setDeletedAt(Date.from(Instant.now()));
        userRepository.save(userEntity);

        return response(HttpStatus.OK, MessageConstant.USER.USER_DELETED_SUCCESSFULLY);
    }

    @Override
    public void createPasswordResetTokenForUser(UserEntity user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(Date.from(Instant.now().plusSeconds(900)));
        passwordResetTokenRepository.save(passwordResetToken);
    }
    @Transactional
    @Override
    public StructureRS updateOwnProfile(UpdateProfileRQ profileRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            log.info("Email : {}", Optional.ofNullable(jwt.getClaim("email")));

            // Check if the email or username already exists
            if (userRepository.existsByEmailOrUsernameAndIsDeletedFalse(profileRequest.getEmail(), profileRequest.getUsername())) {
                throw new BadRequestException(MessageConstant.USER.USERNAME_OR_EMAIL_ALREADY_EXISTS);
            }
            UserEntity userEntity = userRepository.findByUsernameAndIsDeletedFalseAndStatusTrue(authentication.getName())
                    .orElseThrow(() -> new BadRequestException(MessageConstant.AUTH.INVALID_TOKEN));

            // Update user profile based on profileRequest
            userEntityMapper.fromUpdateProfileRQ(userEntity, profileRequest);
            userRepository.save(userEntity);
        }
        return response(HttpStatus.OK, MessageConstant.USER.USER_UPDATED_SUCCESSFULLY);
    }
    @Transactional
    @Override
    public StructureRS updateIsDeletedByUuid(String uuid, Boolean isDeleted) {
        boolean isFound = userRepository.existsByUuid(uuid);

        if (isFound) {
            if (isDeleted.equals(true)){
                userRepository.updateIsDeletedAndDeletedAtByUuidAndStatusTrue(true,Date.from(Instant.now()),uuid);
                return response(HttpStatus.OK, MessageConstant.USER.USER_DELETED_SUCCESSFULLY);
            }
            else {
                userRepository.updateIsDeletedAndUpdatedAtByUuid(isDeleted,Date.from(Instant.now()),uuid);
                return response(HttpStatus.OK, MessageConstant.USER.USER_UPDATED_SUCCESSFULLY);
            }
        } else {
            return response(HttpStatus.NOT_FOUND, MessageConstant.USER.USER_NOT_FOUND);
        }
    }

    @Override
    public StructureRS findAllUsers(BaseListingRQ response) {
        Page<UserEntityInfo> userEntityInfoPage = userRepository.findByQuery(response.getQuery(), response.getPageable(response.getSort(), response.getOrder()));
        return response(userEntityInfoPage.getContent(), userEntityInfoPage);
    }
    @Transactional
    @Override
    public StructureRS updateRoleToUser(String uuid, UpdateRoleToUserRQ request) {
        UserEntity userEntity = userRepository.findByUuidAndStatusTrueAndIsDeletedFalse(uuid)
                .orElseThrow(() -> new BadRequestException(MessageConstant.USER.USER_NOT_FOUND));
        log.info("Updating role to user with uuid {}",uuid);

        Set<RoleEntity> roles = request.getRoleId().stream()
                .map(roleId -> roleRepository.findById(roleId)
                        .orElseThrow(() -> new BadRequestException(MessageConstant.ROLE.ROLE_NOT_FOUND)))
                .collect(Collectors.toSet());

        userEntity.setRoleEntity(roles.stream().findFirst().orElseThrow(() -> new BadRequestException(MessageConstant.ROLE.ROLE_NOT_FOUND)));
        userRepository.save(userEntity);

        return response(HttpStatus.OK, MessageConstant.USER.USER_UPDATED_SUCCESSFULLY);
    }
    @Transactional
    @Override
    public StructureRS customizeUserAccess(String uuid, CustomizePermissionRQ request) {
        UserEntity userEntity = findUserByUuid(uuid);
        List<PermissionEntity> permissionEntities = findPermissionsByIds(request.getPermissionIds());

        userEntity.getRoleEntity().setPermissionEntities(permissionEntities);
        userRepository.save(userEntity);

        return response(HttpStatus.OK, MessageConstant.USER.USER_UPDATED_SUCCESSFULLY);
    }
    private UserEntity findUserByUuid(String uuid) {
        return userRepository.findByUuidAndStatusTrueAndIsDeletedFalse(uuid)
                .orElseThrow(() -> new BadRequestException(MessageConstant.USER.USER_NOT_FOUND));
    }

    private List<PermissionEntity> findPermissionsByIds(Set<Long> permissionIds) {
        return permissionIds.stream()
                .map(permissionId -> permissionRepository.findById(permissionId)
                        .orElseThrow(() -> new BadRequestException(MessageConstant.PERMISSION.PERMISSION_NOT_FOUND)))
                .toList();
    }

}
