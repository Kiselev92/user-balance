package com.kiselev.userbalance.service;

import com.kiselev.userbalance.adapter.sql.entity.PhoneDataEntity;
import com.kiselev.userbalance.adapter.sql.entity.UserEntity;
import com.kiselev.userbalance.adapter.sql.mapper.PhoneDataMapper;
import com.kiselev.userbalance.adapter.sql.repository.PhoneDataRepository;
import com.kiselev.userbalance.adapter.sql.repository.UserRepository;
import com.kiselev.userbalance.port.rest.request.PhoneDataRequest;
import com.kiselev.userbalance.port.rest.response.PhoneDataResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneDataService {

    private final PhoneDataRepository phoneDataRepository;
    private final UserRepository userRepository;
    private final PhoneDataMapper phoneDataMapper;

    public List<PhoneDataResponse> getByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return user.getPhoneDataEntities().stream()
                .map(phoneDataMapper::toDomain)
                .map(phoneDataMapper::toDto)
                .map(dto -> new PhoneDataResponse(dto.id(), dto.userId(), dto.phone()))
                .toList();
    }

    public void add(Long userId, PhoneDataRequest dto) {
        if (phoneDataRepository.existsByPhone(dto.phone())) {
            throw new IllegalArgumentException("Phone already exists");
        }

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        PhoneDataEntity entity = new PhoneDataEntity(null, user, dto.phone());
        phoneDataRepository.save(entity);
    }

    public void delete(Long id) {
        PhoneDataEntity entity = phoneDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Phone not found"));
        phoneDataRepository.delete(entity);
    }
}