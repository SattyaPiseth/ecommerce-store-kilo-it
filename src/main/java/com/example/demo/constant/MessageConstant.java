package com.example.demo.constant;

/**
 * @author Sombath
 * create at 23/1/24 3:48 PM
 */
public class MessageConstant {

    public static final String SUCCESSFULLY = "Successfully";
    public static final String ALL = "ALL";

    public static class AUTH {
        public final static String INCORRECT_USERNAME_OR_PASSWORD = "Incorrect Username or password";
        public final static String ACCOUNT_DEACTIVATE = "Account have been deactivated";
        public final static String REGISTER_SUCCESSFULLY = "Register successfully";
        public final static String EMAIL_NOT_FOUND = "Email not found";
        public final static String EMAIL_EXIST = "Email already exist";
        public final static String USERNAME_EXIST = "Username already exist";
        public final static String PASSWORD_NOT_MATCH = "Password not match";
        public final static String RESET_PASSWORD_SUCCESSFULLY = "Reset password successfully";
        public final static String CHANGE_PASSWORD_SUCCESSFULLY = "Change password successfully";
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
    }

    public static class ROLE {
        public final static String ADMIN = "ADMIN";
        public final static String USER = "USER";
        public static final String ROLE_CREATED_SUCCESSFULLY = "Role has been created";
        public static final String ROLE_NOT_FOUND = "Role could not be found";
        public static final String ROLE_DELETED_SUCCESSFULLY = "Role has been deleted";
    }

}
