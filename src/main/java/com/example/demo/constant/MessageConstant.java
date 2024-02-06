package com.example.demo.constant;

/**
 * @author Sombath
 * create at 23/1/24 3:48 PM
 */
public class MessageConstant {

    public static final String SUCCESSFULLY = "Successfully";
    public static final String ALL = "ALL";

    public static class AUTH {
        public final static String ACCOUNT_LOCKED = "Account locked";
        public final static String INCORRECT_USERNAME_OR_PASSWORD = "Incorrect Username or password";
        public final static String ACCOUNT_DEACTIVATE = "Account have been deactivated";
        public final static String REGISTER_SUCCESSFULLY = "Register successfully";
        public final static String EMAIL_NOT_FOUND = "Email not found";
        public final static String EMAIL_EXIST = "Email already exist";
        public final static String USERNAME_EXIST = "Username already exist";
        public final static String PASSWORD_NOT_MATCH = "Password not match";
        public final static String PASSWORD_SAME = "New password is the same as old password";
        public final static String RESET_PASSWORD_SUCCESSFULLY = "Reset password successfully";
        public final static String CHANGE_PASSWORD_SUCCESSFULLY = "Change password successfully";
        public final static String EMAIL_ALREADY_VERIFIED = "Email already verified";
        public final static String PASSWORD_RESET_TOKEN_ALREADY_SENT= "Password reset token already sent";
        public final static String VERIFICATION_CODE_RESENT = "Verification code resent";
        public final static String RESET_PASSWORD_FAILED = "Reset password failed";
        public final static String EXPIRED_TOKEN = "Expired token";
        public final static String INVALID_TOKEN = "Invalid token";
        public final static String INVALID_REFRESH_TOKEN = "Invalid refresh token";
        public final static String TOKEN_NOT_FOUND = "Token not found";
        public final static String TOKEN_EXPIRED = "Token expired";
        public final static String VERIFY_CODE_NOT_FOUND = "Verify code not found";
        public final static String VERIFY_CODE_NOT_MATCH = "Verify code not match";
        public final static String BAD_CREDENTIALS = "Bad credentials";
        public final static String VERIFY_TOKEN_NOT_FOUND = "Verify token not found";
        public final static String VERIFY_TOKEN_NOT_MATCH = "Verify token not match";
        public final static String RESET_TOKEN_NOT_FOUND = "Reset token not found";
        public final static String RESET_TOKEN_NOT_MATCH = "Reset token not match";
        public final static String USERNAME_OR_EMAIL_ALREADY_EXISTS = "Username or email already exists";
        public final static String EMAIL_VERIFIED = "Email verified";
        public final static String RESET_PASSWORD_EMAIL = "Reset password email";
    }

    public static class ROLE {
        public final static String ADMIN = "ADMIN";
        public final static String USER = "USER";
        public static final String ROLE_CREATED_SUCCESSFULLY = "Role has been created";
        public static final String ROLE_NOT_FOUND = "Role could not be found";
        public static final String ROLE_DELETED_SUCCESSFULLY = "Role has been deleted";
        public static final String ROLE_UPDATED_SUCCESSFULLY = "Role has been updated";
    }

    public static class USER{
        public final static String USER_CREATED_SUCCESSFULLY = "User has been created";
        public final static String USER_NOT_FOUND = "User could not be found";
        public final static String USER_DELETED_SUCCESSFULLY = "User has been deleted";
        public final static String USER_UPDATED_SUCCESSFULLY = "User has been updated";
        public final static String USERNAME_OR_EMAIL_ALREADY_EXISTS = "Username or email already exists";
    }

    public static class PERMISSION{
        public final static String PERMISSION_CREATED_SUCCESSFULLY = "Permission has been created";
        public final static String PERMISSION_NOT_FOUND = "Permission could not be found";
        public final static String PERMISSION_DELETED_SUCCESSFULLY = "Permission has been deleted";
        public final static String PERMISSION_UPDATED_SUCCESSFULLY = "Permission has been updated";
    }


}
