package com.kiselev.userbalance.service.transfer;

import com.kiselev.userbalance.adapter.sql.entity.AccountEntity;
import com.kiselev.userbalance.adapter.sql.entity.UserEntity;
import com.kiselev.userbalance.adapter.sql.repository.AccountRepository;
import com.kiselev.userbalance.service.TransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransferService transferService;

    @Test
    void transfer_must_update_balance_and_save() {
        var fromUserId = 1L;
        var toUserId = 2L;
        var amount = new BigDecimal("50.00");

        var from = AccountEntity.builder()
                .id(1L)
                .userEntity(new UserEntity(fromUserId))
                .balance(new BigDecimal("100.00"))
                .build();

        var to = AccountEntity.builder()
                .id(2L)
                .userEntity(new UserEntity(toUserId))
                .balance(new BigDecimal("20.00"))
                .build();

        when(accountRepository.findByUserEntityId(fromUserId)).thenReturn(Optional.of(from));
        when(accountRepository.findByUserEntityId(toUserId)).thenReturn(Optional.of(to));

        transferService.transfer(fromUserId, toUserId, amount);

        assertEquals(new BigDecimal("50.00"), from.getBalance());
        assertEquals(new BigDecimal("70.00"), to.getBalance());

        verify(accountRepository).save(from);
        verify(accountRepository).save(to);
    }

    @Test
    void transfer_must_newThrowException_when_transferring_to_yourself() {
        var userId = 1L;
        var exception = assertThrows(IllegalArgumentException.class,
                () -> transferService.transfer(userId, userId, BigDecimal.TEN));

        assertEquals("Cannot transfer to yourself", exception.getMessage());
    }

    @Test
    void transfer_must_newThrowException_when_not_enough_funds() {
        var fromUserId = 1L;
        var toUserId = 2L;

        var from = AccountEntity.builder()
                .userEntity(new UserEntity(fromUserId))
                .balance(new BigDecimal("5.00"))
                .build();

        var to = AccountEntity.builder()
                .userEntity(new UserEntity(toUserId))
                .balance(new BigDecimal("0.00"))
                .build();

        when(accountRepository.findByUserEntityId(fromUserId)).thenReturn(Optional.of(from));
        when(accountRepository.findByUserEntityId(toUserId)).thenReturn(Optional.of(to));

        var exception = assertThrows(IllegalStateException.class,
                () -> transferService.transfer(fromUserId, toUserId, new BigDecimal("10.00")));

        assertEquals("Not enough funds", exception.getMessage());
    }
}