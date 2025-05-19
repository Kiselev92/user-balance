package com.kiselev.userbalance.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record UserDto(

        Long id,

        @Schema(description = "Имя пользователя")
        @NotBlank(message = "Name is required")
        String name,

        @Schema(description = "Дата рождения пользователя")
        @JsonFormat(pattern = "dd.MM.yyyy")
        LocalDate dateOfBirth,

        @Schema(description = "Пароль пользователя")
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 500, message = "Password must be 8-500 characters")
        String password
) {}