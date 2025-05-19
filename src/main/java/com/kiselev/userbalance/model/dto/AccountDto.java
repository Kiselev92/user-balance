package com.kiselev.userbalance.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record AccountDto(
        Long id,

        @Schema(description = "ID пользователя")
        Long userId,

        @Schema(description = "Баланс пользователя")
        BigDecimal balance
) {}