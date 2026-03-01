package org.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dictionary {

    private final List<String> dictionary = new ArrayList<>();
    private final String path;
    private final int maxWords;

    public Dictionary(String path, int maxWords) {
        this.path = path;
        this.maxWords = maxWords;
    }

    public void loadWords() {
        try (BufferedReader buff = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = buff.readLine()) != null && dictionary.size() < maxWords) {
                if (Validator.isWordValid(line)) {
                    dictionary.add(line);
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Не найден файл dictionary (словаря) по указанному пути", e);
        }
    }

    public String getARandomWord() {
        return pickRandomWord();
    }

    private String pickRandomWord() {
        if (dictionary.isEmpty()) {
            throw new IllegalStateException("Словарь пуст");
        }
        Random random = new Random();
        return dictionary.get(random.nextInt(dictionary.size()));
    }

}
