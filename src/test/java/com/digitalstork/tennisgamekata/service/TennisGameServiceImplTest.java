package com.digitalstork.tennisgamekata.service;

import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.dto.TennisGameDto;
import com.digitalstork.tennisgamekata.model.TennisGame;
import com.digitalstork.tennisgamekata.repository.TennisGameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

}