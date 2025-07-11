package org.hangman;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hangman.View.initHangmanStages;

class RoundTest {

    private static final String WORD = "мама";
    private Scanner scanner;

    @BeforeAll
    static void setUpDictionary() throws Exception {
        initHangmanStages();
        Path tempDict = Files.createTempFile("dictionary", ".txt");
        Files.writeString(tempDict, WORD);
        Dictionary.loadWords(tempDict.toString());
    }

    private Scanner buildScannerFromLines(String... lines) {
        String input = String.join("\n", lines);
        return new Scanner(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    @DisplayName("Когда слово полностью отгадано — возвращается true")
    void whenWordFullyGuessedThenReturnTrue() {
        scanner = buildScannerFromLines("м", "а");
        boolean result = Round.hangmanEngine(scanner);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Когда превышен лимит ошибок — возвращается false")
    void whenTooManyErrorsThenReturnFalse() {
        scanner = buildScannerFromLines("б", "в", "г", "д", "е", "ж", "з");
        boolean result = Round.hangmanEngine(scanner);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Повторный ввод правильной буквы не влияет на результат")
    void whenRepeatCorrectLetterThenStillWin() {
        scanner = buildScannerFromLines("м", "м", "а", "а");
        boolean result = Round.hangmanEngine(scanner);
        assertThat(result).isTrue();
    }
}