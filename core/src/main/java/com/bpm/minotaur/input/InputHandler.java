package com.bpm.minotaur.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * Handles all player input for the game.
 * This class processes keyboard and mouse events and translates them into game actions.
 * It extends InputAdapter to selectively override only the methods we need.
 */
public class InputHandler extends InputAdapter {

    /**
     * This method is called by the libGDX framework whenever a key is pressed.
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyDown(int keycode) {
        // We will use this method to handle single-press actions,
        // like opening a door or using an item.

        switch (keycode) {
            case Input.Keys.ESCAPE:
                // Exit the game when the Escape key is pressed.
                Gdx.app.exit();
                return true;
        }

        return false;
    }

    /**
     * This method is called in the main game loop to handle continuous input,
     * such as holding down a key to move.
     */
    public void handleContinuousInput(float delta) {
        // We use isKeyPressed for movement because it checks if a key is currently held down,
        // which feels more natural for movement than the single-press keyDown event.

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            // Placeholder for moving forward
            Gdx.app.log("InputHandler", "Move Forward");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            // Placeholder for turning left
            Gdx.app.log("InputHandler", "Turn Left");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            // Placeholder for turning right
            Gdx.app.log("InputHandler", "Turn Right");
        }
    }
}
