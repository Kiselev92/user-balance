package com.kiselev.userbalance.port.rest;

import com.kiselev.userbalance.port.rest.response.AccountResponse;
import com.kiselev.userbalance.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "Получить аккаунт по ID аккаунта")
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getById(id));
    }

    @Operation(summary = "Получить аккаунт по ID пользователя")
    @GetMapping("/user/{userId}")
    public ResponseEntity<AccountResponse> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getByUserId(userId));
    }
}