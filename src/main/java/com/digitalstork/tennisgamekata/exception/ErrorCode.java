package com.digitalstork.tennisgamekata.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DATA_VALIDATION("Data Validation errors!");

    private final String defaultMessage;
}
