package com.kiselev.userbalance.port.rest;

import com.kiselev.userbalance.port.rest.request.TransferRequest;
import com.kiselev.userbalance.service.TransferService;
import com.kiselev.userbalance.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;
    private final JwtService jwtService;

    @PostMapping//
    public ResponseEntity<?> transfer(
            @RequestHeader(value = "Authorization") String authorizationHeader,
            @RequestBody TransferRequest request) {
        Long fromUserId = extractUserId(authorizationHeader);
        transferService.transfer(fromUserId, request.toUserId(), request.amount());
        return ResponseEntity.ok().build();
    }

    private Long extractUserId(String authHeader) {
        if (authHeader == null) {
            throw new IllegalArgumentException("Invalid Authorization header");
        }
        return jwtService.extractUserId(authHeader);
    }
}