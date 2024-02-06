package com.example.demo.mapper;

import com.example.demo.db.entity.UserEntity;
import com.example.demo.model.request.auth.RegisterRQ;
import com.example.demo.model.request.user.UpdateProfileRQ;
import com.example.demo.model.request.user.UpdateRoleToUserRQ;
import com.example.demo.model.response.user.UserProfileRS;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author Sattya
 * create at 1/30/2024 11:49 AM
 */
@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity fromRegisterRQ(RegisterRQ registerRQ);
    RegisterRQ toRegisterRQ(UserEntity userEntity);

    @Mapping(target = "roleName", source = "roleEntity.name")
    @Mapping(target = "roleCode", source = "roleEntity.code")
    UserProfileRS toUserProfileRS(UserEntity userEntity);
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateProfileRQ(@MappingTarget UserEntity userEntity, UpdateProfileRQ request);

}
