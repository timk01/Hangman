package org.hangman;

import java.util.Scanner;

import static org.hangman.Dictionary.loadWords;
import static org.hangman.Round.hangmanEngine;
import static org.hangman.View.initHangmanStages;
import static org.hangman.View.printGameIntro;

public class Game {

    private static final String DICTIONARY_PATH = "src/main/resources/dictionary.txt";

    public static boolean askToStartNewGame(Scanner scanner) {
        while (true) {
            System.out.println("Начать новую игру? Введите 'д' для начала или 'н' для выхода.");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("д")) {
                return true;
            } else if (input.equals("н")) {
                return false;
            } else {
                System.out.println("Некорректный ввод. Пожалуйста, введите 'д' или 'н'.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в игру 'Виселица'!");

        while (askToStartNewGame(scanner)) {
            initHangmanStages();
            loadWords(DICTIONARY_PATH);
            printGameIntro();
            hangmanEngine(scanner);
        }

        scanner.close();
    }
}