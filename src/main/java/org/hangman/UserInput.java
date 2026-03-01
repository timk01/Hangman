package org.hangman;

import java.util.Scanner;

public class UserInput implements Input {
    private static final String YES = "д";
    private static final String NO = "н";

    private final Scanner scanner;

    public UserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public boolean askToPlay() {
        while (true) {
            System.out.printf("Начать новую игру? Введите %s для начала или %s для выхода.%n", YES, NO);
            String input = scanner.nextLine().trim().toLowerCase();
            if (YES.equals(input)) {
                return true;
            } else if (NO.equals(input)) {
                return false;
            } else {
                System.out.printf("Некорректный ввод. Пожалуйста, введите '%s' или '%s'.%n", YES, NO);
            }
        }
    }

    @Override
    public char getGuessedChar() {
        String input;
        do {
            System.out.println("Нужно загадать одну русскую букву");
            input = scanner.nextLine().trim().toLowerCase();
            if (input.length() == 1 && Validator.isCyrillicLetter(input.charAt(0))) {
                return input.charAt(0);
            }
            System.out.println("Некорректный ввод. Введите ОДНУ русскую букву.");
        } while (true);
    }
}
