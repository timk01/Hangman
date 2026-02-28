package org.hangman;

import java.util.ArrayList;
import java.util.List;

public class View {

    private static final List<String> HANGMAN_VIEW = new ArrayList<>();
    //private static int ERRORS = new ArrayList<>(); ошибки - меджик намбер ?

    public void initHangmanStages() {

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

    public String getHangmanStage(int errorCounter) {
        return HANGMAN_VIEW.get(errorCounter);
    }

    public void printGameIntro() {
        System.out.println("""

                Задача:
                Я загадаю одно слово — имя существительное на русском языке в именительном падеже.
                Ты будешь пытаться угадать его по буквам.

                Правила:
                - Вводи по одной русской букве за попытку.
                - Если буква есть в слове — она откроется на всех позициях.
                - Если буквы в слове нет — ты получишь штраф (ошибку).
                - Всего допускается не более 6 ошибок.
                - Повторный ввод уже названной буквы не засчитывается как ошибка.

                Цель:
                Угадать всё слово до того, как человечек будет "повешен".

                Удачи! Начинаем игру.
                """);
    }

    public void greetings() {
        System.out.println("Добро пожаловать в игру 'Виселица'!");
    }
}
