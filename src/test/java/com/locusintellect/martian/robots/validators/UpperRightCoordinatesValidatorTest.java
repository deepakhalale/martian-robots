package com.locusintellect.martian.robots.validators;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

import org.junit.Test;

public class UpperRightCoordinatesValidatorTest {

    private final UpperRightCoordinatesValidator underTest = new UpperRightCoordinatesValidator();

    @Test
    public void shouldIndicateAsSuccessForValidUpperRightCoordinates() {
        final ValidationResult result = underTest.validate("5 3");

        assertThat(result.isSuccess(), is(true));
    }

    @Test
    public void shouldValidateInputIgnoringLeadingAndTrailingSpaces() {
        final ValidationResult result = underTest.validate("  5 3 ");

        assertThat(result.isSuccess(), is(true));
    }

    @Test
    public void shouldIndicateAsSuccessWhenUpperRightCoordinatesAreEqualToMaximumAllowedValue() {
        final ValidationResult result = underTest.validate("50 50");

        assertThat(result.isSuccess(), is(true));
    }

    @Test
    public void shouldIndicateAsSuccessWhenUpperRightCoordinatesAreEqualToMinimumAllowedValue() {
        final ValidationResult result = underTest.validate("0 0");

        assertThat(result.isSuccess(), is(true));
    }

    @Test
    public void shouldIndicateAsFailureWhenUpperRightCoordinatesIsEmpty() {
        final ValidationResult result = underTest.validate("");

        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrorMessage(), is("Upper right coordinates is null or empty. Should specify a pair of integers i.e. x-coordinate followed by y-coordinate"));
    }

    @Test
    public void shouldIndicateAsFailureWhenUpperRightCoordinatesIsNull() {
        final ValidationResult result = underTest.validate(null);

        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrorMessage(), is("Upper right coordinates is null or empty. Should specify a pair of integers i.e. x-coordinate followed by y-coordinate"));
    }

    @Test
    public void shouldIndicateAsFailureWhenUpperRightCoordinatesHasOnlyOneCoordinate() {
        final ValidationResult result = underTest.validate("5 ");

        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrorMessage(), is("Invalid upper right coordinates: 5  does not specify a pair of integers i.e. x-coordinate followed by y-coordinate"));
    }

    @Test
    public void shouldIndicateAsFailureWhenUpperRightCoordinatesHasMoreThanAPairOfIntegers() {
        final ValidationResult result = underTest.validate("12 12 12");

        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrorMessage(), is("Invalid upper right coordinates: 12 12 12 does not specify a pair of integers i.e. x-coordinate followed by y-coordinate"));
    }

    @Test
    public void shouldIndicateAsFailureWhenUpperRightCoordinatesHasLetters() {
        final ValidationResult result = underTest.validate("12 a");

        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrorMessage(), is("Invalid upper right coordinates: 12 a does not specify a pair of integers i.e. x-coordinate followed by y-coordinate"));
    }

    @Test
    public void shouldIndicateAsFailureWhenUpperRightCoordinatesAreMoreThanMaximumAllowedValue() {
        final ValidationResult result = underTest.validate("51 55");

        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrorMessage(), containsString("Invalid x-coordinate for upper right coordinates: 51. Value should be between 0 and 50 inclusive"));
        assertThat(result.getErrorMessage(), containsString("Invalid y-coordinate for upper right coordinates: 55. Value should be between 0 and 50 inclusive"));
    }

    @Test
    public void shouldIndicateAsFailureWhenUpperRightCoordinatesAreLessThanMinimumAllowedValue() {
        final ValidationResult result = underTest.validate("-2 -4");

        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrorMessage(), containsString("Invalid x-coordinate for upper right coordinates: -2. Value should be between 0 and 50 inclusive"));
        assertThat(result.getErrorMessage(), containsString("Invalid y-coordinate for upper right coordinates: -4. Value should be between 0 and 50 inclusive"));
    }

}