package com.locusintellect.martian.robots.validators;

import static com.locusintellect.martian.robots.validators.ValidationResult.newValidationResult;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class UpperRightCoordinatesValidator implements Validator {

    private static final String VALID_PATTERN = "-?[\\d]+ -?[\\d]+";
    private static final String DELIMITER = " ";
    private static final int MAX_COORDINATE_VALUE = 50;
    private static final int MIN_COORDINATE_VALUE = 0;

    @Override
    public ValidationResult validate(final String input) {
        final ValidationResult result = newValidationResult();
        if (isEmpty(input)) {
            return result.addErrorMessage("Upper right coordinates is null or empty. Should specify a pair of integers i.e. x-coordinate followed by y-coordinate");
        }
        if (!input.trim().matches(VALID_PATTERN)) {
            return result.addErrorMessage(format("Invalid upper right coordinates: %s does not specify a pair of integers i.e. x-coordinate followed by y-coordinate", input));
        }
        final String[] coordinates = input.trim().split(DELIMITER);
        final int xCoordinate = parseInt(coordinates[0]);
        if (isNotInValidRange(xCoordinate)) {
            result.addErrorMessage(format("Invalid x-coordinate for upper right coordinates: %s. Value should be between 0 and 50 inclusive", xCoordinate));
        }

        final int yCoordinate = parseInt(coordinates[1]);
        if (isNotInValidRange(yCoordinate)) {
            result.addErrorMessage(format("Invalid y-coordinate for upper right coordinates: %s. Value should be between 0 and 50 inclusive", yCoordinate));
        }

        return result;
    }

    private boolean isNotInValidRange(final int coordinate) {
        return coordinate > MAX_COORDINATE_VALUE || coordinate < MIN_COORDINATE_VALUE;
    }
}
