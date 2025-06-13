package de.bbq.hangman.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Computer guesser for the Hangman game.
 * Implements logic for the computer to guess words intelligently.
 *
 * @author Christos Poulios
 * @version 1.0
 */
public class ComputerGuesser {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzäöüß";
    private static final String[] COMMON_LETTERS = {"m", "e", "k", "a", "h", "c", "z", "i", "u", "f", "r", "g", "d", "w", "s", "o", "l", "n", "b", "t"};

    private final WordProvider wordProvider;
    private final Random random;
    private List<Character> availableLetters;
    private List<String> possibleWords;
    private int currentLetterIndex;

    /**
     * Constructs a new ComputerGuesser.
     */
    public ComputerGuesser() {
        this.wordProvider = new WordProvider();
        this.random = new Random();
        initializeGuesser();
    }

    /**
     * Initializes the guesser for a new game.
     */
    public void initializeGuesser() {
        this.availableLetters = new ArrayList<>();
        for (char c : ALPHABET.toCharArray()) {
            availableLetters.add(c);
        }
        this.currentLetterIndex = 0;
        this.possibleWords = getAllPossibleWords();
    }

    /**
     * Gets the next letter guess from the computer.
     *
     * @return The next letter to guess
     */
    public char getNextLetterGuess() {
        if (currentLetterIndex < COMMON_LETTERS.length) {
            char guess = COMMON_LETTERS[currentLetterIndex].charAt(0);
            currentLetterIndex++;
            availableLetters.remove(Character.valueOf(guess));
            return guess;
        }

        if (!availableLetters.isEmpty()) {
            int randomIndex = random.nextInt(availableLetters.size());
            return availableLetters.remove(randomIndex);
        }

        return 'a'; // Fallback
    }

    /**
     * Gets a word guess from the computer.
     *
     * @param currentDisplay The current state of the word
     * @return A word guess
     */
    public String getWordGuess(String currentDisplay) {
        List<String> matchingWords = filterWordsByPattern(currentDisplay);
        if (!matchingWords.isEmpty()) {
            return matchingWords.get(random.nextInt(matchingWords.size()));
        }
        return wordProvider.getRandomWord();
    }

    /**
     * Updates the possible words based on the current game state.
     *
     * @param currentDisplay The current display of the word
     * @param guessedLetters Letters that have been guessed
     */
    public void updatePossibleWords(String currentDisplay, List<Character> guessedLetters) {
        possibleWords = filterWordsByPattern(currentDisplay);
        possibleWords.removeIf(word -> !isWordCompatible(word, guessedLetters));
    }

    /**
     * Filters words by the current pattern.
     *
     * @param pattern The current word pattern
     * @return List of matching words
     */
    private List<String> filterWordsByPattern(String pattern) {
        List<String> matching = new ArrayList<>();
        for (String word : possibleWords) {
            if (matchesPattern(word.toLowerCase(), pattern.toLowerCase())) {
                matching.add(word);
            }
        }
        return matching;
    }

    /**
     * Checks if a word matches the current pattern.
     *
     * @param word The word to check
     * @param pattern The pattern to match against
     * @return true if the word matches the pattern
     */
    private boolean matchesPattern(String word, String pattern) {
        if (word.length() != pattern.length()) {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            char wordChar = word.charAt(i);
            char patternChar = pattern.charAt(i);

            if (patternChar != '_' && patternChar != wordChar) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a word is compatible with guessed letters.
     *
     * @param word The word to check
     * @param guessedLetters Letters that have been guessed
     * @return true if the word is compatible
     */
    private boolean isWordCompatible(String word, List<Character> guessedLetters) {
        for (char letter : guessedLetters) {
            if (word.toLowerCase().contains(String.valueOf(letter))) {
                return true;
            }
        }
        return guessedLetters.isEmpty();
    }

    /**
     * Gets all possible words from the word provider.
     *
     * @return List of all possible words
     */
    private List<String> getAllPossibleWords() {
        List<String> words = new ArrayList<>();
        String[] commonWords = {
                "Haus", "Baum", "Auto", "Tisch", "Buch", "Katze", "Hund", "Maus",
                "Ball", "Stuhl", "Fenster", "Garten", "Schule", "Bleistift",
                "Telefon", "Computer", "Zeitung", "Kühlschrank", "Schlüssel",
                "Brille", "Fahrrad", "Apfel", "Banane", "Schokolade", "Kaffee"
        };

        Collections.addAll(words, commonWords);
        return words;
    }

    /**
     * Checks if there are more letters available to guess.
     *
     * @return true if more letters are available
     */
    public boolean hasMoreLetters() {
        return !availableLetters.isEmpty() || currentLetterIndex < COMMON_LETTERS.length;
    }
}
