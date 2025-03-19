package com.nxtep.persistence.jpa.implementations;

import com.nxtep.domain.models.PageResult;
import com.nxtep.domain.models.Leader;
import com.nxtep.domain.repositories.LeaderPersistencePort;
import com.nxtep.persistence.jpa.entities.LeaderEntity;
import com.nxtep.persistence.jpa.mappers.CommonJpaMapper;
import com.nxtep.persistence.jpa.mappers.LeaderJpaMapper;
import com.nxtep.persistence.jpa.repositories.LeaderJpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class LeaderJpaPersistenceAdapter implements LeaderPersistencePort {
    private final LeaderJpaRepository leaderJpaRepository;
    public LeaderJpaPersistenceAdapter(LeaderJpaRepository leaderJpaRepository) {
        this.leaderJpaRepository = leaderJpaRepository;
    }
    @Override
    public PageResult<Leader> findLeaders(Integer page, Integer size) {
        Pageable pageRequest = CommonJpaMapper.domainPageToEntityPage(page, size);
        Page<LeaderEntity> leaderEntityPage = leaderJpaRepository.findAll(pageRequest);
        return LeaderJpaMapper.entityPageToDomainPage(leaderEntityPage);
    }
}
