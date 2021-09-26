package com.digitalstork.tennisgamekata.exception;

public class IllegalScoreException extends RuntimeException {
    public IllegalScoreException() {
        super();
    }

    public IllegalScoreException(String message) {
        super(message);
    }
}
