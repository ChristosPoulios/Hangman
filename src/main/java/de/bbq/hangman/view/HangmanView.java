package de.bbq.hangman.view;

import java.util.List;

/**
 * View class for the Hangman game.
 * Handles all visual output and display formatting for the game.
 *
 * @author Christos Poulios
 * @version 1.0
 */
public class HangmanView {

    /**  Constants  */
    private static final String WELCOME = "Willkommen beim Galgenmännchen-Spiel!";
    private static final String ENTER_WORD = "Bitte gib das zu ratende Wort ein:";
    private static final String ENTER_LETTER = "Rate einen Buchstaben oder gib das komplette Wort ein:";
    private static final String WRONG = "Falsch! Du verlierst ein Leben.";
    private static final String CORRECT = "Richtig! Der Buchstabe ist im Wort enthalten.";
    private static final String WON = "Glückwunsch! Du hast das Wort erraten!";
    private static final String LOST = "Spiel vorbei! Du hast keine Leben mehr. Das Wort war: ";
    private static final String PLAY_AGAIN = "Möchtest du noch einmal spielen? (j/n)";
    private static final String ALREADY_GUESSED = "Diesen Buchstaben hast du bereits geraten!";
    private static final String[] GALLOWS_STATES = {
            "",
            "\n\n\n\n\n____",
            "\n |\n |\n |\n |\n_|___",
            " ______\n |\n |\n |\n |\n_|___",
            " ______\n |    |\n |\n |\n |\n_|___",
            " ______\n |    |\n |    O\n |\n |\n_|___",
            " ______\n |    |\n |    O\n |    |\n |\n_|___",
            " ______\n |    |\n |    O\n |   /|\n |\n_|___",
            " ______\n |    |\n |    O\n |   /|\\\n |\n_|___",
            " ______\n |    |\n |    O\n |   /|\\\n |   /\n_|___",
    };
    private static final String GAME_OVER_GALLOWS = " ______\n |    |\n |    O\n |   /|\\\n |   / \\\n_|___\nRIP 💀";
    private static final String CHOOSE_MODE = """
        Wähle den Spielmodus:
        1) Eigenes Wort eingeben
        2) Zufälliges Wort verwenden
        Bitte gib 1 oder 2 ein:""";
    private static final String INVALID_MODE = "Ungültige Eingabe! Bitte wähle 1 oder 2.";

    /**
     * Displays the mode selection prompt.
     */
    public void promptForMode() {
        System.out.println(CHOOSE_MODE);
    }

    /**
     * Displays a message for invalid mode selection.
     */
    public void showInvalidMode() {
        System.out.println(INVALID_MODE);
    }

    /**
     * Displays the welcome message to the player.
     */
    public void showWelcome() {
        System.out.println(WELCOME);
    }

    /**
     * Prompts the player to enter the word to guess.
     */
    public void promptForWord() {
        System.out.println(ENTER_WORD);
    }

    /**
     * Prompts the player to guess a letter or the complete word.
     */
    public void promptForGuess() {
        System.out.println(ENTER_LETTER);
    }

    /**
     * Display a message for a correct guess
     */
    public void showCorrectGuess() {
        System.out.println(CORRECT);
    }

    /**
     * Display a message for a wrong guess
     */
    public void showWrongGuess() {
        System.out.println(WRONG);
    }

    /**
     * Displays a message when a letter has already been guessed.
     */
    public void showAlreadyGuessed() {
        System.out.println(ALREADY_GUESSED);
    }

    /**
     * Displays a winning message when the game is won
     */
    public void showGameWon() {
        System.out.println(WON);
    }

    /**
     * Displays a losing message and reveals the word when the game is lost.
     *
     * @param word The word that was to be guessed
     */
    public void showGameLost(String word) {
        System.out.println(GAME_OVER_GALLOWS);
        System.out.println(LOST + word);
    }

    /**
     * Prompts the player to play again.
     */
    public void promptPlayAgain() {
        System.out.println(PLAY_AGAIN);
    }

    /**
     * Displays the current game state including the gallows, word progress, and game statistics.
     *
     * @param currentWord The current state of the word with revealed letters
     * @param lives The number of remaining lives
     * @param guessedLetters List of already guessed letters
     */
    public void showGameState(String currentWord, int lives, List<Character> guessedLetters) {
        System.out.println("\n");
        showGallows(lives);
        System.out.println("\nAktuelles Wort: " + currentWord);
        System.out.println("Verbleibende Leben: " + lives);
        System.out.println("Bereits geraten: " + guessedLetters);
    }

    /**
     * Displays the gallows ASCII art based on remaining lives.
     */
    public void showGallows(int remainingLives) {
        int index = 10 - remainingLives;
        index = Math.min(Math.max(index, 0), GALLOWS_STATES.length - 1);
        System.out.println(GALLOWS_STATES[index]);
    }
}

