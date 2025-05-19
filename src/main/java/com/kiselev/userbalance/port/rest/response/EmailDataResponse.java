package com.kiselev.userbalance.port.rest.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDataResponse(
        Long id,

        Long userId,

        @Schema(description = "Емеил пользователя")
        @NotBlank(message = "Email is required")
        @Email
        String email
) {}