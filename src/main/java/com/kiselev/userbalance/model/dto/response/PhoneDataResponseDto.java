package com.kiselev.userbalance.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record PhoneDataResponseDto(
        Long id,

        Long userId,

        @Schema(description = "Номер телефона")
        String phone
) {}