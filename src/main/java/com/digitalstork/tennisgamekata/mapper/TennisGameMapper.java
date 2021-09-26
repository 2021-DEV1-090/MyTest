package com.digitalstork.tennisgamekata.mapper;

import com.digitalstork.tennisgamekata.dto.TennisGameDto;
import com.digitalstork.tennisgamekata.model.TennisGame;
import com.digitalstork.tennisgamekata.util.ScoreUtil;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class TennisGameMapper implements Function<TennisGame, TennisGameDto> {

    @Override
    public TennisGameDto apply(TennisGame tennisGame) {
        TennisGameDto tennisGameDto = new TennisGameDto();
        BeanUtils.copyProperties(tennisGame, tennisGameDto, "playerOneScore", "playerTwoScore");

        tennisGameDto.setPlayerOneScore(ScoreUtil.translateScore(tennisGame.getPlayerOneScore(), tennisGame.getPlayerTwoScore()));
        tennisGameDto.setPlayerTwoScore(ScoreUtil.translateScore(tennisGame.getPlayerTwoScore(), tennisGame.getPlayerOneScore()));

        return tennisGameDto;
    }
}
