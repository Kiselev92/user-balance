package com.kiselev.userbalance.port.rest;

import com.kiselev.userbalance.port.rest.response.PhoneDataResponse;
import com.kiselev.userbalance.service.PhoneDataService;
import com.kiselev.userbalance.port.rest.request.PhoneDataRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/phones")
public class PhoneDataController {

    private final PhoneDataService phoneDataService;

    @Operation(summary = "Получить все телефоны по ID пользователя")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PhoneDataResponse>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(phoneDataService.getByUserId(userId));
    }

    @Operation(summary = "Добавить телефон по ID пользователя (если не занят)")
    @PostMapping("/user/{userId}")
    public ResponseEntity<Void> addPhone(@PathVariable Long userId, @RequestBody @Valid PhoneDataRequest dto) {
        phoneDataService.add(userId, dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить телефон по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        phoneDataService.delete(id);
        return ResponseEntity.ok().build();
    }
}