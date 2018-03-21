package com.tdanylchuk.drawmaster.exception;

public class DrawmasterApplicationException extends RuntimeException {

    public DrawmasterApplicationException(final String message) {
        super("Application exception. " + message);
    }
}
