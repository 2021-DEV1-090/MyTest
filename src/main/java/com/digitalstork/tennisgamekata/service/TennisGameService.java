package com.digitalstork.tennisgamekata.service;

import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.dto.TennisGameDto;

public interface TennisGameService {

    public TennisGameDto createGame(TennisGameCreateDto tennisGameCreateDto);
}
