package org.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final List<String> HANGMAN_VIEW = new ArrayList<>();

    private static final List<String> DICTIONARY = new ArrayList<>();

    private static final String DICTIONARY_PATH = "src/main/resources/dictionary.txt";
    private static final int MAX_WORDS = 1000;

    private static final int ERRORS_MAX = 6;

    private static final Scanner scanner = new Scanner(System.in);
    
    private static void initHangmanStages() {

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

    private static boolean isCyrillicLetter(Character c) {
        return (c >= 'а' && c <= 'я') || c == 'ё';
    }

    private static boolean isWordValid(String line) {
        line = line.trim().toLowerCase();
        return line.chars()
                .allMatch(c -> isCyrillicLetter((char) c));
    }

    private static void loadWords() {
        try (BufferedReader buff = new BufferedReader(new FileReader(DICTIONARY_PATH))) {
            String line;
            while ((line = buff.readLine()) != null && DICTIONARY.size() < MAX_WORDS) {
                if (isWordValid(line)) {
                    DICTIONARY.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Не найден файл dictionary (словаря) по указанному пути");
            e.printStackTrace();
        }
    }

    private static String pickARanDomWorld() {
        if (DICTIONARY.isEmpty()) {
            throw new IllegalStateException("Словарь пуст");
        }
        Random random = new Random();
        return DICTIONARY.get(random.nextInt(DICTIONARY.size()));
    }

    private static void printHangman(int errors) {
        System.out.println(HANGMAN_VIEW.get(errors));
    }

    private static StringBuilder initEmptyWord(String word) {
        char[] charArray = word.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            if (i != charArray.length - 1) {
                sb.append("_ ");
            } else {
                sb.append("_");
            }
        }
        return sb;
    }

    private static StringBuilder buildRevealedWord(
            String initialWord, Set<Character> lettersToReplace) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < initialWord.length(); i++) {
            char c = initialWord.charAt(i);
            if (lettersToReplace.contains(c)) {
                sb.append(c);
            } else {
                sb.append("_");
            }

            if (i != initialWord.length() - 1) {
                sb.append(" ");
            }
        }
        return sb;
    }

    private static char getGuessedChar() {
        String word;
        do {
            System.out.println("Нужно загадать одну русскую букву");
            word = scanner.nextLine().trim().toLowerCase();
            if (word.length() == 1 && isCyrillicLetter(word.charAt(0))) {
                return word.charAt(0);
            }
            System.out.println("Некорректный ввод. Введите ОДНУ русскую букву.");
        } while (true);
    }

    private static void hangmanEngine() {
        String word = pickARanDomWorld();
        int errors = 0;
        Set<Character> uniqueLetters = word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());

        Set<Character> goodLetters = new HashSet<>();
        Set<Character> badLetters = new HashSet<>();

        String emptyWordWithPlaceholders = String.valueOf(initEmptyWord(word));

        while (errors < ERRORS_MAX && !isWordFullyGuessed(word, goodLetters)) {
            printHangman(errors);

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

            char guessedChar = getGuessedChar();

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

        if (isWordFullyGuessed(word, goodLetters)) {
            System.out.println("Поздравляем! Вы угадали слово: " + word);
        } else {
            printHangman(errors);
            System.out.println("Вы проиграли. Было загадано слово: " + word);
        }
    }

    private static boolean isWordFullyGuessed(String word, Set<Character> goodLetters) {
        return buildRevealedWord(word, goodLetters).toString().replace(" ", "").equals(word);
    }

    private static void printGameIntro() {
        System.out.println("""
            Привет! Добро пожаловать в игру "Виселица".

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

    public static void main(String[] args) {
        initHangmanStages();
        loadWords();
        printGameIntro();

        boolean continueGame;

        do {
            hangmanEngine();
            System.out.println("Предполагается, что вы будете продолжать. Хотите сыграть еще ? Введите: д/н");
            String answer = scanner.nextLine().trim();
            continueGame = answer.equalsIgnoreCase("д");
        } while (continueGame);

        scanner.close();
    }

}