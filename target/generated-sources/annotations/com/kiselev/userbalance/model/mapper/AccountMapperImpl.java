package com.kiselev.userbalance.model.mapper;

import com.kiselev.userbalance.adapter.entity.AccountEntity;
import com.kiselev.userbalance.adapter.entity.UserEntity;
import com.kiselev.userbalance.domain.Account;
import com.kiselev.userbalance.model.dto.AccountDto;
import com.kiselev.userbalance.model.dto.response.AccountResponseDto;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-19T11:26:43+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toDomain(AccountEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.userId( entityUserEntityId( entity ) );
        account.id( entity.getId() );
        account.balance( entity.getBalance() );

        return account.build();
    }

    @Override
    public AccountEntity toEntity(Account domain) {
        if ( domain == null ) {
            return null;
        }

        AccountEntity.AccountEntityBuilder accountEntity = AccountEntity.builder();

        accountEntity.id( domain.getId() );
        accountEntity.balance( domain.getBalance() );

        accountEntity.userEntity( new com.kiselev.userbalance.adapter.entity.UserEntity(domain.getUserId()) );

        return accountEntity.build();
    }

    @Override
    public AccountResponseDto toResponseDto(Account domain) {
        if ( domain == null ) {
            return null;
        }

        Long id = null;
        Long userId = null;
        BigDecimal balance = null;

        id = domain.getId();
        userId = domain.getUserId();
        balance = domain.getBalance();

        AccountResponseDto accountResponseDto = new AccountResponseDto( id, userId, balance );

        return accountResponseDto;
    }

    @Override
    public AccountDto toDto(Account domain) {
        if ( domain == null ) {
            return null;
        }

        Long id = null;
        Long userId = null;
        BigDecimal balance = null;

        id = domain.getId();
        userId = domain.getUserId();
        balance = domain.getBalance();

        AccountDto accountDto = new AccountDto( id, userId, balance );

        return accountDto;
    }

    @Override
    public Account toDomain(AccountDto dto) {
        if ( dto == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.id( dto.id() );
        account.userId( dto.userId() );
        account.balance( dto.balance() );

        return account.build();
    }

    private Long entityUserEntityId(AccountEntity accountEntity) {
        if ( accountEntity == null ) {
            return null;
        }
        UserEntity userEntity = accountEntity.getUserEntity();
        if ( userEntity == null ) {
            return null;
        }
        Long id = userEntity.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
