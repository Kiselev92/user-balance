package com.kiselev.userbalance.port.rest.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record AccountResponse(
        Long id,

        @Schema(description = "ID пользователя")
        Long userId,

        @Schema(description = "Баланс")
        BigDecimal balance
) {}