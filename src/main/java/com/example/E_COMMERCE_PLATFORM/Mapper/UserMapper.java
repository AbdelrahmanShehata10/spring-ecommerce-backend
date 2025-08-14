package com.example.E_COMMERCE_PLATFORM.Mapper;

import com.example.E_COMMERCE_PLATFORM.DTO.Register_DTO;
import com.example.E_COMMERCE_PLATFORM.DTO.UserDto;
import com.example.E_COMMERCE_PLATFORM.DTO.login_DTO;
import com.example.E_COMMERCE_PLATFORM.Entites.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "username", target = "username")
    @Mapping(source = "age", target = "age")
    UserDto toDto(Users user);
    Users toEntity(Register_DTO register);
    Users toEntity_login(login_DTO register);
// here type is user for registration take this type
 void update(UserDto UPDATE_REQUEST, @MappingTarget  Users user);
}
