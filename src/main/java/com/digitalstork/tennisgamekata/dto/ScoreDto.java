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
public class ScoreDto {

    @NotBlank(message = "scorer field is required!")
    private String scorer;
}
