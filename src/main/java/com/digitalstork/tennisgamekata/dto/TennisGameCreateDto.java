package com.digitalstork.tennisgamekata.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Player one name", example = "John", required = true)
    @NotBlank(message = "playerOne field is required!")
    private String playerOne;

    @Schema(description = "Player two name", example = "Doe", required = true)
    @NotBlank(message = "playerTwo field is required!")
    private String playerTwo;
}
