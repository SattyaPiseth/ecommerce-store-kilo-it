package com.example.demo.service.auth;

import com.example.demo.base.StructureRS;
import com.example.demo.model.request.auth.*;
import com.example.demo.model.request.mail.VerifyCodeDto;
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
     * Forgot password service
     * @param request of request email address from client
     * @return message for client to reset-password
     */
    StructureRS forgotPassword(ForgotPasswordRQ request) throws MessagingException;

    /**
     * Reset password service
     * @param request of request data from client email-address,password
     * @return message for client
     */
    StructureRS resetPassword(ResetPasswordRQ request);

    /**
     * Verify reset token
     * @param token of request data from client
     * @return true if token is valid
     */
    boolean verifyResetToken(String token);

    /**
     * Change password service
     * @param request of request data from client
     * @return message for client
     */
    StructureRS changePassword(ChangePasswordRQ request);
}
