package com.kiselev.userbalance.service;

import com.kiselev.userbalance.adapter.entity.AccountEntity;
import com.kiselev.userbalance.adapter.repository.AccountRepository;
import com.kiselev.userbalance.model.mapper.AccountMapper;
import com.kiselev.userbalance.model.dto.response.AccountResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountResponseDto getById(Long id) {
        AccountEntity entity = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        return accountMapper.toResponseDto(accountMapper.toDomain(entity));
    }

    public AccountResponseDto getByUserId(Long userId) {
        AccountEntity entity = accountRepository.findByUserEntityId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found for userId = " + userId));
        return accountMapper.toResponseDto(accountMapper.toDomain(entity));
    }
}