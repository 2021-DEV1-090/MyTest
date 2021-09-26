package com.digitalstork.tennisgamekata.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TennisGameDto {

    private Long id;
    private String playerOneScore;
    private String playerTwoScore;
    private String playerOne;
    private String playerTwo;
    private boolean gameEnded;
}
