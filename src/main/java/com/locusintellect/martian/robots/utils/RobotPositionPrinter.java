package com.locusintellect.martian.robots.utils;

import static java.lang.String.format;

import com.locusintellect.martian.robots.domain.FinalPosition;

public class RobotPositionPrinter {

    private static final String DEFAULT_DISPLAY_FORMAT = "%s %s %s";
    private static final String DISPLAY_FORMAT_FOR_LOST = "%s %s %s LOST";

    public static String robotPositionDisplayString(final FinalPosition position) {
        final String displayFormat = position.isLost() ? DISPLAY_FORMAT_FOR_LOST : DEFAULT_DISPLAY_FORMAT;
        return format(displayFormat, position.getCoordinates().getX(), position.getCoordinates().getY(), position.getOrientation().getValue());
    }
}
