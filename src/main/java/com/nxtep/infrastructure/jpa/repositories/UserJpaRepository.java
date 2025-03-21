package com.nxtep.infrastructure.jpa.repositories;

import com.nxtep.infrastructure.jpa.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByPhone(String phone);
}
