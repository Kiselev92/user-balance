package com.kiselev.userbalance.service;

import com.kiselev.userbalance.adapter.sql.entity.EmailDataEntity;
import com.kiselev.userbalance.adapter.sql.entity.UserEntity;
import com.kiselev.userbalance.adapter.sql.mapper.EmailDataMapper;
import com.kiselev.userbalance.adapter.sql.repository.EmailDataRepository;
import com.kiselev.userbalance.adapter.sql.repository.UserRepository;
import com.kiselev.userbalance.port.rest.response.EmailDataResponse;
import com.kiselev.userbalance.port.rest.request.EmailDataRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailDataService {

    private final EmailDataRepository emailDataRepository;
    private final EmailDataMapper emailDataMapper;
    private final UserRepository userRepository;

    public List<EmailDataResponse> getByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return user.getEmailDataEntities().stream()
                .map(emailDataMapper::toDomain)
                .map(emailDataMapper::toDto)
                .map(dto -> new EmailDataResponse(dto.id(), dto.userId(), dto.email()))
                .toList();
    }

    public void add(Long userId, EmailDataRequest dto) {
        if (emailDataRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        EmailDataEntity entity = new EmailDataEntity(null, user, dto.email());
        emailDataRepository.save(entity);
    }

    public void delete(Long id) {
        EmailDataEntity entity = emailDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Email not found"));
        emailDataRepository.delete(entity);
    }
}