package com.nadic.desafiobackend.dtos.mappers;

import com.nadic.desafiobackend.dtos.auth.UserDto;
import com.nadic.desafiobackend.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto dto);
}
