package com.kiselev.userbalance.adapter.repository;

import com.kiselev.userbalance.adapter.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    @EntityGraph(attributePaths = {"emailDataEntities", "phoneDataEntities"})
    Page<UserEntity> findAll(Specification<UserEntity> spec, Pageable pageable);
}