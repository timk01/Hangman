package org.hangman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class RoundTest {

    private static final String WORD = "мама";
    private Dictionary dictionary;
    private View view;

    @BeforeEach
    void setUp(@TempDir Path tempDir) throws Exception {
        Path dictFile = tempDir.resolve("dictionary.txt");
        Files.writeString(dictFile, WORD, StandardCharsets.UTF_8);

        dictionary = new Dictionary(dictFile.toString(), 1000);
        dictionary.loadWords();

        view = new View();
    }

    @Test
    void whenWordFullyGuessedThenReturnTrue() {
        Input input = Mockito.mock(Input.class);
        when(input.getGuessedChar()).thenReturn('м', 'а');

        Round round = new Round(6, input, dictionary, view);

        boolean result = round.hangmanEngine();
        assertThat(result).isTrue();
    }

    @Test
    void whenTooManyErrorsThenReturnFalse() {
        Input input = Mockito.mock(Input.class);
        when(input.getGuessedChar()).thenReturn('б', 'в', 'г', 'д', 'е', 'ж', 'з');

        Round round = new Round(6, input, dictionary, view);

        boolean result = round.hangmanEngine();
        assertThat(result).isFalse();
    }

    @Test
    void whenRepeatCorrectLetterThenStillWin() {
        Input input = Mockito.mock(Input.class);
        when(input.getGuessedChar()).thenReturn('м', 'м', 'а', 'а');

        Round round = new Round(6, input, dictionary, view);

        boolean result = round.hangmanEngine();
        assertThat(result).isTrue();
    }

    @Test
    void whenRepeatWrongLetterThenStillWin() {
        Input input = Mockito.mock(Input.class);
        when(input.getGuessedChar()).thenReturn('б', 'б', 'м', 'а');

        Round round = new Round(2, input, dictionary, view);

        boolean result = round.hangmanEngine();
        assertThat(result).isTrue();
    }
}