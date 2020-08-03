package com.locusintellect.martian.robots.exceptions;

public class InstructionsFileNotFound extends RuntimeException {

    public InstructionsFileNotFound(final String message, final Exception cause) {
        super(message, cause);
    }
}
