package com.kiselev.userbalance.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;

public record UserResponseDto(
        Long id,

        String name,

        @Schema(description = "Дата рождения")
        @JsonFormat(pattern = "dd.MM.yyyy")
        LocalDate dateOfBirth,

        @Schema(description = "Список емеил-адересов")
        List<String> emails,
        @Schema(description = "Список телефонов")
        List<String> phones
) {}