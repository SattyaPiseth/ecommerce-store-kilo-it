package com.example.demo.controller;

import com.example.demo.base.BaseController;
import com.example.demo.base.BaseListingRQ;
import com.example.demo.base.StructureRS;
import com.example.demo.constant.MessageConstant;
import com.example.demo.model.request.role.RoleRQ;
import com.example.demo.model.request.role.UpdateRoleRQ;
import com.example.demo.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sattya
 * create at 1/29/2024 3:49 PM
 */
@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController extends BaseController {
    private final RoleService roleService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public StructureRS createRole(@RequestBody @Validated RoleRQ roleRQ){
        roleService.createRole(roleRQ);
        return new StructureRS(HttpStatus.CREATED, MessageConstant.ROLE.ROLE_CREATED_SUCCESSFULLY);
    }
    @GetMapping
    public StructureRS getAllRoles(BaseListingRQ response){
        return roleService.getAllRoles(response);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{roleId}")
    public StructureRS deleteRole(@PathVariable Long roleId){
        roleService.deleteRole(roleId);
        return new StructureRS(HttpStatus.OK, MessageConstant.ROLE.ROLE_DELETED_SUCCESSFULLY);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{roleId}")
    public StructureRS updateRole(@PathVariable Long roleId, @RequestBody UpdateRoleRQ request){
        roleService.updateRole(roleId, request);
        return new StructureRS(HttpStatus.OK, MessageConstant.ROLE.ROLE_UPDATED_SUCCESSFULLY);
    }
}
