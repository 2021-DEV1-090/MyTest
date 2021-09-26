package com.digitalstork.tennisgamekata.service;

import com.digitalstork.tennisgamekata.dto.ScoreDto;
import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.dto.TennisGameDto;
import com.digitalstork.tennisgamekata.exception.PlayerNotFoundException;
import com.digitalstork.tennisgamekata.exception.ResourceNotFoundException;
import com.digitalstork.tennisgamekata.exception.UnauthorizedActionException;
import com.digitalstork.tennisgamekata.mapper.TennisGameCreateDtoMapper;
import com.digitalstork.tennisgamekata.mapper.TennisGameMapper;
import com.digitalstork.tennisgamekata.model.TennisGame;
import com.digitalstork.tennisgamekata.repository.TennisGameRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TennisGameServiceImpl implements TennisGameService {

    private static final Logger log = LoggerFactory.getLogger(TennisGameServiceImpl.class);

    private final TennisGameRepository tennisGameRepository;
    private final TennisGameCreateDtoMapper tennisGameCreateDtoMapper = new TennisGameCreateDtoMapper();
    private final TennisGameMapper tennisGameMapper = new TennisGameMapper();

    @Override
    public TennisGameDto createGame(TennisGameCreateDto tennisGameCreateDto) {
        TennisGame tennisGame = tennisGameCreateDtoMapper.apply(tennisGameCreateDto);
        TennisGame savedTennisGame = tennisGameRepository.save(tennisGame);

        log.info("New Tennis Game between {} and {}", savedTennisGame.getPlayerOne(), savedTennisGame.getPlayerTwo());

        return tennisGameMapper.apply(savedTennisGame);
    }

    @Override
    public TennisGameDto score(Long gameId, ScoreDto scoreDto) {
        TennisGame tennisGame = tennisGameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Tennis game not found with id: " + gameId));

        if (tennisGame.isGameEnded())
            throw new UnauthorizedActionException("You cannot score on a game that is already finished!");

        if (scoreDto.getScorer().equals(tennisGame.getPlayerOne())) {
            tennisGame.setPlayerOneScore(tennisGame.getPlayerOneScore() + 1);
        } else if (scoreDto.getScorer().equals(tennisGame.getPlayerTwo())) {
            tennisGame.setPlayerTwoScore(tennisGame.getPlayerTwoScore() + 1);
        } else {
            throw new PlayerNotFoundException("Player not found with name: " + scoreDto.getScorer());
        }

        if (tennisGame.getPlayerOneScore() >= 4 && tennisGame.getPlayerOneScore() >= tennisGame.getPlayerTwoScore() + 2) {
            log.info("{} Won the game", tennisGame.getPlayerOne());
            tennisGame.setGameEnded(true);
        } else if (tennisGame.getPlayerTwoScore() >= 4 && tennisGame.getPlayerTwoScore() > tennisGame.getPlayerOneScore() + 2) {
            log.info("{} Won the game", tennisGame.getPlayerTwo());
            tennisGame.setGameEnded(true);
        }

        TennisGame savedTennisGame = tennisGameRepository.save(tennisGame);

        return tennisGameMapper.apply(savedTennisGame);
    }
}
