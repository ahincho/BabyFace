package com.nxtep.persistence.jpa.repositories;

import com.nxtep.persistence.jpa.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUsername(String username);
}
