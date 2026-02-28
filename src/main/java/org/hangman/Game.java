package org.hangman;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        View view = new View();
        view.greetings();

        Scanner scanner = new Scanner(System.in);
        Input userInput = new UserInput(scanner);

        String path = "src/main/resources/dictionary.txt";
        int maxWords = 1000;
        Dictionary dictionary = new Dictionary(path, maxWords);
        dictionary.loadWords();

        Round round = new Round();

        while (userInput.userDialogue()) {
            view.initHangmanStages();
            view.printGameIntro();
            round.hangmanEngine(scanner);
        }

        scanner.close();
    }
}