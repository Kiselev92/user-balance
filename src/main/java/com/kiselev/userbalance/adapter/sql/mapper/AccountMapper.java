package com.kiselev.userbalance.adapter.sql.mapper;

import com.kiselev.userbalance.adapter.sql.entity.AccountEntity;
import com.kiselev.userbalance.domain.Account;
import com.kiselev.userbalance.port.rest.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "userId", source = "userEntity.id")
    Account toDomain(AccountEntity entity);

    AccountResponse toResponseDto(Account domain);
}