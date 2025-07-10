package org.hangman;

public class Validator {

    public static boolean isCyrillicLetter(char c) {
        return (c >= 'а' && c <= 'я') || c == 'ё';
    }

    public static boolean isWordValid(String word) {
        word = word.trim();
        if (word.isEmpty()) {
            return false;
        }
        return word.chars()
                .allMatch(ch -> isCyrillicLetter((char) ch));
    }
}