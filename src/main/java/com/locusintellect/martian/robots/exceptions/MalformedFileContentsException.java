package com.locusintellect.martian.robots.exceptions;

public class MalformedFileContentsException extends RuntimeException {

    public MalformedFileContentsException(final String message, final Exception cause) {
        super(message, cause);
    }
}
