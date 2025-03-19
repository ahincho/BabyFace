package com.nxtep.domain.repositories;

import com.nxtep.domain.models.PageResult;
import com.nxtep.domain.models.Leader;

public interface LeaderPersistencePort {
    PageResult<Leader> findLeaders(Integer page, Integer size);
}
