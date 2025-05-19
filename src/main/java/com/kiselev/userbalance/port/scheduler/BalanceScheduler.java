package com.kiselev.userbalance.port.scheduler;

import com.kiselev.userbalance.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BalanceScheduler {

    private final BalanceService balanceService;

    @Scheduled(fixedRate = 30000)
    public void increaseBalance() {
        balanceService.increaseAllBalances();
    }
}