package com.example.demo.controller;

import com.example.demo.base.BaseController;
import com.example.demo.base.StructureRS;
import com.example.demo.model.request.role.RoleRQ;
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
        return new StructureRS(HttpStatus.CREATED,"created");
    }
}
