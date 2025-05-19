package com.kiselev.userbalance.port.security;

import com.kiselev.userbalance.adapter.entity.UserEntity;
import com.kiselev.userbalance.adapter.repository.UserRepository;
import com.kiselev.userbalance.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String login, @RequestParam String password) {
        Optional<UserEntity> userOpt = userRepository.findAll().stream()
                .filter(u -> u.getPassword().equals(password) &&
                        (u.getEmailDataEntities().stream().anyMatch(e -> e.getEmail().equals(login)) ||
                                u.getPhoneDataEntities().stream().anyMatch(p -> p.getPhone().equals(login))))
                .findFirst();

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = jwtService.generateToken(userOpt.get().getId());
        return ResponseEntity.ok(token);
    }
}