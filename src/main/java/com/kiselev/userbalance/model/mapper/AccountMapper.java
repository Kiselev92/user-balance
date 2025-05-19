package com.kiselev.userbalance.model.mapper;

import com.kiselev.userbalance.adapter.entity.AccountEntity;
import com.kiselev.userbalance.domain.Account;
import com.kiselev.userbalance.model.dto.AccountDto;
import com.kiselev.userbalance.model.dto.response.AccountResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "userId", source = "userEntity.id")
    Account toDomain(AccountEntity entity);

    @Mapping(
            target = "userEntity",
            expression = "java(new com.kiselev.userbalance.adapter.entity.UserEntity(domain.getUserId()))"
    )
    AccountEntity toEntity(Account domain);

    AccountResponseDto toResponseDto(Account domain);

    AccountDto toDto(Account domain);

    Account toDomain(AccountDto dto);
}