package org.hangman;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        View view = new View();
        view.greetings();

        String path = "src/main/resources/dictionary.txt";
        int maxWords = 1000;
        Dictionary dictionary = new Dictionary(path, maxWords);
        dictionary.loadWords();

        Scanner scanner = new Scanner(System.in);
        Input input = new UserInput(scanner);

        int maxErrors = 6;
        Round round = new Round(maxErrors, input, dictionary, view);

        while (input.askToPlay()) {
            view.printGameIntro(maxErrors);
            round.hangmanEngine();
        }

        scanner.close();
    }
}