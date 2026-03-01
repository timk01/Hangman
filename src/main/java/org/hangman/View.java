package org.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class View {

    private final List<String> HANGMAN_VIEW = new ArrayList<>();

    public View() {
        initHangmanStages();
    }

    private void initHangmanStages() {

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
    }

    public void printStage(int errors) {
        System.out.println(getHangmanStage(errors));
    }

    private String getHangmanStage(int errorCounter) {
        return HANGMAN_VIEW.get(errorCounter);
    }

    public void printCurrentWordState(String wordState) {
        System.out.println(wordState);
    }

    public void printErrors(int errors, Set<Character> badLetters) {
        System.out.print("Ошибки (" + errors + "): ");

        if (!badLetters.isEmpty()) {
            System.out.println(badLetters);
        } else {
            System.out.println();
        }
    }

    public void printMessage(String x) {
        System.out.println(x);
    }

    public void printRoundResult(String word, boolean isWin) {
        String result = isWin ? "выиграли" : "проиграли";
        System.out.printf("Вы %s. Было загадано слово: %s%n", result, word);
    }

    public void printGameIntro(int maxErrors) {
        System.out.printf("""  
                Задача:
                Я загадаю одно слово — имя существительное на русском языке в именительном падеже.
                Ты будешь пытаться угадать его по буквам.
                        
                Правила:
                - Вводи по одной русской букве за попытку.
                - Если буква есть в слове — она откроется на всех позициях.
                - Если буквы в слове нет — ты получишь штраф (ошибку).
                - Всего допускается не более %d ошибок.
                - Повторный ввод уже названной буквы не засчитывается как ошибка.
                        
                Цель:
                Угадать всё слово до того, как человечек будет "повешен".
                        
                Удачи! Начинаем игру.
                %n""", maxErrors);
    }

    public void greetings() {
        System.out.println("Добро пожаловать в игру 'Виселица'!");
    }
}
