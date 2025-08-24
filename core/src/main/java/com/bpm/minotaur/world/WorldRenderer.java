package com.bpm.minotaur.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bpm.minotaur.model.Maze;
import com.bpm.minotaur.model.Tile;
import com.bpm.minotaur.model.TileType;

/**
 * Responsible for rendering the game world, including the maze, player, and monsters.
 */
public class WorldRenderer {

    private final Maze maze;
    private final ShapeRenderer shapeRenderer;

    /**
     * Constructor for the WorldRenderer.
     * @param maze The Maze object to render.
     */
    public WorldRenderer(Maze maze) {
        this.maze = maze;
        this.shapeRenderer = new ShapeRenderer();
    }

    /**
     * Renders the game world.
     */
    public void render() {
        // We will use a ShapeRenderer to draw simple, colored rectangles for our 2D map.
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Iterate through every tile in the maze
        for (int x = 0; x < Maze.WIDTH; x++) {
            for (int y = 0; y < Maze.HEIGHT; y++) {
                Tile tile = maze.getTile(x, y);
                if (tile.getType() == TileType.WALL) {
                    shapeRenderer.setColor(Color.GRAY); // Walls are gray
                } else {
                    shapeRenderer.setColor(Color.WHITE); // Floors are white
                }
                // Draw a rectangle for each tile. We'll scale it up to be visible.
                shapeRenderer.rect(x * 20, y * 20, 20, 20);
            }
        }

        shapeRenderer.end();
    }

    /**
     * Disposes of resources used by the renderer.
     */
    public void dispose() {
        shapeRenderer.dispose();
    }
}
