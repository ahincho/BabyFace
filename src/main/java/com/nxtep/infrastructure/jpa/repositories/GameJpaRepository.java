package com.nxtep.infrastructure.jpa.repositories;

import com.nxtep.infrastructure.jpa.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameJpaRepository extends JpaRepository<GameEntity, Long> {
    boolean existsByUserId(Integer userId);
}
