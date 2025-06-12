package de.bbq.hangman.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class for the Hangman game.
 * Contains the game logic and state management for the Hangman game.
 * This class is responsible for maintaining the game state and processing game moves.
 *
 * @author Christos Poulios
 * @version 1.0
 */
public class HangmanModel {
    private String wordToGuess;
    private StringBuilder currentDisplay;
    private int remainingLives;
    private List<Character> guessedLetters;
    private boolean gameWon;
    private boolean gameOver;

    /**
     * Initializes a new game with the given word.
     * Sets up the initial game state including lives, display mask, and resets all game flags.
     *
     * @param word The word that players need to guess
     * @throws IllegalArgumentException if the word is null or empty
     */
    public void initializeGame(String word) {
        if (word == null || word.trim().isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        this.wordToGuess = word.toLowerCase();
        this.remainingLives = 10;
        this.guessedLetters = new ArrayList<>();
        this.gameWon = false;
        this.gameOver = false;
        createMaskedWord();
    }

    /**
     * Creates the initially masked version of the word.
     * Shows the first letter and replaces all other letters with underscores.
     * This method is called during game initialization.
     */
    private void createMaskedWord() {
        currentDisplay = new StringBuilder();
        currentDisplay.append(wordToGuess.charAt(0));
        currentDisplay.append("_".repeat(wordToGuess.length() - 1));
    }

    /**
     * Processes a letter guess and updates the game state accordingly.
     *
     * @param letter The letter being guessed
     * @return true if the letter was found in the word, false otherwise
     */
    public boolean guessLetter(char letter) {
        letter = Character.toLowerCase(letter);
        if (guessedLetters.contains(letter)) {
            return false;
        }

        guessedLetters.add(letter);
        boolean letterFound = false;

        for (int i = 1; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == letter) {
                currentDisplay.setCharAt(i, letter);
                letterFound = true;
            }
        }

        if (!letterFound) {
            remainingLives--;
        }

        checkGameState();
        return letterFound;
    }

    /**
     * Processes a complete word guess.
     *
     * @param word The complete word being guessed
     * @return true if the guess was correct, false otherwise
     */
    public boolean guessWord(String word) {
        word = word.toLowerCase();
        if (word.equals(wordToGuess)) {
            currentDisplay = new StringBuilder(wordToGuess);
            gameWon = true;
            return true;
        }
        remainingLives--;
        checkGameState();
        return false;
    }

    /**
     * Checks and updates the current game state.
     * Determines if the game has been won or lost.
     */
    private void checkGameState() {
        if (remainingLives <= 0) {
            gameOver = true;
        }
        if (currentDisplay.toString().equals(wordToGuess)) {
            gameWon = true;
        }
    }

    /**
     * Gets the current display state of the word.
     *
     * @return String representing the current state of the word with unguessed letters as underscores
     */
    public String getCurrentDisplay() {
        return currentDisplay.toString();
    }

    /**
     * Gets the number of remaining lives.
     *
     * @return int representing the number of remaining attempts
     */
    public int getRemainingLives() {
        return remainingLives;
    }

    /**
     * Gets the list of letters that have been guessed.
     *
     * @return List of Characters that have been guessed so far
     */
    public List<Character> getGuessedLetters() {
        return new ArrayList<>(guessedLetters);
    }

    /**
     * Gets the word that needs to be guessed.
     *
     * @return String containing the target word
     */
    public String getWordToGuess() {
        return wordToGuess;
    }

    /**
     * Checks if the game has been won.
     *
     * @return true if the game has been won, false otherwise
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over (either won or lost), false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Checks if a letter has already been guessed.
     * @param letter The letter to check
     * @return true if the letter has been guessed before, false otherwise
     */
    public boolean hasBeenGuessed(char letter) {
        return guessedLetters.contains(Character.toLowerCase(letter));
    }
}
