package com.kiselev.userbalance.model.mapper;

import com.kiselev.userbalance.adapter.entity.UserEntity;
import com.kiselev.userbalance.domain.User;
import com.kiselev.userbalance.model.dto.UserDto;
import com.kiselev.userbalance.model.dto.response.UserResponseDto;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-19T11:26:43+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto toResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        LocalDate dateOfBirth = null;

        id = user.getId();
        name = user.getName();
        dateOfBirth = user.getDateOfBirth();

        List<String> emails = null;
        List<String> phones = null;

        UserResponseDto userResponseDto = new UserResponseDto( id, name, dateOfBirth, emails, phones );

        return userResponseDto;
    }

    @Override
    public UserEntity toUserEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( userDto.id() );
        userEntity.name( userDto.name() );
        userEntity.password( userDto.password() );
        userEntity.dateOfBirth( userDto.dateOfBirth() );

        return userEntity.build();
    }

    @Override
    public User toDomain(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( dto.id() );
        user.name( dto.name() );
        user.dateOfBirth( dto.dateOfBirth() );
        user.password( dto.password() );

        return user.build();
    }

    @Override
    public UserEntity toEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( user.getId() );
        userEntity.name( user.getName() );
        userEntity.password( user.getPassword() );
        userEntity.dateOfBirth( user.getDateOfBirth() );

        return userEntity.build();
    }

    @Override
    public User toDomain(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( entity.getId() );
        user.name( entity.getName() );
        user.dateOfBirth( entity.getDateOfBirth() );
        user.password( entity.getPassword() );

        return user.build();
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        LocalDate dateOfBirth = null;
        String password = null;

        id = user.getId();
        name = user.getName();
        dateOfBirth = user.getDateOfBirth();
        password = user.getPassword();

        UserDto userDto = new UserDto( id, name, dateOfBirth, password );

        return userDto;
    }
}
