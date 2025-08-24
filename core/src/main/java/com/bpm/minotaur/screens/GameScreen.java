package com.bpm.minotaur.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bpm.minotaur.MinotaurGame;

/**
 * The main screen where the game is played.
 * This screen will handle the rendering of the maze, player, monsters, and UI.
 */
public class GameScreen extends BaseScreen {

    /**
     * Constructor for the game screen.
     * @param game The main game instance.
     */
    public GameScreen(MinotaurGame game) {
        super(game);
    }

    @Override
    public void show() {
        // Log a message to the console to confirm the screen has changed.
        Gdx.app.log("GameScreen", "Screen Shown");
    }

    @Override
    public void render(float delta) {
        // Clear the screen with the floor/ceiling color from our design document.
        // RGB values are derived from #5B602F.
        ScreenUtils.clear(91f / 255f, 96f / 255f, 47f / 255f, 1);
    }

    @Override
    public void dispose() {
        // We will add resources to dispose of here later.
    }
}
