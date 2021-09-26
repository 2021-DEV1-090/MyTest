package com.digitalstork.tennisgamekata.service;

import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.dto.TennisGameDto;
import com.digitalstork.tennisgamekata.repository.TennisGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TennisGameServiceImpl implements TennisGameService {

    private final TennisGameRepository tennisGameRepository;

    @Override
    public TennisGameDto createGame(TennisGameCreateDto tennisGameCreateDto) {
        return null;
    }
}
