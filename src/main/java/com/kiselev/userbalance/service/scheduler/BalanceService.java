package com.kiselev.userbalance.service.scheduler;

import com.kiselev.userbalance.adapter.entity.AccountEntity;
import com.kiselev.userbalance.adapter.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final AccountRepository accountRepository;

    @Transactional
    public void increaseAllBalances() {
        List<AccountEntity> accounts = accountRepository.findAll();

        for (AccountEntity account : accounts) {
            BigDecimal initial = account.getInitialDeposit();
            BigDecimal maxBalance = initial.multiply(BigDecimal.valueOf(2.07));
            BigDecimal current = account.getBalance();
            BigDecimal increased = current.multiply(BigDecimal.valueOf(1.1));

            if (increased.compareTo(maxBalance) > 0) {
                increased = maxBalance;
            }

            account.setBalance(increased);
        }

        accountRepository.saveAll(accounts);
    }
}