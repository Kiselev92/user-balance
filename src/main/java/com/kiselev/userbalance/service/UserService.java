package com.kiselev.userbalance.service;

import com.kiselev.userbalance.adapter.elastic.UserSearchDocument;
import com.kiselev.userbalance.adapter.elastic.UserSearchRepository;
import com.kiselev.userbalance.adapter.sql.entity.EmailDataEntity;
import com.kiselev.userbalance.adapter.sql.entity.PhoneDataEntity;
import com.kiselev.userbalance.adapter.sql.entity.UserEntity;
import com.kiselev.userbalance.adapter.sql.mapper.UserMapper;
import com.kiselev.userbalance.adapter.sql.repository.EmailDataRepository;
import com.kiselev.userbalance.adapter.sql.repository.PhoneDataRepository;
import com.kiselev.userbalance.adapter.sql.repository.UserRepository;
import com.kiselev.userbalance.domain.User;
import com.kiselev.userbalance.port.rest.response.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EmailDataRepository emailDataRepository;
    private final PhoneDataRepository phoneDataRepository;
    private final UserSearchRepository userSearchRepository;
    private final UserSearchIndexer userSearchIndexer;
    private final UserMapper userMapper;

    @Cacheable(value = "userById", key = "#id")
    public UserResponse findById(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        log.info("Fetching user by id: {}", id);

        User domain = userMapper.toDomain(entity);
        return userMapper.toResponse(domain);
    }

    public List<UserResponse> search(String name,
                                     LocalDate dateOfBirth,
                                     String phone,
                                     String email,
                                     int offset,
                                     int limit) {
        log.info("Searching users: name={}, dob={}, phone={}, email={}, offset={}, limit={}",
                name, dateOfBirth, phone, email, offset, limit);

        boolean onlyName = name != null && !name.isBlank()
                && dateOfBirth == null
                && (email == null || email.isBlank())
                && (phone == null || phone.isBlank());

        if (onlyName) {
            log.info("Searching in Elasticsearch by name: {}", name);

            List<UserSearchDocument> searchResults = userSearchRepository.findByNameContainingIgnoreCase(name);
            List<Long> userIds = searchResults.stream()
                    .map(UserSearchDocument::getId)
                    .toList();

            return userRepository.findAllById(userIds).stream()
                    .map(userMapper::toDomain)
                    .map(userMapper::toResponse)
                    .toList();
        }

        Specification<UserEntity> spec = Specification.where(null);

        if (name != null && !name.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("name")), name.toLowerCase() + "%"));
        }

        if (dateOfBirth != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThan(root.get("dateOfBirth"), dateOfBirth));
        }

        if (phone != null && !phone.isBlank()) {
            spec = spec.and((root, query, cb) -> {
                Join<UserEntity, PhoneDataEntity> join = root.join("phoneDataEntities", JoinType.LEFT);
                return cb.equal(join.get("phone"), phone);
            });
        }

        if (email != null && !email.isBlank()) {
            spec = spec.and((root, query, cb) -> {
                Join<UserEntity, EmailDataEntity> join = root.join("emailDataEntities", JoinType.LEFT);
                return cb.equal(join.get("email"), email);
            });
        }

        Pageable pageable = PageRequest.of(offset, limit);

        return userRepository.findAll(spec, pageable).stream()
                .map(userMapper::toDomain)
                .map(userMapper::toResponse)
                .toList();
    }

    @Transactional
    @CacheEvict(value = "userById", key = "#userId")
    public void updateEmail(Long userId, List<String> newEmails) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<EmailDataEntity> conflicts = emailDataRepository.findAllByEmailIn(newEmails).stream()
                .filter(e -> !e.getUserEntity().getId().equals(userId))
                .toList();

        if (!conflicts.isEmpty()) {
            throw new IllegalArgumentException("Some emails are already in use: " +
                    conflicts.stream().map(EmailDataEntity::getEmail).collect(Collectors.joining(", ")));
        }

        user.getEmailDataEntities().clear();
        for (String email : newEmails) {
            user.getEmailDataEntities().add(
                    EmailDataEntity.builder()
                            .email(email)
                            .userEntity(user)
                            .build()
            );
        }

        userRepository.save(user);
        userSearchIndexer.indexUser(user);
    }

    @Transactional
    @CacheEvict(value = "userById", key = "#userId")
    public void updatePhone(Long userId, List<String> newPhones) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<PhoneDataEntity> conflicts = phoneDataRepository.findAllByPhoneIn(newPhones).stream()
                .filter(p -> !p.getUserEntity().getId().equals(userId))
                .toList();

        if (!conflicts.isEmpty()) {
            throw new IllegalArgumentException("Some phones are already in use: " +
                    conflicts.stream().map(PhoneDataEntity::getPhone).collect(Collectors.joining(", ")));
        }

        user.getPhoneDataEntities().clear();
        for (String phone : newPhones) {
            user.getPhoneDataEntities().add(
                    PhoneDataEntity.builder()
                            .phone(phone)
                            .userEntity(user)
                            .build()
            );
        }

        userRepository.save(user);
        userSearchIndexer.indexUser(user);
    }
}