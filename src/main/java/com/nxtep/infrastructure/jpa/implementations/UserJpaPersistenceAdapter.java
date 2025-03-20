package com.nxtep.infrastructure.jpa.implementations;

import com.nxtep.domain.models.PageResult;
import com.nxtep.domain.models.User;
import com.nxtep.domain.repositories.UserPersistencePort;
import com.nxtep.infrastructure.jpa.entities.UserEntity;
import com.nxtep.infrastructure.jpa.mappers.UserJpaMapper;
import com.nxtep.infrastructure.jpa.repositories.UserJpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class UserJpaPersistenceAdapter implements UserPersistencePort {
    private final UserJpaRepository userJpaRepository;
    public UserJpaPersistenceAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }
    @Override
    @Transactional
    public User createOneUser(User user) {
        UserEntity userEntity = UserJpaMapper.domainToEntity(user);
        UserEntity savedUserEntity = this.userJpaRepository.save(userEntity);
        return UserJpaMapper.entityToDomain(savedUserEntity);
    }
    @Override
    public PageResult<User> findUsers(Integer page, Integer size) {
        return null;
    }

    @Override
    public Optional<User> findOneUser(Integer userId) {
        return this.userJpaRepository.findById(userId).map(UserJpaMapper::entityToDomain);
    }
    @Override
    public boolean existsOneUser(Integer userId) {
        return this.userJpaRepository.existsById(userId);
    }
    @Override
    public boolean existsOneUserByUsername(String username) {
        return this.userJpaRepository.existsByUsername(username);
    }
    @Override
    @Transactional
    public User updateOneUser(User user) {
        UserEntity userEntity = this.userJpaRepository.save(UserJpaMapper.domainToEntity(user));
        return UserJpaMapper.entityToDomain(userEntity);
    }
}
