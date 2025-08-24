package com.bpm.minotaur.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bpm.minotaur.MinotaurGame;
import com.bpm.minotaur.input.InputHandler;
import com.bpm.minotaur.model.Maze;
import com.bpm.minotaur.world.MazeGenerator;
import com.bpm.minotaur.world.WorldRenderer;

/**
 * The main screen where the game is played.
 * This screen will handle the rendering of the maze, player, monsters, and UI.
 */
public class GameScreen extends BaseScreen {

    private final InputHandler inputHandler;
    private final MazeGenerator mazeGenerator;
    private final WorldRenderer worldRenderer;
    private final Maze maze;

    /**
     * Constructor for the game screen.
     * @param game The main game instance.
     */
    public GameScreen(MinotaurGame game) {
        super(game);
        this.inputHandler = new InputHandler();

        // Create a new MazeGenerator.
        this.mazeGenerator = new MazeGenerator();

        // Generate the first level of the maze.
        this.maze = mazeGenerator.generate(1);

        // Create a WorldRenderer to draw our maze.
        this.worldRenderer = new WorldRenderer(maze);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void render(float delta) {
        inputHandler.handleContinuousInput(delta);

        // Clear the screen with a black color.
        ScreenUtils.clear(0, 0, 0, 1);

        // Tell the WorldRenderer to draw the maze.
        worldRenderer.render();
    }

    @Override
    public void dispose() {
        // Dispose of the renderer's resources when this screen is closed.
        worldRenderer.dispose();
    }
}
