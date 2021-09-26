package com.digitalstork.tennisgamekata.service;

import com.digitalstork.tennisgamekata.dto.ScoreDto;
import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.dto.TennisGameDto;
import com.digitalstork.tennisgamekata.enums.GameStatus;
import com.digitalstork.tennisgamekata.exception.PlayerNotFoundException;
import com.digitalstork.tennisgamekata.exception.ResourceNotFoundException;
import com.digitalstork.tennisgamekata.exception.UnauthorizedActionException;
import com.digitalstork.tennisgamekata.model.TennisGame;
import com.digitalstork.tennisgamekata.repository.TennisGameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TennisGameServiceImplTest {

    @InjectMocks
    private TennisGameServiceImpl tennisGameService;

    @Mock
    private TennisGameRepository tennisGameRepository;

    @Test
    void should_create_new_tennis_game_properly() {
        // Given
        TennisGameCreateDto tennisGameCreateDto = new TennisGameCreateDto();
        tennisGameCreateDto.setPlayerOne("Player 1");
        tennisGameCreateDto.setPlayerTwo("Player 2");

        TennisGame tennisGame = new TennisGame();
        tennisGame.setId(1L);
        tennisGame.setPlayerOne("Player 1");
        tennisGame.setPlayerTwo("Player 2");

        // When
        when(tennisGameRepository.save(any(TennisGame.class)))
                .thenReturn(tennisGame);
        TennisGameDto tennisGameDto = tennisGameService.createGame(tennisGameCreateDto);

        // Test Assertions
        assertNotNull(tennisGameDto);
        assertNotNull(tennisGameDto.getId());
        assertFalse(tennisGame.isGameEnded());
        assertEquals(tennisGame.getPlayerOne(), tennisGameDto.getPlayerOne());
        assertEquals(tennisGame.getPlayerTwo(), tennisGameDto.getPlayerTwo());
    }

    @Test
    void should_score_and_return_updated_game_details() {
        // Given
        Long gameId = 1L;

        TennisGame tennisGame = new TennisGame();
        tennisGame.setId(gameId);
        tennisGame.setPlayerOne("Player 1");
        tennisGame.setPlayerTwo("Player 2");
        tennisGame.setPlayerOneScore(1);
        tennisGame.setPlayerTwoScore(2);

        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setScorer("Player 1");

        // When
        when(tennisGameRepository.findById(gameId))
                .thenReturn(Optional.of(tennisGame));
        when(tennisGameRepository.save(any(TennisGame.class)))
                .thenReturn(tennisGame);
        TennisGameDto tennisGameDto = tennisGameService.score(gameId, scoreDto);

        // Test Assertions
        assertNotNull(tennisGameDto);
        assertEquals("30", tennisGameDto.getPlayerOneScore());
        assertEquals("30", tennisGameDto.getPlayerTwoScore());
        assertFalse(tennisGame.isGameEnded());
    }

    @Test
    void should_throw_ResourceNotFoundException_when_gameId_is_wrong() {
        // Given
        Long wrongGameId = 2L;

        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setScorer("Player 1");

        // When
        when(tennisGameRepository.findById(wrongGameId))
                .thenReturn(Optional.empty());

        // Test Assertions
        ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class, () -> {
            TennisGameDto tennisGameDto = tennisGameService.score(wrongGameId, scoreDto);
        });

        assertEquals("Tennis game not found with id: " + wrongGameId, resourceNotFoundException.getMessage());
    }

    @Test
    void should_throw_PlayerNotFoundException_when_scorer_is_wrong() {
        // Given
        Long gameId = 1L;

        TennisGame tennisGame = new TennisGame();
        tennisGame.setId(gameId);
        tennisGame.setPlayerOne("Player 1");
        tennisGame.setPlayerTwo("Player 2");
        tennisGame.setPlayerOneScore(1);
        tennisGame.setPlayerTwoScore(2);

        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setScorer("Player 10");

        // When
        when(tennisGameRepository.findById(gameId))
                .thenReturn(Optional.of(tennisGame));

        // Test Assertions
        PlayerNotFoundException playerNotFoundException = assertThrows(PlayerNotFoundException.class, () -> {
            TennisGameDto tennisGameDto = tennisGameService.score(gameId, scoreDto);
        });

        assertEquals("Player not found with name: " + scoreDto.getScorer(), playerNotFoundException.getMessage());
    }

    @Test
    void should_win_and_end_game_if_player_have_forty_and_win_ball() {
        // Given
        Long gameId = 1L;

        TennisGame tennisGame = new TennisGame();
        tennisGame.setId(gameId);
        tennisGame.setPlayerOne("Player 1");
        tennisGame.setPlayerTwo("Player 2");
        tennisGame.setPlayerOneScore(3);
        tennisGame.setPlayerTwoScore(2);

        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setScorer("Player 1");

        // When
        when(tennisGameRepository.findById(gameId))
                .thenReturn(Optional.of(tennisGame));
        when(tennisGameRepository.save(any(TennisGame.class)))
                .thenReturn(tennisGame);
        TennisGameDto tennisGameDto = tennisGameService.score(gameId, scoreDto);

        // Test Assertions
        assertNotNull(tennisGameDto);
        assertEquals("Won", tennisGameDto.getPlayerOneScore());
        assertEquals("30", tennisGameDto.getPlayerTwoScore());
        assertTrue(tennisGame.isGameEnded());
    }

    @Test
    void should_throw_UnauthorizedActionException_when_attempt_to_score_on_ended_game() {
        // Given
        Long gameId = 1L;

        TennisGame tennisGame = new TennisGame();
        tennisGame.setId(gameId);
        tennisGame.setPlayerOne("Player 1");
        tennisGame.setPlayerTwo("Player 2");
        tennisGame.setPlayerOneScore(6);
        tennisGame.setPlayerTwoScore(4);
        tennisGame.setGameEnded(true);

        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setScorer("Player 1");

        // When
        when(tennisGameRepository.findById(gameId))
                .thenReturn(Optional.of(tennisGame));

        // Test Assertions
        UnauthorizedActionException unauthorizedActionException = assertThrows(UnauthorizedActionException.class, () -> {
            TennisGameDto tennisGameDto = tennisGameService.score(gameId, scoreDto);
        });

        assertEquals("You cannot score on a game that is already finished!", unauthorizedActionException.getMessage());
    }

    @Test
    void should_be_in_deuce_if_both_players_have_forty() {
        // Given
        Long gameId = 1L;

        TennisGame tennisGame = new TennisGame();
        tennisGame.setId(gameId);
        tennisGame.setPlayerOne("Player 1");
        tennisGame.setPlayerTwo("Player 2");
        tennisGame.setPlayerOneScore(3);
        tennisGame.setPlayerTwoScore(2);
        tennisGame.setStatus(GameStatus.IN_PROGRESS);

        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setScorer("Player 2");

        // When
        when(tennisGameRepository.findById(gameId))
                .thenReturn(Optional.of(tennisGame));
        when(tennisGameRepository.save(any(TennisGame.class)))
                .thenReturn(tennisGame);
        TennisGameDto tennisGameDto = tennisGameService.score(gameId, scoreDto);

        // Test Assertions
        assertNotNull(tennisGameDto);
        assertEquals("40", tennisGameDto.getPlayerOneScore());
        assertEquals("40", tennisGameDto.getPlayerTwoScore());
        assertEquals(GameStatus.DEUCE, tennisGameDto.getStatus());
        assertFalse(tennisGame.isGameEnded());
    }
}