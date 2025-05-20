package com.kiselev.userbalance.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import java.time.LocalDate;

@With
@Builder
@Value
public class User {
    /**
     * ID пользователя
     */
    Long id;

    /**
     * Имя пользователя
     */
    String name;

    /**
     * Дата рождения пользователя
     */
    LocalDate dateOfBirth;

    /**
     * Пароль пользователя.
     */
    String password;
}