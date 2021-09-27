package com.digitalstork.tennisgamekata.controller;

import com.digitalstork.tennisgamekata.dto.ScoreDto;
import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.dto.TennisGameDto;
import com.digitalstork.tennisgamekata.enums.GameStatus;
import com.digitalstork.tennisgamekata.exception.*;
import com.digitalstork.tennisgamekata.service.TennisGameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TennisGameControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private TennisGameService tennisGameService;

    @Test
    void should_return_ok_and_create_new_game_when_calling_new_game_endpoint() {
        // Given
        String url = "http://localhost:" + port + "/api/tennis-game/new";
        TennisGameCreateDto tennisGameCreateDto = new TennisGameCreateDto();
        tennisGameCreateDto.setPlayerOne("Player 1");
        tennisGameCreateDto.setPlayerTwo("Player 2");

        TennisGameDto tennisGameDto = new TennisGameDto();
        tennisGameDto.setId(1L);
        tennisGameDto.setPlayerOne(tennisGameCreateDto.getPlayerOne());
        tennisGameDto.setPlayerTwo(tennisGameCreateDto.getPlayerTwo());

        // When
        when(tennisGameService.createGame(any(TennisGameCreateDto.class)))
                .thenReturn(tennisGameDto);

        ResponseEntity<TennisGameDto> response = restTemplate.postForEntity(url, tennisGameCreateDto, TennisGameDto.class);

        // Test Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(tennisGameDto.getPlayerOne(), response.getBody().getPlayerOne());
        assertEquals(tennisGameDto.getPlayerTwo(), response.getBody().getPlayerTwo());
        assertFalse(response.getBody().isGameEnded());
    }

    @Test
    void should_throw_data_validation_errors_when_blank_values_provided() {
        // Given
        String url = "http://localhost:" + port + "/api/tennis-game/new";
        TennisGameCreateDto tennisGameCreateDto = new TennisGameCreateDto();
        tennisGameCreateDto.setPlayerOne("");
        tennisGameCreateDto.setPlayerTwo(null);

        // When
        ResponseEntity<ApiError> response = restTemplate.postForEntity(url, tennisGameCreateDto, ApiError.class);

        // Test Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorCode.DATA_VALIDATION.name(), response.getBody().getErrorCode());
        assertTrue(response.getBody().getSubErrors().contains("playerTwo field is required!"));
    }

    @Test
    void should_score_and_return_ok_with_updated_game_details() {
        // Given
        String url = "http://localhost:" + port + "/api/tennis-game/";
        Long gameId = 1L;
        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setScorer("Player 1");

        TennisGameDto tennisGameDto = new TennisGameDto();
        tennisGameDto.setId(gameId);
        tennisGameDto.setPlayerOne("Player 1");
        tennisGameDto.setPlayerTwo("Player 2");
        tennisGameDto.setPlayerOneScore("30");
        tennisGameDto.setPlayerTwoScore("0");
        tennisGameDto.setStatus(GameStatus.STARTED);

        // When
        when(tennisGameService.score(eq(gameId), any(ScoreDto.class)))
                .thenReturn(tennisGameDto);

        ResponseEntity<TennisGameDto> response = restTemplate.postForEntity(url + gameId + "/score", scoreDto, TennisGameDto.class);

        // Test Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(tennisGameDto.getPlayerOne(), response.getBody().getPlayerOne());
        assertEquals(tennisGameDto.getPlayerOneScore(), response.getBody().getPlayerOneScore());
        assertEquals(tennisGameDto.getPlayerTwo(), response.getBody().getPlayerTwo());
        assertEquals(tennisGameDto.getPlayerTwoScore(), response.getBody().getPlayerTwoScore());
        assertEquals(tennisGameDto.getStatus(), response.getBody().getStatus());
        assertFalse(response.getBody().isGameEnded());
    }

    @Test
    void should_handle_UnauthorizedActionException() {
        // Given
        String url = "http://localhost:" + port + "/api/tennis-game/";
        Long gameId = 1L;
        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setScorer("Player 1");

        // When
        when(tennisGameService.score(eq(gameId), any(ScoreDto.class)))
                .thenThrow(UnauthorizedActionException.class);

        ResponseEntity<ApiError> response = restTemplate.postForEntity(url + gameId + "/score", scoreDto, ApiError.class);

        // Test Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorCode.UNAUTHORIZED_ACTION.name(), response.getBody().getErrorCode());
    }

    @Test
    void should_handle_ResourceNotFoundException() {
        // Given
        String url = "http://localhost:" + port + "/api/tennis-game/";
        Long gameId = 1L;
        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setScorer("Player 2");

        // When
        when(tennisGameService.score(eq(gameId), any(ScoreDto.class)))
                .thenThrow(ResourceNotFoundException.class);

        ResponseEntity<ApiError> response = restTemplate.postForEntity(url + gameId + "/score", scoreDto, ApiError.class);

        // Test Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorCode.RESOURCE_NOT_FOUND.name(), response.getBody().getErrorCode());
    }

    @Test
    void should_handle_PlayerNotFoundException() {
        // Given
        String url = "http://localhost:" + port + "/api/tennis-game/";
        Long gameId = 1L;
        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setScorer("Player 45");

        // When
        when(tennisGameService.score(eq(gameId), any(ScoreDto.class)))
                .thenThrow(PlayerNotFoundException.class);

        ResponseEntity<ApiError> response = restTemplate.postForEntity(url + gameId + "/score", scoreDto, ApiError.class);

        // Test Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorCode.PLAYER_NOT_FOUND.name(), response.getBody().getErrorCode());
    }
}