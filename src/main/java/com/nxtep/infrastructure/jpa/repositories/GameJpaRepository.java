package com.nxtep.infrastructure.jpa.repositories;

import com.nxtep.infrastructure.jpa.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface GameJpaRepository extends JpaRepository<GameEntity, Long> {
    boolean existsByUserIdAndCreatedAtAfter(Integer userId, LocalDateTime createdAt);
}
