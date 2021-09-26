package com.digitalstork.tennisgamekata.service;

import com.digitalstork.tennisgamekata.dto.ScoreDto;
import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.dto.TennisGameDto;

public interface TennisGameService {

    TennisGameDto createGame(TennisGameCreateDto tennisGameCreateDto);

    TennisGameDto score(Long gameId, ScoreDto scoreDto);
}
