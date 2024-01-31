package com.example.demo.service.user;

import com.example.demo.base.StructureRS;
import com.example.demo.db.entity.UserEntity;
import com.example.demo.model.request.auth.ChangePasswordRQ;
import com.example.demo.model.request.auth.RegisterRQ;
import com.example.demo.model.response.user.UserProfileRS;

import javax.management.relation.RoleNotFoundException;

/**
 * @author Sattya
 * create at 1/29/2024 10:18 PM
 */
public interface UserService {
    /**
     * Create new user
     * @param registerRQ of Request data from client
     */
    void createNewUser(RegisterRQ registerRQ) throws RoleNotFoundException;

    /**
     * Change password service
     * @param request of request data from client
     * @return message for client
     */
    StructureRS changePassword(ChangePasswordRQ request);

    /**
     * Check if valid old password for user
     * @param user of UserEntity
     * @param oldPassword of UserEntity
     * @return boolean value for client
     */
    boolean checkIfValidOldPassword(UserEntity user, String oldPassword);

    /**
     * Change user password service
     * @param user of UserEntity
     * @param newPassword of UserEntity
     */
    void changeUserPassword(UserEntity user, String newPassword);

    /**
     * Retrieve current logged in user
     * @return UserProfileRS
     */
    UserProfileRS myProfile();
}
