package com.kiselev.userbalance.adapter.sql.mapper;

import com.kiselev.userbalance.adapter.sql.entity.UserEntity;
import com.kiselev.userbalance.domain.User;
import com.kiselev.userbalance.port.rest.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User toDomain(UserEntity entity);

    UserResponse toResponse(User user);
}