package com.locusintellect.martian.robots.validators;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

import java.util.List;

public class ValidationResult {

    private final List<String> errorMessages = newArrayList();

    public static ValidationResult success() {
        return new ValidationResult();
    }

    public static ValidationResult newValidationResult() {
        return new ValidationResult();
    }

    private ValidationResult() {
    }

    public ValidationResult addErrorMessage(final String message) {
        errorMessages.add(message);
        return this;
    }

    public boolean isSuccess() {
        return errorMessages.isEmpty();
    }

    public String getErrorMessage() {
        return errorMessages.stream().collect(joining(lineSeparator()));
    }
}
