package com.kiselev.userbalance.port.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

public record UserResponse(
        Long id,

        @Schema(description = "Имя пользователя")
        @NotBlank(message = "Name is required")
        String name,

        @Schema(description = "Дата рождения")
        @JsonFormat(pattern = "dd.MM.yyyy")
        LocalDate dateOfBirth,

        @Schema(description = "Список емеил-адересов")
        List<String> emails,
        @Schema(description = "Список телефонов")
        List<String> phones
) {}