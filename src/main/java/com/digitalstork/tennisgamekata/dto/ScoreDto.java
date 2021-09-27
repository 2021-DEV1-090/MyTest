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
public class ScoreDto {

    @Schema(description = "Name of player that score the point", example = "John", required = true)
    @NotBlank(message = "scorer field is required!")
    private String scorer;
}
