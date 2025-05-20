package com.kiselev.userbalance.port.rest;

import com.kiselev.userbalance.adapter.sql.entity.UserEntity;
import com.kiselev.userbalance.service.CachedUserService;
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

    private final CachedUserService cachedUserService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String login, @RequestParam String password) {
        Optional<UserEntity> userOpt = cachedUserService.findByLoginAndPassword(login, password);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = jwtService.generateToken(userOpt.get().getId());
        return ResponseEntity.ok(token);
    }
}