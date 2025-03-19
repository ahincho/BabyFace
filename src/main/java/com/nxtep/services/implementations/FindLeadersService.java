package com.nxtep.services.implementations;

import com.nxtep.domain.models.PageResult;
import com.nxtep.domain.models.Leader;
import com.nxtep.domain.repositories.LeaderPersistencePort;
import com.nxtep.services.specifications.FindLeadersUseCase;

import org.springframework.stereotype.Service;

@Service
public class FindLeadersService implements FindLeadersUseCase {
    private final LeaderPersistencePort leaderPersistencePort;
    public FindLeadersService(LeaderPersistencePort leaderPersistencePort) {
        this.leaderPersistencePort = leaderPersistencePort;
    }
    @Override
    public PageResult<Leader> execute(Integer page, Integer size) {
        return this.leaderPersistencePort.findLeaders(page, size);
    }
}
