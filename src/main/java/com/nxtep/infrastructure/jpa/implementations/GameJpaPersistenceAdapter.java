package com.nxtep.infrastructure.jpa.implementations;

import com.nxtep.domain.models.Game;
import com.nxtep.domain.repositories.GamePersistencePort;
import com.nxtep.infrastructure.jpa.entities.GameEntity;
import com.nxtep.infrastructure.jpa.mappers.GameJpaMapper;
import com.nxtep.infrastructure.jpa.repositories.GameJpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GameJpaPersistenceAdapter implements GamePersistencePort {
    private final GameJpaRepository gameJpaRepository;
    public GameJpaPersistenceAdapter(GameJpaRepository gameJpaRepository) {
        this.gameJpaRepository = gameJpaRepository;
    }
    @Override
    @Transactional
    public Game createOneGame(Game game) {
        GameEntity gameEntity = GameJpaMapper.domainToEntity(game);
        GameEntity gameEntitySaved = this.gameJpaRepository.save(gameEntity);
        return GameJpaMapper.entityToEntity(gameEntitySaved);
    }
    @Override
    public boolean existsOneGameByUserId(Integer userId) {
        return this.gameJpaRepository.existsByUserId(userId);
    }
}
