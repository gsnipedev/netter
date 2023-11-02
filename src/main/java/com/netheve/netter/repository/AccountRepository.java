package com.netheve.netter.repository;

import com.netheve.netter.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByUsername(String username);
    Optional<AccountEntity> findByAuthToken(String authToken);
    boolean existsByUsername(String username);
}
