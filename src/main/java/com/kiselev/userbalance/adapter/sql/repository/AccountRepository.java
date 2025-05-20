package com.kiselev.userbalance.adapter.sql.repository;

import com.kiselev.userbalance.adapter.sql.entity.AccountEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Query("select a from AccountEntity a where a.userEntity.id = :userId")
    Optional<AccountEntity> findByUserEntityId(@Param("userId") Long userId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from AccountEntity a where a.userEntity.id = :userId")
    Optional<AccountEntity> findForUpdateByUserId(@Param("userId") Long userId);
}