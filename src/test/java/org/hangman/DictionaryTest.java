package org.hangman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DictionaryTest {

    //лютейший костыль. временный, пока без ООП
    @BeforeEach
    void resetDictionary() throws Exception {
        Field field = Dictionary.class.getDeclaredField("DICTIONARY");
        field.setAccessible(true);
        List<String> internalList = (List<String>) field.get(null);
        internalList.clear();
    }

    @Test
    @DisplayName("Загрузка корректного словаря из временного файла")
    void whenLoadValidDictionaryThenSuccess(@TempDir Path tempDir) throws IOException {
        Path dictFile = tempDir.resolve("valid_dictionary.txt");
        Files.write(dictFile, """
                мама
                папа
                дом
                кот
                ёж
                """.getBytes());

        Dictionary.loadWords(dictFile.toString());
        String word = Dictionary.getARandomWord();

        assertThat(word).isIn("мама", "папа", "дом", "кот", "ёж");
    }

    @Test
    @DisplayName("Загрузка пустого словаря из временного файла")
    void whenLoadEmptyDictionaryThenThrow(@TempDir Path tempDir) throws IOException {
        Path dictFile = tempDir.resolve("empty.txt");
        Files.write(dictFile, new byte[0]);

        Dictionary.loadWords(dictFile.toString());

        assertThatThrownBy(Dictionary::getARandomWord)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Словарь пуст");
    }

    @Test
    @DisplayName("Файл словаря не существует")
    void whenFileDoesNotExistThenThrow() {
        Dictionary.loadWords("src/test/resources/no_such_file.txt");

        assertThatThrownBy(Dictionary::getARandomWord)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Словарь пуст");
    }
}