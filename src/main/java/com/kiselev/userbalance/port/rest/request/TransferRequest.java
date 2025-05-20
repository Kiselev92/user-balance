package com.kiselev.userbalance.port.rest.request;

import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public record TransferRequest(
        Long toUserId,

        @DecimalMin(value = "0.01", message = "Amount must be positive")
        BigDecimal amount
) {}