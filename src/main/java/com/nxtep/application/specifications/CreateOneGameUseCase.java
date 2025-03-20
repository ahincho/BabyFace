package com.nxtep.application.specifications;

import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.models.Game;

public interface CreateOneGameUseCase {
    Game execute(Game game) throws UserNotFoundException;
}
