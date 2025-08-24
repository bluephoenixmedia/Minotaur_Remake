package com.bpm.minotaur.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.bpm.minotaur.world.GameController;

/**
 * Handles all player input for the game.
 * This class processes keyboard and mouse events and translates them into game actions.
 * It extends InputAdapter to selectively override only the methods we need.
 */
public class InputHandler extends InputAdapter {

    private final GameController gameController;

    /**
     * Constructor for the InputHandler.
     * @param gameController The controller that will execute game actions.
     */
    public InputHandler(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public boolean keyDown(int keycode) {
        // We only handle non-movement, single-press actions here now.
        if (keycode == Input.Keys.ESCAPE) {
            Gdx.app.exit();
            return true;
        }
        return false;
    }

    /**
     * This method is called in the main game loop to handle continuous input,
     * such as holding down a key to move or turn.
     */
    public void handleContinuousInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            gameController.tryMoveForward();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            gameController.tryTurnLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            gameController.tryTurnRight();
        }
    }
}

