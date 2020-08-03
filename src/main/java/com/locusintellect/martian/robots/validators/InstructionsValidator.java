package com.locusintellect.martian.robots.validators;

import static com.locusintellect.martian.robots.validators.ValidationResult.newValidationResult;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class InstructionsValidator implements Validator {

    private static final String INSTRUCTION_INFO = "A robot instruction is a string of the letters \"L\", \"R\" and \"F\" on one line.";
    private static final String VALID_PATTERN = "[LRF]+";
    private static final int MAX_INSTRUCTION_LENGTH = 100;

    @Override
    public ValidationResult validate(final String input) {
        final ValidationResult result = newValidationResult();
        if (isEmpty(input)) {
            return result.addErrorMessage(format("Instruction is null or empty. %s", INSTRUCTION_INFO));
        }
        if (!input.trim().matches(VALID_PATTERN)) {
            return result.addErrorMessage(format("Invalid instruction: %s. %s", input, INSTRUCTION_INFO));
        }
        if (input.trim().length() >= MAX_INSTRUCTION_LENGTH) {
            result.addErrorMessage(format("Invalid instruction: %s. Instruction string needs to be less than %s characters in length.", input, MAX_INSTRUCTION_LENGTH));
        }
        return result;
    }
}
