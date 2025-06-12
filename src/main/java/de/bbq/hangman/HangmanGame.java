package de.bbq.hangman;

import de.bbq.hangman.model.HangmanModel;
import de.bbq.hangman.view.HangmanView;
import de.bbq.hangman.controller.HangmanController;

/**
 * Main class to start the Hangman game.
 * Implements the entry point of the application and initializes the MVC components.
 *
 * @author Christos Poulios
 * @version 1.0
 */
public class HangmanGame {

    /**
     * The main entry point of the Hangman game.
     * Creates instances of Model, View, and Controller, then starts the game.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        HangmanModel model = new HangmanModel();
        HangmanView view = new HangmanView();
        HangmanController controller = new HangmanController(model, view);

        controller.startGame();
    }
}
