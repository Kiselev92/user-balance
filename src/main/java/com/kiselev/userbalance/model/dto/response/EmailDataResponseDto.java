package com.kiselev.userbalance.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record EmailDataResponseDto(
        Long id,

        Long userId,

        @Schema(description = "Емеил")
        String email
) {}