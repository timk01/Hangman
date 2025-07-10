package org.hangman;

import java.util.Set;

public class WordUtils {

    public static StringBuilder initEmptyWord(String word) {
        char[] charArray = word.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            if (i != charArray.length - 1) {
                sb.append("_ ");
            } else {
                sb.append("_");
            }
        }
        return sb;
    }

    public static StringBuilder buildRevealedWord(
            String initialWord, Set<Character> lettersToReplace) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < initialWord.length(); i++) {
            char c = initialWord.charAt(i);
            if (lettersToReplace.contains(c)) {
                sb.append(c);
            } else {
                sb.append("_");
            }

            if (i != initialWord.length() - 1) {
                sb.append(" ");
            }
        }
        return sb;
    }

    public static boolean isWordFullyGuessed(String word, Set<Character> goodLetters) {
        return buildRevealedWord(word, goodLetters).toString().replace(" ", "").equals(word);
    }
}
