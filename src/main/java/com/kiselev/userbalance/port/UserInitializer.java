package com.kiselev.userbalance.port;

import com.kiselev.userbalance.adapter.sql.entity.AccountEntity;
import com.kiselev.userbalance.adapter.sql.entity.EmailDataEntity;
import com.kiselev.userbalance.adapter.sql.entity.UserEntity;
import com.kiselev.userbalance.adapter.sql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class UserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        Random random = new Random();
        int r = random.nextInt(1000);
        for (int i = 0; i < 2; i++) {
            String userName = "user" + i;
            String password = "password" + i;
            String emailString = "email" + r + i + "@example.com";

            UserEntity build = UserEntity.builder()
                    .name(userName)
                    .password(password)
                    .dateOfBirth(LocalDate.of(1999, 1, 1))
                    .build();

            var email = new EmailDataEntity();
            email.setEmail(emailString);
            email.setUserEntity(build);

            var balance = new AccountEntity();
            balance.setBalance(new BigDecimal("1000.50"));
            balance.setInitialDeposit(new BigDecimal("1000.50"));
            balance.setUserEntity(build);

            build.setEmailDataEntities(List.of(email));
            build.setPhoneDataEntities(Collections.emptyList());
            build.setAccountEntity(balance);

            userRepository.save(build);
        }
    }
}