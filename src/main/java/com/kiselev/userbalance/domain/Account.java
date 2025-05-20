package com.kiselev.userbalance.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import java.math.BigDecimal;

@With
@Builder
@Value
public class Account {

    Long id;

    /**
     * id пользователя
     */
    Long userId;

    /**
     * Баланс пользователя
     */
    BigDecimal balance;

}