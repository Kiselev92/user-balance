package com.kiselev.userbalance.port.rest;

import com.kiselev.userbalance.port.rest.response.UserResponse;
import com.kiselev.userbalance.service.UserService;
import com.kiselev.userbalance.service.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "User Api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @Operation(summary = "Получить пользователя ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary = "Получить список пользователей")
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateOfBirth,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(userService.search(name, dateOfBirth, phone, email, offset, limit));
    }

    @Operation(summary = "Обновить емеил пользователя (если не занят)")
    @PutMapping("/{id}/emails")
    public ResponseEntity<Void> updateEmails(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody List<@Email String> emails) {
        Long userId = extractUserId(authorizationHeader);
        if (!userId.equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        userService.updateEmail(userId, emails);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Обновить телефон пользователя (если не занят)")
    @PutMapping("/{id}/phones")
    public ResponseEntity<Void> updatePhone(
            @PathVariable Long id,
            @RequestBody List<@Pattern(regexp = "^\\d{11}$") String> phones) {
        userService.updatePhone(id, phones);
        return ResponseEntity.ok().build();
    }

    private Long extractUserId(String authHeader) {
        log.info("Authorization header: {}", authHeader);
        if (authHeader == null) {
            throw new IllegalArgumentException("Invalid Authorization header");
        }
        return jwtService.extractUserId(authHeader);
    }
}