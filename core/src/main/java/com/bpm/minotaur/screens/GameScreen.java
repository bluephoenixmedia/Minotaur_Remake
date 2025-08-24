package com.bpm.minotaur.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bpm.minotaur.MinotaurGame;
import com.bpm.minotaur.input.InputHandler;
import com.bpm.minotaur.world.GameController;
import com.bpm.minotaur.world.GameWorld;
import com.bpm.minotaur.world.WorldRenderer;

/**
 * The main screen where the game is played.
 * This screen will handle the rendering of the maze, player, monsters, and UI.
 */
public class GameScreen extends BaseScreen {

    private final InputHandler inputHandler;
    private final WorldRenderer worldRenderer;
    private final GameWorld gameWorld;
    private final GameController gameController;

    /**
     * Constructor for the game screen.
     * @param game The main game instance.
     */
    public GameScreen(MinotaurGame game) {
        super(game);

        // Create the world and controller.
        this.gameWorld = new GameWorld();
        this.gameController = new GameController(gameWorld);

        // Create the input handler and renderer, passing them the necessary components.
        this.inputHandler = new InputHandler(gameController);
        this.worldRenderer = new WorldRenderer(gameWorld);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void render(float delta) {
        // Note: For now, we are calling moveForward from handleContinuousInput.
        // This will result in very fast movement. We will fix this in a later step
        // by adding a delay/timer to movement.
        inputHandler.handleContinuousInput(delta);

        // Update the game logic.
        gameController.update(delta);

        // Clear the screen with a black color.
        ScreenUtils.clear(0, 0, 0, 1);

        // Tell the WorldRenderer to draw the world.
        worldRenderer.render();
    }

    @Override
    public void dispose() {
        worldRenderer.dispose();
    }
}
