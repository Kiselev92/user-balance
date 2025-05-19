package com.kiselev.userbalance.domain;

import lombok.Builder;
import lombok.Data;
import lombok.With;
import java.math.BigDecimal;

@With
@Builder
@Data
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