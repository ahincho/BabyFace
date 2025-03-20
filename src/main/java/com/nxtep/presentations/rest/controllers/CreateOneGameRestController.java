package com.nxtep.presentations.rest.controllers;

import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.models.Game;
import com.nxtep.presentations.rest.dtos.GameCreateRequest;
import com.nxtep.presentations.rest.dtos.GameResponse;
import com.nxtep.presentations.rest.mappers.GameRestMapper;
import com.nxtep.application.specifications.CreateOneGameUseCase;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/games")
public class CreateOneGameRestController {
    private final CreateOneGameUseCase createOneGameUseCase;
    public CreateOneGameRestController(CreateOneGameUseCase createOneGameUseCase) {
        this.createOneGameUseCase = createOneGameUseCase;
    }
    @PostMapping
    public ResponseEntity<GameResponse> createOneGame(@RequestBody @Valid GameCreateRequest gameCreateRequest) throws UserNotFoundException {
        Game game = GameRestMapper.createRequestToDomain(gameCreateRequest);
        Game savedGame = this.createOneGameUseCase.execute(game);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{gameId}").buildAndExpand(savedGame.getId()).toUri();
        return ResponseEntity.created(uri).body(GameRestMapper.domainToResponse(savedGame));
    }
}
