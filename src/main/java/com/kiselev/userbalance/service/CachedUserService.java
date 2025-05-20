package com.kiselev.userbalance.service;

import com.kiselev.userbalance.adapter.sql.entity.UserEntity;
import com.kiselev.userbalance.adapter.sql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CachedUserService {

    private final UserRepository userRepository;

    @Cacheable(value = "usersByLogin", key = "#login + ':' + #password")
    public Optional<UserEntity> findByLoginAndPassword(String login, String password) {
        return userRepository.findAll().stream()
                .filter(u -> u.getPassword().equals(password) &&
                        (u.getEmailDataEntities().stream().anyMatch(e -> e.getEmail().equals(login)) ||
                                u.getPhoneDataEntities().stream().anyMatch(p -> p.getPhone().equals(login))))
                .findFirst();
    }

    @CachePut(value = "usersByLogin", key = "#login + ':' + #password")
    public UserEntity cacheUser(UserEntity user, String login, String password) {
        return user;
    }
}