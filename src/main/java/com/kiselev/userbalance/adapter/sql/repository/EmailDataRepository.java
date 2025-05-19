package com.kiselev.userbalance.adapter.sql.repository;

import com.kiselev.userbalance.adapter.sql.entity.EmailDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmailDataRepository extends JpaRepository<EmailDataEntity, Long> {

      List<EmailDataEntity> findAllByEmailIn(Collection<String> emails);

      Optional<EmailDataEntity> findByEmail(String email);
      boolean existsByEmail(String email);
}