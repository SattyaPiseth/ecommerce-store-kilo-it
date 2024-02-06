package com.example.demo.model.projection.user;

/**
 * Projection for {@link com.example.demo.db.entity.UserEntity}
 */
public interface UserEntityInfo {
    Long getId();

    String getUuid();

    String getUsername();

    String getEmail();

    String getName();

    String getBio();

    String getAvatar();

    String getAddress();

    String getPhone();

    Boolean getStatus();

//    Boolean getDeletedAt();

    RoleEntityInfo getRoleEntity();

    /**
     * Projection for {@link com.example.demo.db.entity.RoleEntity}
     */
    interface RoleEntityInfo {
        Long getId();

        String getName();

        String getCode();
    }
}