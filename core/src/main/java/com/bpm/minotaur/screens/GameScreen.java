package com.bpm.minotaur.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bpm.minotaur.MinotaurGame;
import com.bpm.minotaur.debug.DebugManager;
import com.bpm.minotaur.input.InputHandler;
import com.bpm.minotaur.world.GameController;
import com.bpm.minotaur.world.GameWorld;
import com.bpm.minotaur.world.FirstPersonRenderer;
import com.bpm.minotaur.world.WorldRenderer;

/**
 * The main screen where the game is played.
 */
public class GameScreen extends BaseScreen {

    private final InputHandler inputHandler;
    private final WorldRenderer debugRenderer; // Renamed for clarity
    private final FirstPersonRenderer primaryRenderer; // Our new renderer
    private final GameWorld gameWorld;
    private final GameController gameController;

    public GameScreen(MinotaurGame game) {
        super(game);

        this.gameWorld = new GameWorld();
        this.gameController = new GameController(gameWorld);
        this.inputHandler = new InputHandler(gameController);

        // Initialize both renderers
        this.primaryRenderer = new FirstPersonRenderer(gameWorld);
        this.debugRenderer = new WorldRenderer(gameWorld);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void render(float delta) {
        inputHandler.handleContinuousInput(delta);
        gameController.update(delta);

        // Clear the screen with a black color.
        ScreenUtils.clear(0, 0, 0, 1);

        // Render the primary first-person view.
        primaryRenderer.render();

        // Conditionally render the debug 2D map on top.
        if (DebugManager.INSTANCE.isDebugOverlayVisible()) {
            debugRenderer.render();
        }
    }

    @Override
    public void dispose() {
        primaryRenderer.dispose();
        debugRenderer.dispose();
    }
}
