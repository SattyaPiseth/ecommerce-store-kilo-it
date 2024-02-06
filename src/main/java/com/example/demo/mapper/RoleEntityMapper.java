package com.example.demo.mapper;

import com.example.demo.db.entity.RoleEntity;
import com.example.demo.model.request.role.UpdateRoleRQ;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author Sattya
 * create at 2/5/2024 10:19 PM
 */
@Mapper(componentModel = "spring")
public interface RoleEntityMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromRoleRQ(UpdateRoleRQ updateRoleRQ, @MappingTarget RoleEntity roleEntity);
}
