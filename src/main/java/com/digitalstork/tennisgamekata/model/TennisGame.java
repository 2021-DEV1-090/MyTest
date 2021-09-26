package com.digitalstork.tennisgamekata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TennisGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int playerOneScore;
    private int playerTwoScore;
    private String playerOne;
    private String playerTwo;
    private boolean gameEnded;
}
