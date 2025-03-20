package com.nxtep.persistence.jpa.repositories;

import com.nxtep.persistence.jpa.entities.LeaderEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderJpaRepository extends JpaRepository<LeaderEntity, Integer> {

}
