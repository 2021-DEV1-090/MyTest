package com.digitalstork.tennisgamekata.mapper;

import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.enums.GameStatus;
import com.digitalstork.tennisgamekata.model.TennisGame;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class TennisGameCreateDtoMapper implements Function<TennisGameCreateDto, TennisGame> {

    @Override
    public TennisGame apply(TennisGameCreateDto tennisGameCreateDto) {
        TennisGame tennisGame = new TennisGame();
        BeanUtils.copyProperties(tennisGameCreateDto, tennisGame);
        tennisGame.setStatus(GameStatus.STARTED);

        return tennisGame;
    }
}
