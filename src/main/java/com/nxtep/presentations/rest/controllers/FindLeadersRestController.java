package com.nxtep.presentations.rest.controllers;

import com.nxtep.domain.models.PageResult;
import com.nxtep.domain.models.Leader;
import com.nxtep.presentations.rest.dtos.LeaderResponse;
import com.nxtep.presentations.rest.dtos.PageRequest;
import com.nxtep.presentations.rest.dtos.PageResponse;
import com.nxtep.presentations.rest.mappers.LeaderRestMapper;
import com.nxtep.application.specifications.FindLeadersUseCase;

import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/leaders")
public class FindLeadersRestController {
    private final FindLeadersUseCase findLeadersUseCase;
    public FindLeadersRestController(FindLeadersUseCase findLeadersUseCase) {
        this.findLeadersUseCase = findLeadersUseCase;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<LeaderResponse>> findLeaders(
        @ModelAttribute @Valid PageRequest pageRequest
    ) {
        PageResult<Leader> leaderPageResult = this.findLeadersUseCase.execute(pageRequest.getPage(), pageRequest.getSize());
        if (leaderPageResult.getItems().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(LeaderRestMapper.domainToPageResponse(leaderPageResult));
    }
}
