package com.nxtep.persistence.jpa.implementations;

import com.nxtep.domain.models.PageResult;
import com.nxtep.domain.models.User;
import com.nxtep.domain.repositories.UserPersistencePort;
import com.nxtep.persistence.jpa.entities.UserEntity;
import com.nxtep.persistence.jpa.mappers.UserJpaMapper;
import com.nxtep.persistence.jpa.repositories.UserJpaRepository;

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
}
