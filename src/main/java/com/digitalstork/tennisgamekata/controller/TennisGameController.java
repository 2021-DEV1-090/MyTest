package com.digitalstork.tennisgamekata.controller;

import com.digitalstork.tennisgamekata.dto.ScoreDto;
import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.dto.TennisGameDto;
import com.digitalstork.tennisgamekata.service.TennisGameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tennis-game")
@AllArgsConstructor
public class TennisGameController {

    private final TennisGameService tennisGameService;

    @PostMapping("/new")
    public ResponseEntity<TennisGameDto> createNewGame(@RequestBody @Valid TennisGameCreateDto tennisGameCreateDto) {
        return ResponseEntity.ok(tennisGameService.createGame(tennisGameCreateDto));
    }

    @PostMapping("/{gameId}/score")
    public ResponseEntity<TennisGameDto> score(@PathVariable Long gameId, @RequestBody @Valid ScoreDto scoreDto) {
        return ResponseEntity.ok(new TennisGameDto());
    }
}
