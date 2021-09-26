package com.digitalstork.tennisgamekata.controller;

import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.dto.TennisGameDto;
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
import static org.mockito.ArgumentMatchers.any;
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
}