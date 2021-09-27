package com.digitalstork.tennisgamekata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TennisGameCreateDto {

    @NotBlank(message = "playerOne field is required!")
    private String playerOne;
    @NotBlank(message = "playerTwo field is required!")
    private String playerTwo;
}
