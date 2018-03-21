package com.tdanylchuk.drawmaster.exception;

public class UserInputException extends RuntimeException {

    public UserInputException(final String message) {
        super("Incorrect input. " + message);
    }
}
