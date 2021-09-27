package com.digitalstork.tennisgamekata.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DATA_VALIDATION("Data Validation errors!"),
    UNAUTHORIZED_ACTION("Action not authorized"),
    RESOURCE_NOT_FOUND("Resource not found"),
    PLAYER_NOT_FOUND("Player with provided name not found");

    private final String defaultMessage;
}
