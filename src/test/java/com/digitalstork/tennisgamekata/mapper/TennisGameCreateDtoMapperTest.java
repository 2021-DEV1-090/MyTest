package com.digitalstork.tennisgamekata.mapper;

import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.model.TennisGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TennisGameCreateDtoMapperTest {

    private final TennisGameCreateDtoMapper tennisGameCreateDtoMapper = new TennisGameCreateDtoMapper();

    @Test
    void should_map_TennisGameCreateDto_to_TennisGame_properly() {
        // Given
        TennisGameCreateDto tennisGameCreateDto = new TennisGameCreateDto();
        tennisGameCreateDto.setPlayerOne("Player 1");
        tennisGameCreateDto.setPlayerTwo("Player 2");

        // When
        TennisGame tennisGame = tennisGameCreateDtoMapper.apply(tennisGameCreateDto);

        // Test Assertions
        assertNotNull(tennisGame);
        assertEquals(tennisGameCreateDto.getPlayerOne(), tennisGame.getPlayerOne());
        assertEquals(tennisGameCreateDto.getPlayerTwo(), tennisGame.getPlayerTwo());
        assertEquals(0, tennisGame.getPlayerOneScore());
        assertEquals(0, tennisGame.getPlayerTwoScore());
        assertFalse(tennisGame.isGameEnded());
    }
}