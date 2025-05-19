package com.kiselev.userbalance.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Builder
@Value
public class EmailData {

    Long id;

    /**
     * Id пользователя
     */
    Long userId;

    /**
     * Email пользователя
     */
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email should be not empty")
    String email;
}