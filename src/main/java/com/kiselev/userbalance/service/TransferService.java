package com.kiselev.userbalance.service;

import com.kiselev.userbalance.adapter.sql.entity.AccountEntity;
import com.kiselev.userbalance.adapter.sql.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferService {

    private final AccountRepository accountRepository;
    private final ReentrantLock lock = new ReentrantLock(true);

    @Transactional
    public void transfer(Long fromUserId, Long toUserId, BigDecimal amount) {
        if (fromUserId.equals(toUserId)) {
            throw new IllegalArgumentException("Cannot transfer to yourself");
        }

        // Но при работе с несколькими подами этого мало - используется пессимистическая блокировка на БД
        lock.lock();
        try {
            AccountEntity from = accountRepository.findForUpdateByUserId(fromUserId)
                    .orElseThrow(() -> new EntityNotFoundException("Sender account not found"));

            AccountEntity to = accountRepository.findForUpdateByUserId(toUserId)
                    .orElseThrow(() -> new EntityNotFoundException("Recipient account not found"));

            if (from.getBalance().compareTo(amount) < 0) {
                throw new IllegalStateException("Not enough funds");
            }

            from.setBalance(from.getBalance().subtract(amount));
            to.setBalance(to.getBalance().add(amount));

            accountRepository.save(from);
            accountRepository.save(to);

            log.info("Transfer from {} to {} amount {}", fromUserId, toUserId, amount);
        } catch (Throwable e) {
            log.error("Failed to transfer from {} to {} amount {}", fromUserId, toUserId, amount, e);
            // В реальной жизни упавшие транзакции надо куда-то записывать
            throw e;
        } finally {
            lock.unlock();
        }
    }
}