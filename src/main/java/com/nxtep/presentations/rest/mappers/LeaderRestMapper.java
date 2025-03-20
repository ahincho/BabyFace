package com.nxtep.presentations.rest.mappers;

import com.nxtep.domain.models.PageResult;
import com.nxtep.domain.models.Leader;
import com.nxtep.presentations.rest.dtos.LeaderResponse;
import com.nxtep.presentations.rest.dtos.PageResponse;

public class LeaderRestMapper {
    private LeaderRestMapper() {}
    public static LeaderResponse domainToResponse(Leader leader) {
        return LeaderResponse.builder()
            .id(leader.getId())
            .rank(leader.getRank())
            .name(leader.getName())
            .points(leader.getPoints() * GameRestMapper.POINTS_PER_HIT)
            .build();
    }
    public static PageResponse<LeaderResponse> domainToPageResponse(PageResult<Leader> leaderPageResult) {
        return PageResponse.<LeaderResponse>builder()
            .totalItems(leaderPageResult.getTotalItems())
            .totalPages(leaderPageResult.getTotalPages())
            .currentPage(leaderPageResult.getCurrentPage())
            .pageSize(leaderPageResult.getPageSize())
            .hasNextPage(leaderPageResult.getHasNextPage())
            .items(leaderPageResult.getItems().stream().map(LeaderRestMapper::domainToResponse).toList())
            .build();
    }
}
