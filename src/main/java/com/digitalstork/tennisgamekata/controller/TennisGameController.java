package com.digitalstork.tennisgamekata.controller;

import com.digitalstork.tennisgamekata.dto.ScoreDto;
import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.dto.TennisGameDto;
import com.digitalstork.tennisgamekata.service.TennisGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Tag(name = "Tennis Game Controller", description = "Use this APIs to create new tennis game game and play")
@RequestMapping("/api/tennis-game")
@AllArgsConstructor
public class TennisGameController {

    private final TennisGameService tennisGameService;

    @Operation(summary = "API for creating new Tennis game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the created tennis game",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TennisGameDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid data",
                    content = @Content)
    })
    @PostMapping("/new")
    public ResponseEntity<TennisGameDto> createNewGame(@RequestBody @Valid TennisGameCreateDto tennisGameCreateDto) {
        return ResponseEntity.ok(tennisGameService.createGame(tennisGameCreateDto));
    }

    @Operation(summary = "API for scoring a point for a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the updated tennis game score and details",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TennisGameDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid data or unauthorized action",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Game not found or player not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Invalid game score combination",
                    content = @Content)
    })
    @PostMapping("/{gameId}/score")
    public ResponseEntity<TennisGameDto> score(@PathVariable Long gameId, @RequestBody @Valid ScoreDto scoreDto) {
        return ResponseEntity.ok(tennisGameService.score(gameId, scoreDto));
    }

    @Operation(summary = "API for quit a tennis game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the updated tennis game score and details",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TennisGameDto.class))}),
            @ApiResponse(responseCode = "400", description = "unauthorized action",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Game not found",
                    content = @Content)
    })
    @PostMapping("/{gameId}/quit")
    public ResponseEntity<TennisGameDto> quitGame(@PathVariable Long gameId) {
        return ResponseEntity.ok(tennisGameService.endGame(gameId));
    }
}
