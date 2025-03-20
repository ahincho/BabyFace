package com.nxtep.services.implementations;

import com.nxtep.domain.exceptions.LeaderNotFoundException;
import com.nxtep.domain.models.Leader;
import com.nxtep.domain.repositories.LeaderPersistencePort;
import com.nxtep.services.specifications.FindOneLeaderUseCase;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindOneLeaderService implements FindOneLeaderUseCase {
    private final LeaderPersistencePort leaderPersistencePort;
    public FindOneLeaderService(LeaderPersistencePort leaderPersistencePort) {
        this.leaderPersistencePort = leaderPersistencePort;
    }
    @Override
    public Leader execute(Integer leaderId) throws LeaderNotFoundException {
        Optional<Leader> optionalLeader = this.leaderPersistencePort.findLeaderById(leaderId);
        if (optionalLeader.isEmpty()) {
            throw new LeaderNotFoundException(String.format("Leader with id '%s' not found", leaderId));
        }
        return optionalLeader.get();
    }
}
