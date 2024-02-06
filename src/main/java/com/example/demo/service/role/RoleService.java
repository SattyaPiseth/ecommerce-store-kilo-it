package com.example.demo.service.role;

import com.example.demo.base.BaseListingRQ;
import com.example.demo.base.StructureRS;
import com.example.demo.model.request.role.RoleRQ;
import com.example.demo.model.request.role.UpdateRoleRQ;

/**
 * @author Sattya
 * create at 1/29/2024 3:36 PM
 */
public interface RoleService {
    /**
     * Create a new role
     * @param roleRQ the new role to create
     */
    void createRole(RoleRQ roleRQ);
    /**
     * Delete a role
     * @param roleId the role id to delete
     */
    void deleteRole(Long roleId);
    /**
     * Update a role
     * @param roleId the role id to update
     * @param request the new role data
     */
    void updateRole(Long roleId, UpdateRoleRQ request);
    /**
     * Get all roles with pagination and filter options
     * @param response the request data
     * @return the response data with pagination and filter options
     */
    StructureRS getAllRoles(BaseListingRQ response);
}
