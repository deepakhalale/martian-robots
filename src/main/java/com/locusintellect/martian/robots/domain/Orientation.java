package com.locusintellect.martian.robots.domain;

import static java.lang.String.format;
import static java.util.Arrays.stream;

public enum Orientation {
    NORTH("N"), SOUTH("S"), EAST("E"), WEST("W");

    private final String value;

    Orientation(final String value) {
        this.value = value;
    }

    public static Orientation from(final String inputValue) {
        return stream(values()).filter(orientation -> orientation.getValue().equals(inputValue))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(format("Invalid orientation %s", inputValue)));
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
