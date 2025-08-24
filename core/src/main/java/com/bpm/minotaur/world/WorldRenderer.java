package com.bpm.minotaur.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bpm.minotaur.model.Maze;
import com.bpm.minotaur.model.Player;
import com.bpm.minotaur.model.Tile;
import com.bpm.minotaur.model.TileType;

/**
 * Responsible for rendering the game world, including the maze, player, and monsters.
 */
public class WorldRenderer {

    private final GameWorld gameWorld;
    private final ShapeRenderer shapeRenderer;

    /**
     * Constructor for the WorldRenderer.
     * @param gameWorld The GameWorld object to render.
     */
    public WorldRenderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.shapeRenderer = new ShapeRenderer();
    }

    /**
     * Renders the game world.
     */
    public void render() {
        Maze maze = gameWorld.getMaze();
        Player player = gameWorld.getPlayer();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Render the maze
        for (int x = 0; x < Maze.WIDTH; x++) {
            for (int y = 0; y < Maze.HEIGHT; y++) {
                Tile tile = maze.getTile(x, y);
                if (tile.getType() == TileType.WALL) {
                    shapeRenderer.setColor(Color.GRAY);
                } else {
                    shapeRenderer.setColor(Color.WHITE);
                }
                shapeRenderer.rect(x * 20, y * 20, 20, 20);
            }
        }

        // Render the player
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(player.getX() * 20, player.getY() * 20, 20, 20);

        shapeRenderer.end();
    }

    /**
     * Disposes of resources used by the renderer.
     */
    public void dispose() {
        shapeRenderer.dispose();
    }
}
