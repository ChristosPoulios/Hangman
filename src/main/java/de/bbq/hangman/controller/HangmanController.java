package de.bbq.hangman.controller;

import de.bbq.hangman.model.HangmanModel;
import de.bbq.hangman.model.WordProvider;
import de.bbq.hangman.view.HangmanView;
import de.bbq.hangman.model.ComputerGuesser;

import java.util.Scanner;

/**
 * Controller class for the Hangman game.
 * Manages game flow, user input, and coordinates between Model and View.
 *
 * @author Christos Poulios
 * @version 1.0
 */
public class HangmanController {
    private final HangmanModel model;
    private final HangmanView view;
    private final ComputerGuesser computerGuesser;
    private final Scanner scanner;
    private final WordProvider wordProvider;
    private boolean isComputerMode;

    /**
     * Constructs a HangmanController with the specified model and view.
     *
     * @param model The game model containing the game logic
     * @param view  The game view handling user interface
     * @throws IllegalArgumentException if model or view is null
     */
    public HangmanController(HangmanModel model, HangmanView view) {
        if (model == null || view == null) {
            throw new IllegalArgumentException("Model and View cannot be null");
        }
        this.model = model;
        this.view = view;
        this.computerGuesser = new ComputerGuesser();
        this.scanner = new Scanner(System.in);
        this.wordProvider = new WordProvider();
    }

    /**
     * Prompts the user to choose a game mode and returns the word to guess.
     * Handles both computer guessing mode and user input mode.
     *
     * @return The word to guess in the game
     */
    private String getGameWord() {
        while (true) {
            view.promptForMode();
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    isComputerMode = true;
                    return startComputerGuessMode();
                case "2":
                    isComputerMode = false;
                    return wordProvider.getRandomWord();
                default:
                    view.showInvalidMode();
            }
        }
    }


    /**
     * Starts and controls the main game loop.
     * This method manages the overall flow of the game, including multiple rounds
     * and game restarts.
     */
    public void startGame() {
        boolean playing = true;
        while (playing) {
            playOneGame();
            playing = askForNewGame();
        }
        scanner.close();
    }

    /**
     * Manages a single game session from start to finish.
     * Includes word input, game loop, and end game conditions.
     */
    private void playOneGame() {
        view.showWelcome();
        String word = getGameWord();

        if (isComputerMode) {
            if (model.isGameWon()) {
                view.showComputerWon();
            } else if (model.isGameOver()) {
                view.showGameLost(model.getWordToGuess());
            }
            return;
        }

        // Normal game mode
        model.initializeGame(word);

        while (!model.isGameOver() && !model.isGameWon()) {
            view.showGameState(
                    model.getCurrentDisplay(),
                    model.getRemainingLives(),
                    model.getGuessedLetters()
            );

            processGuess();
        }

        if (model.isGameWon()) {
            view.showGameWon();
        } else if (model.isGameOver()) {
            view.showGameLost(model.getWordToGuess());
        }
    }

    /**
     * Processes a single guess from the user.
     * Handles both single letter guesses and full word guesses.
     */
    private void processGuess() {
        view.promptForGuess();
        String input = scanner.nextLine().toLowerCase();


        if (input.length() == 1) {
            char guess = input.charAt(0);

            if (model.hasBeenGuessed(guess)) {
                view.showAlreadyGuessed();
                return;
            }

            if (model.guessLetter(guess)) {
                view.showCorrectGuess();
            } else {
                view.showWrongGuess();
            }
        }

        else {
            if (model.guessWord(input)) {
                view.showCorrectGuess();
            } else {
                view.showWrongGuess();
            }
        }
    }

    /**
     * Asks the user if they want to play another game.
     *
     * @return true if the user wants to play again, false otherwise
     */
    private boolean askForNewGame() {
        view.promptPlayAgain();
        String answer = scanner.nextLine().toLowerCase();
        return answer.startsWith("j");
    }

    /**
     * Starts the computer guess mode where the computer tries to guess a user's word.
     *
     * @return The word that was guessed
     */
    private String startComputerGuessMode() {
        view.promptForWord();
        String userWord = scanner.nextLine();

        computerGuesser.initializeGuesser();
        model.initializeGame(userWord);

        view.showComputerStartsGuessing();

        while (!model.isGameOver() && !model.isGameWon() && computerGuesser.hasMoreLetters()) {
            view.showGameState(
                    model.getCurrentDisplay(),
                    model.getRemainingLives(),
                    model.getGuessedLetters()
            );

            char computerGuess = computerGuesser.getNextLetterGuess();
            view.showComputerLetterGuess(computerGuess);

            if (model.hasBeenGuessed(computerGuess)) {
                continue;
            }

            if (model.guessLetter(computerGuess)) {
                view.showCorrectGuess();
            } else {
                view.showWrongGuess();
            }

            computerGuesser.updatePossibleWords(
                    model.getCurrentDisplay(),
                    model.getGuessedLetters()
            );

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (!model.isGameWon() && !model.isGameOver() && computerGuesser.hasMoreLetters()) {
            String wordGuess = computerGuesser.getWordGuess(model.getCurrentDisplay());
            view.showComputerWordGuess(wordGuess);
            model.guessWord(wordGuess);
        }

        return userWord;
    }

}