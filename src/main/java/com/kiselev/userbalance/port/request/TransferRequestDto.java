package com.kiselev.userbalance.port.request;

import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public record TransferRequestDto(
        Long toUserId,

        @DecimalMin(value = "0.01", message = "Amount must be positive")
        BigDecimal amount
)
{}