package com.example.demo.mapper;

import com.example.demo.db.entity.UserEntity;
import com.example.demo.model.request.auth.RegisterRQ;
import org.mapstruct.Mapper;

/**
 * @author Sattya
 * create at 1/30/2024 11:49 AM
 */
@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity fromRegisterRQ(RegisterRQ registerRQ);
    RegisterRQ toRegisterRQ(UserEntity userEntity);
}
