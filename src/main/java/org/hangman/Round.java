package org.hangman;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hangman.Dictionary.getARandomWord;
import static org.hangman.WordUtils.*;

public class Round {

    private static final int ERRORS_MAX = 6;

    private static char getGuessedChar(Scanner scanner) {
        String word;
        do {
            System.out.println("Нужно загадать одну русскую букву");
            word = scanner.nextLine().trim().toLowerCase();
            if (word.length() == 1 && Validator.isCyrillicLetter(word.charAt(0))) {
                return word.charAt(0);
            }
            System.out.println("Некорректный ввод. Введите ОДНУ русскую букву.");
        } while (true);
    }


    public static boolean hangmanEngine(Scanner scanner) {
        String word = getARandomWord();
        int errors = 0;
        Set<Character> uniqueLetters = word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());

        Set<Character> goodLetters = new HashSet<>();
        Set<Character> badLetters = new HashSet<>();

        String emptyWordWithPlaceholders = String.valueOf(initEmptyWord(word));

        while (errors < ERRORS_MAX && !isWordFullyGuessed(word, goodLetters)) {
            System.out.println(View.getHangmanStage(errors));

            if (goodLetters.isEmpty()) {
                System.out.println(emptyWordWithPlaceholders);
            } else {
                System.out.println(buildRevealedWord(word, goodLetters));
            }

            System.out.print("Ошибки (" + errors + "): ");

            if (!badLetters.isEmpty()) {
                System.out.println(badLetters);
            } else {
                System.out.println();
            }

            char guessedChar = getGuessedChar(scanner);

            if (goodLetters.contains(guessedChar) || badLetters.contains(guessedChar)) {
                System.out.println("Вы уже называли эту букву. Попробуйте другую.");
                continue;
            }

            System.out.println("Загаданная буква: " + guessedChar);

            if (uniqueLetters.contains(guessedChar)) {
                goodLetters.add(guessedChar);
            } else {
                badLetters.add(guessedChar);
                errors++;
            }
        }

        boolean result;
        if (isWordFullyGuessed(word, goodLetters)) {
            System.out.println("Поздравляем! Вы угадали слово: " + word);
            result = true;
        } else {
            System.out.println(View.getHangmanStage(errors));
            System.out.println("Вы проиграли. Было загадано слово: " + word);
            result = false;
        }

        return result;
    }

}
