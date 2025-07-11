package org.hangman;

import java.util.Scanner;

import static org.hangman.Dictionary.loadWords;
import static org.hangman.Round.hangmanEngine;
import static org.hangman.View.initHangmanStages;
import static org.hangman.View.printGameIntro;

public class Game {

    private static final String DICTIONARY_PATH = "src/main/resources/dictionary.txt";

    public static boolean checkFinalAnswer(Scanner scanner) {
        while (true) {
            System.out.println("Хотите сыграть еще? Введите 'д' для продолжения или 'н' для выхода.");
            String word = scanner.nextLine().trim().toLowerCase();
            if (word.equals("д")) {
                return true;
            } else if (word.equals("н")) {
                return false;
            }
            System.out.println("Некорректный ввод. Пожалуйста, введите 'д' или 'н'.");
        }
    }

    public static void main(String[] args) {
        initHangmanStages();
        loadWords(DICTIONARY_PATH);
        printGameIntro();

        boolean continueGame = false;
        Scanner scanner = new Scanner(System.in);

        do {
            if (hangmanEngine(scanner)) {
                continueGame = checkFinalAnswer(scanner);
            }
        } while (continueGame);

        scanner.close();
    }
}