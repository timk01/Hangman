package org.hangman;

public class Validator {
    private static final char LOWER_A = 'а';
    private static final char LOWER_YA = 'я';
    private static final char LOWER_YO = 'ё';

    public static boolean isCyrillicLetter(char c) {
        return (c >= LOWER_A && c <= LOWER_YA) || c == LOWER_YO;
    }

    public static boolean isWordValid(String word) {
        word = word.trim().toLowerCase();
        if (word.isEmpty()) {
            return false;
        }
        return word.chars()
                .allMatch(ch -> isCyrillicLetter((char) ch));
    }
}