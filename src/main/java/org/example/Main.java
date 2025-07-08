package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<String> HANGMAN_VIEW = new ArrayList<>();
    private static final int ERRORS = 6;

    private static List<String> initHangmanStages() {

        HANGMAN_VIEW.add("""
      +---+
      |   |
          |
          |
          |
          |
    =========
    """);

        HANGMAN_VIEW.add("""
      +---+
      |   |
      O   |
          |
          |
          |
    =========
    """);

        HANGMAN_VIEW.add("""
      +---+
      |   |
      O   |
      +   |
          |
          |
    =========
    """);

        HANGMAN_VIEW.add("""
      +---+
      |   |
      O   |
     /+   |
          |
          |
    =========
    """);

        HANGMAN_VIEW.add("""
      +---+
      |   |
      O   |
     /+\\  |
          |
          |
    =========
    """);

        HANGMAN_VIEW.add("""
      +---+
      |   |
      O   |
     /+\\  |
     /    |
          |
    =========
    """);

        HANGMAN_VIEW.add("""
      +---+
      |   |
      O   |
     /+\\  |
     / \\  |
          |
    =========
    """);

        return HANGMAN_VIEW;
    }

    public static void main(String[] args) {
        initHangmanStages();
        printHangman(3);
    }

    static void printHangman(int errors) {
        System.out.println(HANGMAN_VIEW.get(errors));
    }

}