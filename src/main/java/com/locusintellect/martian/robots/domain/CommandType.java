package com.locusintellect.martian.robots.domain;

import static java.lang.String.format;
import static java.util.Arrays.stream;

public enum CommandType {

    LEFT('L'), RIGHT('R'), FORWARD('F');

    private final char value;

    CommandType(final char value) {
        this.value = value;
    }

    public static CommandType from(final char inputValue) {
        return stream(values()).filter(commandType -> commandType.getValue() == inputValue)
                .findFirst().orElseThrow(() -> new IllegalArgumentException(format("Invalid command type character %s", inputValue)));
    }

    public char getValue() {
        return value;
    }
}
