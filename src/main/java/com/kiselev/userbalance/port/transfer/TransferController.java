package com.kiselev.userbalance.port.transfer;

import com.kiselev.userbalance.port.request.TransferRequestDto;
import com.kiselev.userbalance.service.security.JwtService;
import com.kiselev.userbalance.service.transfer.TransferService;
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
            @RequestBody TransferRequestDto request) {
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