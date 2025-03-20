package com.nxtep.application.specifications;

import com.nxtep.domain.models.PageResult;
import com.nxtep.domain.models.Leader;

public interface FindLeadersUseCase {
    PageResult<Leader> execute(Integer page, Integer size);
}
