package com.locusintellect.martian.robots.domain;

public enum Orientation {
    NORTH("N"), SOUTH("S"), EAST("E"), WEST("W");

    private final String value;

    Orientation(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
