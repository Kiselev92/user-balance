package com.kiselev.userbalance.service;

import com.kiselev.userbalance.adapter.sql.entity.AccountEntity;
import com.kiselev.userbalance.adapter.sql.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferService {

    private final AccountRepository accountRepository;

    @Transactional
    public void transfer(Long fromUserId, Long toUserId, BigDecimal amount) {
        if (fromUserId.equals(toUserId)) {
            throw new IllegalArgumentException("Cannot transfer to yourself");
        }

        AccountEntity from = accountRepository.findByUserEntityId(fromUserId)
                .orElseThrow(() -> new EntityNotFoundException("Sender account not found"));

        AccountEntity to = accountRepository.findByUserEntityId(toUserId)
                .orElseThrow(() -> new EntityNotFoundException("Recipient account not found"));

        // Необходимо защититься от DDos чтобы все потоки не заблокировались.
        // Этот лок спасает только в рамках 1 пода. Для нескольких подов, используем пессимистичную блокировку на двух балансах БД.
        synchronized (getLock(fromUserId, toUserId)) {
            if (from.getBalance().compareTo(amount) < 0) {
                throw new IllegalStateException("Not enough funds");
            }
            try {
                from.setBalance(from.getBalance().subtract(amount));
                to.setBalance(to.getBalance().add(amount));

                accountRepository.save(from);
                accountRepository.save(to);
            } catch (Throwable e) {
                log.error("Failed to transfer from {} to {} amount {}", fromUserId, toUserId, amount, e);
                // В реальной жизни упавшие транзакции стоит куда-то записывать
                throw e;
            }
        }
        log.info("Transfer from {} to {} amount {}", fromUserId, toUserId, amount);
    }

    private Object getLock(Long id1, Long id2) {
        return (id1 < id2)
                ? (id1.toString() + "_" + id2.toString()).intern()
                : (id2.toString() + "_" + id1.toString()).intern();
    }
}