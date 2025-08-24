package com.bpm.minotaur.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bpm.minotaur.MinotaurGame;
import com.bpm.minotaur.input.InputHandler; // Import the InputHandler

/**
 * The main screen where the game is played.
 * This screen will handle the rendering of the maze, player, monsters, and UI.
 */
public class GameScreen extends BaseScreen {

    // A reference to our input handler.
    private final InputHandler inputHandler;

    /**
     * Constructor for the game screen.
     * @param game The main game instance.
     */
    public GameScreen(MinotaurGame game) {
        super(game);
        // Create a new instance of our InputHandler.
        this.inputHandler = new InputHandler();
    }

    @Override
    public void show() {
        // Set our InputHandler as the official input processor for the game.
        // This allows it to receive events like keyDown, keyUp, etc.
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void render(float delta) {
        // Handle continuous input (like holding down movement keys) each frame.
        inputHandler.handleContinuousInput(delta);

        // Clear the screen with the floor/ceiling color from our design document.
        // RGB values are derived from #5B602F.
        ScreenUtils.clear(91f / 255f, 96f / 255f, 47f / 255f, 1);
    }

    @Override
    public void dispose() {
        // We will add resources to dispose of here later.
    }
}
