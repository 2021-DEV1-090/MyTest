package com.digitalstork.tennisgamekata.mapper;

import com.digitalstork.tennisgamekata.dto.TennisGameDto;
import com.digitalstork.tennisgamekata.model.TennisGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TennisGameMapperTest {

    private final TennisGameMapper tennisGameMapper = new TennisGameMapper();

    @Test
    void should_map_TennisGame_to_TennisGameDto_properly() {
        // Given
        TennisGame tennisGame = new TennisGame();
        tennisGame.setPlayerOne("Player 1");
        tennisGame.setPlayerTwo("Player 2");
        tennisGame.setPlayerOneScore(1);
        tennisGame.setPlayerTwoScore(3);

        // When
        TennisGameDto tennisGameDto = tennisGameMapper.apply(tennisGame);

        // Test Assertions
        assertNotNull(tennisGameDto);
        assertEquals(tennisGame.getPlayerOne(), tennisGameDto.getPlayerOne());
        assertEquals(tennisGame.getPlayerTwo(), tennisGameDto.getPlayerTwo());
        assertEquals("15", tennisGameDto.getPlayerOneScore());
        assertEquals("40", tennisGameDto.getPlayerTwoScore());
        assertFalse(tennisGame.isGameEnded());
    }
}