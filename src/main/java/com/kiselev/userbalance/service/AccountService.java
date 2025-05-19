package com.kiselev.userbalance.service;

import com.kiselev.userbalance.adapter.sql.entity.AccountEntity;
import com.kiselev.userbalance.adapter.sql.mapper.AccountMapper;
import com.kiselev.userbalance.adapter.sql.repository.AccountRepository;
import com.kiselev.userbalance.port.rest.response.AccountResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountResponse getById(Long id) {
        AccountEntity entity = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        return accountMapper.toResponseDto(accountMapper.toDomain(entity));
    }

    public AccountResponse getByUserId(Long userId) {
        AccountEntity entity = accountRepository.findByUserEntityId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found for userId = " + userId));
        return accountMapper.toResponseDto(accountMapper.toDomain(entity));
    }
}