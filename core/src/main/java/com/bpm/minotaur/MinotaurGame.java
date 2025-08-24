package com.bpm.minotaur;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bpm.minotaur.screens.MainMenuScreen; // Import the MainMenuScreen class

/**
 * The main entry point of the Minotaur game.
 * This class extends the libGDX Game class, which helps manage different screens.
 * It is responsible for creating essential game-wide objects and setting the initial screen.
 */
public class MinotaurGame extends Game {

    // A SpriteBatch is used to draw 2D images, like textures and sprites.
    // We create one here to be used by all screens, which is efficient.
    public SpriteBatch batch;

    /**
     * This method is called once when the application is created.
     */
    @Override
    public void create() {
        // Initialize the SpriteBatch.
        batch = new SpriteBatch();

        // Set the MainMenuScreen as the first screen to be displayed.
        this.setScreen(new MainMenuScreen(this));
    }



    /**
     * This method is called when the application is closing.
     * It's important to dispose of resources to prevent memory leaks.
     */
    @Override
    public void dispose() {
        // Dispose of the SpriteBatch when the game is closed.
        if (batch != null) {
            batch.dispose();
        }
        // Also, make sure to dispose the current screen's resources.
        if (screen != null) {
            screen.dispose();
        }
    }
}
