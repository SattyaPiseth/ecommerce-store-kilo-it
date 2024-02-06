package com.example.demo.service.user;

import com.example.demo.base.BaseListingRQ;
import com.example.demo.base.StructureRS;
import com.example.demo.db.entity.UserEntity;
import com.example.demo.model.request.auth.ChangePasswordRQ;
import com.example.demo.model.request.auth.RegisterRQ;
import com.example.demo.model.request.user.CustomizePermissionRQ;
import com.example.demo.model.request.user.UpdateProfileRQ;
import com.example.demo.model.request.user.UpdateRoleToUserRQ;
import com.example.demo.model.response.user.UserProfileRS;

/**
 * @author Sattya
 * create at 1/29/2024 10:18 PM
 */
public interface UserService {
    /**
     * Create new user
     * @param registerRQ of Request data from client
     */
    void createNewUser(RegisterRQ registerRQ);

    /**
     * reset password service
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
     *
     * @return UserProfileRS
     */
    UserProfileRS myProfile();

    /**
     * retrieve resource user by uuid from database
     * @param uuid of User
     * @return UserProfileRS --> ErrorResponse
     */
    StructureRS getUserByUuid(String uuid);

    /**
     * Delete user by uuid
     * @param uuid of User
     */
    StructureRS deleteByUuid(String uuid);

    /**
     *  create password reset token for user
     * @param user of UserEntity
     * @param token of UserEntity expired token for user to reset password (15 min)
     */
    void createPasswordResetTokenForUser(UserEntity user, String token);


    /**
     * Update own profile
     * @param request of Request data from client
     * @return message for client
     */
    StructureRS updateOwnProfile(UpdateProfileRQ request);

    /**
     * This method is used to update status(enable or disable)
     * for delete a user(Soft-Delete) by UUID
     * default of 'isDeleted' = false
     * @param uuid      of User
     * @param isDeleted of User
     */
    StructureRS updateIsDeletedByUuid(String uuid, Boolean isDeleted);

    /**
     * This method is used to retrieve all users
     * from database
     * @param response is the request data from client for pagination and filter
     * @return StructureRS
     */
    StructureRS findAllUsers(BaseListingRQ response);
    /**
     * This method is used to update role to user
     *
     * @param uuid    of User
     * @param request of Request data from client
     * @return StructureRS
     */
    StructureRS updateRoleToUser(String uuid, UpdateRoleToUserRQ request);

    // user access based on permission (custom permission)
    StructureRS customizeUserAccess(String uuid, CustomizePermissionRQ request);

}
