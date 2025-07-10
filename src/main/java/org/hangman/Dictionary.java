package org.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dictionary {

    private static final List<String> DICTIONARY = new ArrayList<>();

    private static final int MAX_WORDS = 1000;

    public static void loadWords(String path) {
        try (BufferedReader buff = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = buff.readLine()) != null && DICTIONARY.size() < MAX_WORDS) {
                if (Validator.isWordValid(line)) {
                    DICTIONARY.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Не найден файл dictionary (словаря) по указанному пути");
            e.printStackTrace();
        }
    }

    private static String pickRandomWord() {
        if (DICTIONARY.isEmpty()) {
            throw new IllegalStateException("Словарь пуст");
        }
        Random random = new Random();
        return DICTIONARY.get(random.nextInt(DICTIONARY.size()));
    }

    public static String getARandomWord() {
        return pickRandomWord();
    }

}
