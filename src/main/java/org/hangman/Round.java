package org.hangman;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hangman.WordUtils.*;

public class Round {

    private final int maxErrors;
    private final Input input;
    private final Dictionary dictionary;
    private final View view;

    public Round(int maxErrors, Input input, Dictionary dictionary, View view) {
        this.maxErrors = maxErrors;
        this.input = input;
        this.dictionary = dictionary;
        this.view = view;
    }

    public boolean hangmanEngine() {
        String word = dictionary.getARandomWord();

        Set<Character> uniqueWordLetters = extractUniqueLetters(word);

        Set<Character> goodLetters = new HashSet<>();
        Set<Character> badLetters = new HashSet<>();

        String emptyWordWithPlaceholders = String.valueOf(initEmptyWord(word));

        int roundErrors = progressRoundLoop(word, goodLetters,
                emptyWordWithPlaceholders, badLetters, uniqueWordLetters);

        return finishRound(word, goodLetters, roundErrors);
    }

    private Set<Character> extractUniqueLetters(String word) {
        return word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());
    }

    private int progressRoundLoop(String word, Set<Character> goodLetters, String emptyWordWithPlaceholders, Set<Character> badLetters, Set<Character> uniqueWordLetters) {
        int roundErrors = 0;
        while (roundErrors < maxErrors && !isWordFullyGuessed(word, goodLetters)) {
            view.printStage(roundErrors);

            if (goodLetters.isEmpty()) {
                view.printCurrentWordState(emptyWordWithPlaceholders);
            } else {
                view.printCurrentWordState(buildRevealedWord(word, goodLetters).toString());
            }

            view.printErrors(roundErrors, badLetters);

            char guessedChar = input.getGuessedChar();

            if (goodLetters.contains(guessedChar) || badLetters.contains(guessedChar)) {
                view.printMessage("Вы уже называли эту букву. Попробуйте другую.");
                continue;
            }

            view.printMessage("Загаданная буква: " + guessedChar);

            if (uniqueWordLetters.contains(guessedChar)) {
                goodLetters.add(guessedChar);
            } else {
                badLetters.add(guessedChar);
                roundErrors++;
            }
        }
        return roundErrors;
    }

    private boolean finishRound(String word, Set<Character> goodLetters, int roundErrors) {
        boolean roundResult = isWin(word, goodLetters);
        if (!roundResult) {
            view.printStage(roundErrors);
        }
        view.printRoundResult(word, roundResult);
        return roundResult;
    }

    private boolean isWin(String word, Set<Character> goodLetters) {
        return isWordFullyGuessed(word, goodLetters);
    }

}
