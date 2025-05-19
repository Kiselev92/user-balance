package com.kiselev.userbalance.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDataDto (

        Long id,

        @Schema(description = "ID пользователя")
        Long userId,

        @Schema(description = "Емеил пользователя")
        @NotBlank(message = "Email is required")
        @Email
        String email
){}