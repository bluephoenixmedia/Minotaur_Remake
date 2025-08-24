package com.bpm.minotaur.screens;

import com.badlogic.gdx.Screen;
import com.bpm.minotaur.MinotaurGame;

/**
 * An abstract base class for all screens in the game.
 * This class implements the libGDX Screen interface and provides a basic structure
 * that all other game screens (MainMenuScreen, GameScreen, etc.) will extend.
 * It holds a reference to the main game class to allow screens to interact with it
 * (e.g., to switch to another screen).
 */
public abstract class BaseScreen implements Screen {

    // A reference to the main game class.
    protected final MinotaurGame game;

    /**
     * Constructor to be called by subclasses.
     * @param game The main game instance.
     */
    public BaseScreen(MinotaurGame game) {
        this.game = game;
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
        // This can be overridden by subclasses to set up the screen.
    }

    /**
     * Called when the screen should render itself.
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        // This will be implemented by each specific screen to draw its content.
    }

    /**
     * Called when the game window is resized.
     * @param width The new width.
     * @param height The new height.
     */
    @Override
    public void resize(int width, int height) {
        // This can be overridden to handle window resizing.
    }

    /**
     * Called when the application is paused.
     */
    @Override
    public void pause() {
        // This can be overridden to handle pausing.
    }

    /**
     * Called when the application is resumed from a paused state.
     */
    @Override
    public void resume() {
        // This can be overridden to handle resuming.
    }

    /**
     * Called when this screen is no longer the current screen for a Game.
     */
    @Override
    public void hide() {
        // This can be overridden to clean up when the screen is hidden.
    }

    /**
     * Called when this screen should release all its resources.
     */
    @Override
    public void dispose() {
        // This must be implemented by subclasses to dispose of their resources.
    }
}
