package com.kiselev.userbalance.port.rest.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PhoneDataResponse(
        Long id,

        Long userId,

        @Schema(description = "Телефон пользователя")
        @Size(min = 11, max = 11, message = "Phone must be 11 digits long")
        @NotEmpty(message = "Phone should be not empty")
        @Pattern(regexp = "^\\d{11}$", message = "Phone must contain exactly 11 digits")
        String phone
) {}