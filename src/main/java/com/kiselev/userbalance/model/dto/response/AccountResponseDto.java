package com.kiselev.userbalance.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record AccountResponseDto(
        Long id,

        Long userId,

        @Schema(description = "Баланс")
        BigDecimal balance
) {}