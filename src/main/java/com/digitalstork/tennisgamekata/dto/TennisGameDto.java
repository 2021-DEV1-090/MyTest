package com.digitalstork.tennisgamekata.dto;

import com.digitalstork.tennisgamekata.enums.GameStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TennisGameDto {

    @Schema(description = "ID of the game", example = "1")
    private Long id;

    @Schema(description = "Player one score", example = "0")
    private String playerOneScore;

    @Schema(description = "Player two score", example = "30")
    private String playerTwoScore;

    @Schema(description = "Player one name", example = "John")
    private String playerOne;

    @Schema(description = "Player two name", example = "Doe")
    private String playerTwo;

    @Schema(description = "Whether the game is ended or not", example = "false")
    private boolean gameEnded;

    @Schema(description = "Game status", example = "DEUCE")
    private GameStatus status;
}
