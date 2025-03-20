package com.nxtep.infrastructure.jpa.implementations;

import com.nxtep.domain.models.PageResult;
import com.nxtep.domain.models.Leader;
import com.nxtep.domain.repositories.LeaderPersistencePort;
import com.nxtep.infrastructure.jpa.entities.LeaderEntity;
import com.nxtep.infrastructure.jpa.mappers.CommonJpaMapper;
import com.nxtep.infrastructure.jpa.mappers.LeaderJpaMapper;
import com.nxtep.infrastructure.jpa.repositories.LeaderJpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LeaderJpaPersistenceAdapter implements LeaderPersistencePort {
    private final LeaderJpaRepository leaderJpaRepository;
    public LeaderJpaPersistenceAdapter(LeaderJpaRepository leaderJpaRepository) {
        this.leaderJpaRepository = leaderJpaRepository;
    }
    @Override
    public PageResult<Leader> findLeaders(Integer page, Integer size) {
        Pageable pageRequest = CommonJpaMapper.domainPageToEntityPage(page, size);
        Page<LeaderEntity> leaderEntityPage = this.leaderJpaRepository.findAll(pageRequest);
        return LeaderJpaMapper.entityPageToDomainPage(leaderEntityPage);
    }
    @Override
    public Optional<Leader> findLeaderById(Integer leaderId) {
        return this.leaderJpaRepository.findById(leaderId).map(LeaderJpaMapper::entityToDomain);
    }
}
