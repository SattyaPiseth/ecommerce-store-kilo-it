package com.example.demo.controller;

import com.example.demo.base.BaseController;
import com.example.demo.base.BaseListingRQ;
import com.example.demo.base.PagingRS;
import com.example.demo.base.StructureRS;
import com.example.demo.model.request.auth.ChangePasswordRQ;
import com.example.demo.model.request.user.CustomizePermissionRQ;
import com.example.demo.model.request.user.UpdateIsDeletedRQ;
import com.example.demo.model.request.user.UpdateProfileRQ;
import com.example.demo.model.request.user.UpdateRoleToUserRQ;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sattya
 * create at 1/31/2024 7:59 AM
 */
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/change-password")
    public StructureRS changePassword(@RequestBody @Validated ChangePasswordRQ request){
        return response(userService.changePassword(request)).getBody();
    }
    @GetMapping("/my-profile")
    public StructureRS myProfile(){
        return response(userService.myProfile(),new PagingRS(1,1,1,1)).getBody();
    }
    @GetMapping("/{uuid}")
    public StructureRS getUserByUuid(@PathVariable String uuid){
        return response(userService.getUserByUuid(uuid)).getBody();
    }
    @DeleteMapping("/{uuid}")
    public StructureRS isDeleted(@PathVariable String uuid){
        return response(userService.deleteByUuid(uuid)).getBody();
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("update-profile")
    public StructureRS updateUserProfile(@RequestBody @Validated UpdateProfileRQ request){
        return response(userService.updateOwnProfile(request)).getBody();
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{uuid}/update-is-deleted")
    public StructureRS updateIsDeletedByUuid(@PathVariable String uuid,
                                             @RequestBody @Validated UpdateIsDeletedRQ request){
        return response(userService.updateIsDeletedByUuid(uuid,request.getIsDeleted())).getBody();
    }
    @GetMapping
    public StructureRS findAllUsers(BaseListingRQ response){
        return response(userService.findAllUsers(response)).getBody();
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{uuid}/update-role")
    public StructureRS updateRoleToUser(@PathVariable String uuid,
                                        @RequestBody @Validated UpdateRoleToUserRQ request){
        return response(userService.updateRoleToUser(uuid,request)).getBody();
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{uuid}/customize-permission")
    public StructureRS customizePermission(@PathVariable String uuid,
                                          @RequestBody @Validated CustomizePermissionRQ request){
        return response(userService.customizeUserAccess(uuid,request)).getBody();
    }
}
