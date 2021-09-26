package com.digitalstork.tennisgamekata.exception;

public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException() {
        super();
    }

    public UnauthorizedActionException(String message) {
        super(message);
    }
}
