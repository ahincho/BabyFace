package com.nxtep.presentations.rest.mappers;

import com.nxtep.domain.models.Game;
import com.nxtep.domain.models.User;
import com.nxtep.presentations.rest.dtos.GameCreateRequest;
import com.nxtep.presentations.rest.dtos.GameResponse;

public class GameRestMapper {
    private GameRestMapper() {}
    public static Game createRequestToDomain(GameCreateRequest gameCreateRequest) {
        return Game.builder()
            .user(
                User.builder()
                    .id(gameCreateRequest.getUserId())
                    .build()
            )
            .points(gameCreateRequest.getPoints())
            .build();
    }
    public static GameResponse domainToResponse(Game game) {
        return GameResponse.builder()
            .id(game.getId())
            .userId(game.getUser().getId())
            .points(game.getPoints())
            .build();
    }
}
