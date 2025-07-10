package org.hangman;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;
import static org.hangman.View.initHangmanStages;

class RoundTest {

/*    @Test
    @DisplayName("Когда слово полностью отгадано — возвращается true")
    void whenWordFullyGuessedThenReturnTrue() throws Exception {
        // Создаём словарь с одним словом "мама"
        Path tempDict = Files.createTempFile("dictionary", ".txt");
        Files.writeString(tempDict, "мама");
        Dictionary.loadWords(tempDict.toString());

        // Буквы: м, а → достаточно чтобы отгадать всё слово
        String input = String.join("\n", "м", "а");
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        boolean result = Round.hangmanEngine(scanner);

        assertThat(result).isTrue();
    }*/

    @BeforeAll
    static void setUpDictionary() throws Exception {
        initHangmanStages();
        Path tempDict = Files.createTempFile("dictionary", ".txt");
        Files.writeString(tempDict, "мама");
        Dictionary.loadWords(tempDict.toString());
    }

    @Test
    @DisplayName("Когда слово полностью отгадано — возвращается true")
    void whenWordFullyGuessedThenReturnTrue() {
        String input = String.join("\n", "м", "а", "а");
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        boolean result = Round.hangmanEngine(scanner);

        assertThat(result).isTrue();
    }

}