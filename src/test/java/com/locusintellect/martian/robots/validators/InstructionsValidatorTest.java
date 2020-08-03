package com.locusintellect.martian.robots.validators;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class InstructionsValidatorTest {

    private final InstructionsValidator underTest = new InstructionsValidator();

    @Test
    public void shouldIndicateAsSuccessForValidInstructions() {
        final ValidationResult result = underTest.validate("FRRFLLFFRRFLL");

        assertThat(result.isSuccess(), is(true));
    }

    @Test
    public void shouldValidateInputIgnoringLeadingAndTrailingSpaces() {
        final ValidationResult result = underTest.validate("  FRR ");

        assertThat(result.isSuccess(), is(true));
    }

    @Test
    public void shouldIndicateAsFailureWhenInstructionsAreEmpty() {
        final ValidationResult result = underTest.validate("");

        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrorMessage(), is("Instruction is null or empty. A robot instruction is a string of the letters \"L\", \"R\" and \"F\" on one line."));
    }

    @Test
    public void shouldIndicateAsFailureWhenInstructionsAreNull() {
        final ValidationResult result = underTest.validate(null);

        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrorMessage(), is("Instruction is null or empty. A robot instruction is a string of the letters \"L\", \"R\" and \"F\" on one line."));
    }

    @Test
    public void shouldIndicateAsFailureWhenInstructionStringIs100CharactersInLength() {
        final ValidationResult result = underTest.validate("FRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLLLLFRRFLLFFRRFLLFRRFLLL");

        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrorMessage(), is("Invalid instruction: FRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLLLLFRRFLLFFRRFLLFRRFLLL. Instruction string needs to be less than 100 characters in length."));
    }

    @Test
    public void shouldIndicateAsFailureWhenInstructionStringHasInvalidCharacter() {
        final ValidationResult result = underTest.validate("FRRFLLFFA");

        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrorMessage(), is("Invalid instruction: FRRFLLFFA. A robot instruction is a string of the letters \"L\", \"R\" and \"F\" on one line."));
    }

}