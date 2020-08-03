package com.locusintellect.martian.robots.validators;

import static com.locusintellect.martian.robots.validators.ValidationResult.newValidationResult;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class PositionValidator implements Validator {

    private static final String POSITION_INFO = "A position consists of two integers specifying the initial coordinates of the robot and an orientation (N, S, E, W), all separated by whitespace on one line.";

    private static final String VALID_PATTERN = "-?[\\d]+ -?[\\d]+ [NSEW]";
    private static final String DELIMITER = " ";
    private static final int MAX_COORDINATE_VALUE = 50;
    private static final int MIN_COORDINATE_VALUE = 0;

    @Override
    public ValidationResult validate(final String input) {
        final ValidationResult result = newValidationResult();
        if (isEmpty(input)) {
            return result.addErrorMessage(format("Position is null or empty. %s", POSITION_INFO));
        }
        if (!input.trim().matches(VALID_PATTERN)) {
            return result.addErrorMessage(format("Invalid position: %s. %s", input, POSITION_INFO));
        }
        final String[] positionValues = input.trim().split(DELIMITER);
        final int xCoordinate = parseInt(positionValues[0]);
        if (isNotInValidRange(xCoordinate)) {
            result.addErrorMessage(format("Invalid x-coordinate for position: %s. Value should be between 0 and 50 inclusive", xCoordinate));
        }

        final int yCoordinate = parseInt(positionValues[1]);
        if (isNotInValidRange(yCoordinate)) {
            result.addErrorMessage(format("Invalid y-coordinate for position: %s. Value should be between 0 and 50 inclusive", yCoordinate));
        }

        return result;
    }

    private boolean isNotInValidRange(final int coordinate) {
        return coordinate > MAX_COORDINATE_VALUE || coordinate < MIN_COORDINATE_VALUE;
    }
}
