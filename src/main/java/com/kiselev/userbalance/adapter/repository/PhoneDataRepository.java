package com.kiselev.userbalance.adapter.repository;

import com.kiselev.userbalance.adapter.entity.PhoneDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneDataRepository extends JpaRepository<PhoneDataEntity, Long> {

    List<PhoneDataEntity> findAllByPhoneIn(Collection<String> phones);

    Optional<PhoneDataEntity> findByPhone(String email);
    boolean existsByPhone(String email);
}