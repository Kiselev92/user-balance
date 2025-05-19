package com.kiselev.userbalance.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Builder
@Value
public class PhoneData {
    Long id;

    /**
     * Id пользователя
     */
    Long userId;

    /**
     * Телефон пользователя
     */
    String phone;
}