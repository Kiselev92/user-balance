package com.kiselev.userbalance;

import org.springframework.boot.SpringApplication;

public class TestUserBalanceApplication {

    public static void main(String[] args) {
        SpringApplication.from(UserBalanceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
