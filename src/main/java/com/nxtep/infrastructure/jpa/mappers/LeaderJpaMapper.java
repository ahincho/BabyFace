package com.nxtep.persistence.jpa.mappers;

import com.nxtep.domain.models.PageResult;
import com.nxtep.domain.models.Leader;
import com.nxtep.persistence.jpa.entities.LeaderEntity;

import org.springframework.data.domain.Page;

public class LeaderJpaMapper {
    private LeaderJpaMapper() {}
    public static Leader entityToDomain(LeaderEntity leaderEntity) {
        return Leader.builder()
            .id(leaderEntity.getId())
            .rank(leaderEntity.getRank())
            .name(leaderEntity.getName())
            .hits(leaderEntity.getHits())
            .build();
    }
    public static PageResult<Leader> entityPageToDomainPage(Page<LeaderEntity> leaderEntityPage) {
        return PageResult.<Leader>builder()
            .totalItems(leaderEntityPage.getTotalElements())
            .totalPages(leaderEntityPage.getTotalPages())
            .currentPage(leaderEntityPage.getNumber())
            .pageSize(leaderEntityPage.getSize())
            .hasNextPage(leaderEntityPage.hasNext())
            .items(leaderEntityPage.getContent().stream().map(LeaderJpaMapper::entityToDomain).toList())
            .build();
    }
}
