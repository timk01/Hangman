package org.example;

import java.util.*;

public class Main {

    private static final List<String> HANGMAN_VIEW = new ArrayList<>();
    private static final String[] VOCABLUARY = {
            "карета"
/*            "пирожок",
            "гитара",
            "ежевика",
            "сумерки",
            "фонарик"*/
    };

    private static final int ERRORS_MAX = 6;


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

    private static String pickARanDomWorld() {
        Random random = new Random();
        return VOCABLUARY[random.nextInt(VOCABLUARY.length)];
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

    private static void hangmanEngine() {
        String word = pickARanDomWorld();
        int errors = 0;
        Set<Character> uniqueLetters = new HashSet<>();
        for (char c : word.toCharArray()) {
            uniqueLetters.add(c);
        }

        Set<Character> goodLetters = new HashSet<>();
        Set<Character> badLetters = new HashSet<>();
        Scanner scanner = new Scanner(System.in);

        Character guessedChar = '1';

        String emptyWordWithPlaceholders = String.valueOf(initEmptyWord(word));

        while (errors < ERRORS_MAX && isWordFullyGuessed(word, goodLetters)) {
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

            System.out.println("нужно загадать букву");
            if (scanner.hasNext()) {
                guessedChar = scanner.next().charAt(0);
            }
            System.out.println("загаданная буква: " + guessedChar);

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

    public static void main(String[] args) {
        initHangmanStages();
        boolean continueGame;
        Scanner scanner = new Scanner(System.in);

        do {
            hangmanEngine();
            System.out.println("Предполагается, что вы будете продолжать. Хотите сыграть еще ? Введите: д/н");
            String answer = scanner.nextLine().trim();
            continueGame = answer.equalsIgnoreCase("д");
        } while (continueGame);
    }

}