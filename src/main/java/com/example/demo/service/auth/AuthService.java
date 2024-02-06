package com.example.demo.service.auth;

import com.example.demo.base.StructureRS;
import com.example.demo.db.entity.UserEntity;
import com.example.demo.model.request.auth.*;
import com.example.demo.model.request.mail.VerifyRQ;
import jakarta.mail.MessagingException;

import javax.management.relation.RoleNotFoundException;

/**
 * @author Sattya
 * create at 1/26/2024 11:50 PM
 */
public interface AuthService {
    /**
     * Login service for user
     * @param request of Request data from client
     * @return message for client
     */
    StructureRS login(LoginRQ request);
    /**
     * Register service for user
     * @param request of Request data from client
     * @return message for client
     */
    StructureRS register(RegisterRQ request) throws RoleNotFoundException, MessagingException;

    /**
     * Verify service for user
     * @param request of Request data from client
     */
    void verifyRQ(VerifyRQ request);
    /**
     * Verify service for user
     * @param token of Request data from client
     */
    void verifyUser(String token);

    /**
     * forgot password service
     * @param request of request email address from client
     * @return message for client to reset-password
     * @throws MessagingException if mail not sent
     */
    StructureRS forgotPassword(ForgotPasswordRQ request) throws MessagingException;

    /**
     * change password service
     * @param request of request data from client email-address,password
     * @return message for client
     */
    StructureRS changePassword(String token,ResetPasswordRQ request);

    /**
     * Validate password reset token service
     * @param token of token from client
     * @return true if token is valid
     */
    boolean validatePasswordResetToken(String token);

    /**
     * Get user by password reset token service
     * @param token of token from client
     * @return user entity
     */
    UserEntity getUserByPasswordResetToken(String token);

    /**
     * Resend verification email service
     * @param email of email from client
     * @return message for client
     * @throws MessagingException if mail not sent
     */
    StructureRS resendVerificationCodeViaEmail(String email) throws MessagingException;

}
