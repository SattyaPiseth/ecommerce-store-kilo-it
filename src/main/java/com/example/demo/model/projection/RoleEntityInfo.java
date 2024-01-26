package com.example.demo.model.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Projection for {@link com.example.demo.db.entity.RoleEntity}
 */
public interface RoleEntityInfo {
    Long getId();

    String getName();

    String getCode();

    @JsonProperty("permission")
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