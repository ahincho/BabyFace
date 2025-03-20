package com.nxtep.application.specifications;

import com.nxtep.domain.exceptions.LeaderNotFoundException;
import com.nxtep.domain.models.Leader;

public interface FindOneLeaderUseCase {
    Leader execute(Integer leaderId) throws LeaderNotFoundException;
}
