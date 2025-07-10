package org.hangman;

import java.util.ArrayList;
import java.util.List;

public class View {

    private static final List<String> HANGMAN_VIEW = new ArrayList<>();

    public static void initHangmanStages() {

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

    public static String getHangmanStage(int errorCounter) {
        return HANGMAN_VIEW.get(errorCounter);
    }

    public static void printGameIntro() {
        System.out.println("""
            Привет! Добро пожаловать в игру "Виселица".

            Задача:
            Я загадаю одно слово — имя существительное на русском языке в именительном падеже.
            Ты будешь пытаться угадать его по буквам.

            Правила:
            - Вводи по одной русской букве за попытку. "Валидные" - только с маленькой буквы.
            - Если буква есть в слове — она откроется на всех позициях.
            - Если буквы в слове нет — ты получишь штраф (ошибку).
            - Всего допускается не более 6 ошибок.
            - Повторный ввод уже названной буквы не засчитывается как ошибка.

            Цель:
            Угадать всё слово до того, как человечек будет "повешен".

            Удачи! Начинаем игру.
            """);
    }
}
