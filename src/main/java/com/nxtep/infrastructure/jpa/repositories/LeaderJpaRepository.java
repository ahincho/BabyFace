package com.nxtep.infrastructure.jpa.repositories;

import com.nxtep.infrastructure.jpa.entities.LeaderEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderJpaRepository extends JpaRepository<LeaderEntity, Integer> {

}
