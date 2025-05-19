package com.kiselev.userbalance.model.mapper;

import com.kiselev.userbalance.adapter.entity.UserEntity;
import com.kiselev.userbalance.domain.User;
import com.kiselev.userbalance.model.dto.UserDto;
import com.kiselev.userbalance.model.dto.response.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserResponseDto toResponseDto(User user);

    UserEntity toUserEntity(UserDto userDto);

    User toDomain(UserDto dto);

    UserEntity toEntity(User user);

    User toDomain(UserEntity entity);

    UserDto toDto(User user);
}