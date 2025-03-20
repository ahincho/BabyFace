package com.nxtep.persistence.jpa.repositories;

import com.nxtep.persistence.jpa.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameJpaRepository extends JpaRepository<GameEntity, Long> {

}
