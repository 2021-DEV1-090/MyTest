package com.digitalstork.tennisgamekata.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DATA_VALIDATION("Data Validation errors!"),
    UNAUTHORIZED_ACTION("Action not authorized");

    private final String defaultMessage;
}
