package com.example.demo.constant;

/**
 * @author Sombath
 * create at 23/1/24 3:48 PM
 */
public class MessageConstant {

    public static final String SUCCESSFULLY = "Successfully";
    public static final String ITEMNOTFOUND = "Item Not Found";
    public static final String ALL = "ALL";

    public static class AUTH {
        public final static String INCORRECT_USERNAME_OR_PASSWORD = "Incorrect Username or password";
        public final static String ACCOUNT_DEACTIVATE = "Account have been deactivated";

        public final static String BAD_CREDENTIALS = "Bad credentials";
    }

    public static class ROLE {
        public final static String ADMIN = "ADMIN";
        public final static String USER = "USER";
        public static final String ROLE_CREATED_SUCCESSFULLY = "Role has been created";
        public static final String ROLE_NOT_FOUND = "Role could not be found";
        public static final String ROLE_DELETED_SUCCESSFULLY = "Role has been deleted";
    }
   public static class CATEGORY{
        public final static String CATEGORYIDALREADYEXIST= "Category already exist";
        public static final String ITEMCATEGORYNOTFOUND = "Item Not Found";

   }
}
