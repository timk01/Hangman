package org.hangman;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DictionaryTest {

    @Test
    void whenLoadValidDictionaryThenSuccess(@TempDir Path tempDir) throws IOException {
        Path dictFile = tempDir.resolve("valid_dictionary.txt");
        Files.write(dictFile, """
                мама
                папа
                дом
                кот
                ёж
                """.getBytes());

        Dictionary dictionary = new Dictionary(dictFile.toString(), 1000);
        dictionary.loadWords();

        String aRandomWord = dictionary.getARandomWord();

        assertThat(aRandomWord).isIn("мама", "папа", "дом", "кот", "ёж");
    }

    @Test
    void whenLoadValidDictionaryButItsCapped(@TempDir Path tempDir) throws IOException {
        Path dictFile = tempDir.resolve("valid_dictionary.txt");
        Files.write(dictFile, """
                мама
                папа
                дом
                кот
                ёж
                """.getBytes());

        int dictionaryCap = 2;
        Dictionary dictionary = new Dictionary(dictFile.toString(), dictionaryCap);
        dictionary.loadWords();

        String aRandomWord = dictionary.getARandomWord();

        assertThat(aRandomWord).isIn("мама", "папа");
    }

    @Test
    void whenLoadEmptyDictionaryThenThrow(@TempDir Path tempDir) throws IOException {
        Dictionary dictionary = new Dictionary("src/test/resources/no_dictionary_here.txt", 1000);

        assertThatThrownBy(dictionary::loadWords)
                .isInstanceOf(UncheckedIOException.class)
                .hasMessageContaining("Не найден файл dictionary");
    }
}