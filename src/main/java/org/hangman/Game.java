package org.hangman;

import java.util.*;

import static org.hangman.Dictionary.*;
import static org.hangman.Round.hangmanEngine;
import static org.hangman.View.initHangmanStages;
import static org.hangman.View.printGameIntro;

public class Game {

    private static final String DICTIONARY_PATH = "src/main/resources/dictionary.txt";

    public static void main(String[] args) {
        initHangmanStages();
        loadWords(DICTIONARY_PATH);
        printGameIntro();

        boolean continueGame = false;
        Scanner scanner = new Scanner(System.in);

        do {
            if (hangmanEngine(scanner)) {
                System.out.println("Предполагается, что вы будете продолжать. Хотите сыграть еще ? Введите: д/н");
                String answer = scanner.nextLine().trim();
                continueGame = answer.equalsIgnoreCase("д");
            }
        } while (continueGame);

        scanner.close();
    }
}