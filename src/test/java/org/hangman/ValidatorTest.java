package org.hangman;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    @ParameterizedTest
    @ValueSource(chars = {'а', 'д', 'я', 'ё', 'м'})
    void whenCharIsCyrillicThenReturnTrue(char input) {
        assertThat(Validator.isCyrillicLetter(input)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', '1', '@', ' ', 'Ж'})
    void whenCharIsNotCyrillicThenReturnFalse(char input) {
        assertThat(Validator.isCyrillicLetter(input)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"карета", "ёж", "мама", "папа", " Мама ", "Ёж"})
    void whenWordIsValidThenReturnTrue(String word) {
        assertThat(Validator.isWordValid(word)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"карета!", "papa", "мама1", "hello"})
    void whenWordIsNotValidThenReturnFalse(String word) {
        assertThat(Validator.isWordValid(word)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "     "})
    void whenWordIsEmptyOrBlankThenReturnFalse(String input) {
        assertThat(Validator.isWordValid(input)).isFalse();
    }
}