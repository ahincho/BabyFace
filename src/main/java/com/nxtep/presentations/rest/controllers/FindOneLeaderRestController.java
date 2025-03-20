package com.nxtep.presentations.rest.controllers;

import com.nxtep.domain.exceptions.LeaderNotFoundException;
import com.nxtep.domain.models.Leader;
import com.nxtep.presentations.rest.dtos.LeaderResponse;
import com.nxtep.presentations.rest.mappers.LeaderRestMapper;
import com.nxtep.services.specifications.FindOneLeaderUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/leaders")
public class FindOneLeaderRestController {
    private final FindOneLeaderUseCase findOneLeaderUseCase;
    public FindOneLeaderRestController(FindOneLeaderUseCase findOneLeaderUseCase) {
        this.findOneLeaderUseCase = findOneLeaderUseCase;
    }
    @GetMapping("/{leaderId}")
    public ResponseEntity<LeaderResponse> findOneLeader(
        @PathVariable Integer leaderId
    ) throws LeaderNotFoundException {
        Leader leader = this.findOneLeaderUseCase.execute(leaderId);
        LeaderResponse leaderResponse = LeaderRestMapper.domainToResponse(leader);
        return ResponseEntity.ok(leaderResponse);
    }
}
