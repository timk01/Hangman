package org.hangman;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class WordUtilsTest {

    @ParameterizedTest()
    @MethodSource("revealWordCases")
    void whenBuildRevealedWordThenReturnCorrectMaskedWord(String word, Set<Character> guessed, String expected) {
        StringBuilder revealed = WordUtils.buildRevealedWord(word, guessed);
        assertThat(revealed.toString()).isEqualTo(expected);
    }

    static Stream<Arguments> revealWordCases() {
        return Stream.of(
                Arguments.of("мир", Set.of(), "_ _ _"),
                Arguments.of("мир", Set.of('и'), "_ и _"),
                Arguments.of("мир", Set.of('м', 'р'), "м _ р"),
                Arguments.of("мир", Set.of('м', 'и', 'р'), "м и р")
        );
    }

    @ParameterizedTest()
    @MethodSource("fullyGuessedCases")
    void whenIsWordFullyGuessedThenReturnExpectedResult(String word, Set<Character> guessed, boolean expected) {
        boolean actual = WordUtils.isWordFullyGuessed(word, guessed);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> fullyGuessedCases() {
        return Stream.of(
                Arguments.of("кот", Set.of('к', 'о'), false),
                Arguments.of("кот", Set.of('к', 'о', 'т'), true),
                Arguments.of("мир", Set.of(), false)
        );
    }

    @ParameterizedTest()
    @MethodSource("emptyWordCases")
    void whenInitEmptyWordThenReturnPlaceholders(String word, String expected) {
        StringBuilder placeholders = WordUtils.initEmptyWord(word);
        assertThat(placeholders.toString()).isEqualTo(expected);
    }

    static Stream<Arguments> emptyWordCases() {
        return Stream.of(
                Arguments.of("кот", "_ _ _"),
                Arguments.of("мама", "_ _ _ _"),
                Arguments.of("ё", "_")
        );
    }
}