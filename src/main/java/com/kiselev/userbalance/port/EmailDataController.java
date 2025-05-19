package com.kiselev.userbalance.port;

import com.kiselev.userbalance.model.dto.EmailDataDto;
import com.kiselev.userbalance.model.dto.response.EmailDataResponseDto;
import com.kiselev.userbalance.service.EmailDataService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emails")
public class EmailDataController {

    private final EmailDataService emailDataService;

    @Operation(summary = "Получить все емеилы по ID пользователя")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EmailDataResponseDto>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(emailDataService.getByUserId(userId));
    }

    @Operation(summary = "Добавить емеил по ID пользователя (если не занят)")
    @PostMapping("/user/{userId}")
    public ResponseEntity<Void> addEmail(@PathVariable Long userId, @RequestBody @Valid EmailDataDto dto) {
        emailDataService.add(userId, dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить емеил по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        emailDataService.delete(id);
        return ResponseEntity.ok().build();
    }
}