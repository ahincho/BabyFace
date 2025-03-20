package com.nxtep.infrastructure.jpa.mappers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class CommonJpaMapper {
    private CommonJpaMapper() {}
    public static Pageable domainPageToEntityPage(Integer page, Integer size) {
        return PageRequest.of(page, size);
    }
}
