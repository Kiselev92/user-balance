package com.kiselev.userbalance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class UserBalanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserBalanceApplication.class, args);
    }

}