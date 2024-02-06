package com.example.demo.model.projection.user;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Projection for {@link com.example.demo.db.entity.UserEntity}
 */
public interface UserProfileByUuid {
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

    RoleEntityInfo getRoleEntity();

    /**
     * Projection for {@link com.example.demo.db.entity.RoleEntity}
     */
    interface RoleEntityInfo {
        Instant getCreatedAt();

        Date getUpdatedAt();

        Long getId();

        String getName();

        String getCode();

        List<PermissionEntityInfo> getPermissionEntities();

        /**
         * Projection for {@link com.example.demo.db.entity.PermissionEntity}
         */
        interface PermissionEntityInfo {
            Long getId();

            String getName();

            String getModule();
        }
    }
}