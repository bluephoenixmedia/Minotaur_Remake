package com.bpm.minotaur.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bpm.minotaur.model.Direction;
import com.bpm.minotaur.model.Maze;
import com.bpm.minotaur.model.Player;
import com.bpm.minotaur.model.Tile;
import com.bpm.minotaur.model.TileType;

/**
 * Responsible for rendering the game world from a first-person perspective.
 */
public class FirstPersonRenderer {

    private final GameWorld gameWorld;
    private final ShapeRenderer shapeRenderer;

    // Define the colors from our design document
    private final Color floorColor = new Color(0x5B602FFF);
    private final Color darkWallColor = new Color(0x005945FF);
    private final Color lightWallColor = new Color(0x00A95AFF);

    /**
     * Constructor for the FirstPersonRenderer.
     * @param gameWorld The game world to render.
     */
    public FirstPersonRenderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.shapeRenderer = new ShapeRenderer();
    }

    /**
     * Renders the first-person view.
     */
    public void render() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw the floor and ceiling
        shapeRenderer.setColor(floorColor);
        shapeRenderer.rect(0, 0, width, height / 2f); // Floor
        shapeRenderer.rect(0, height / 2f, width, height / 2f); // Ceiling

        // --- Render distant walls (2 tiles away) ---
        if (isWallAtRelative(0, 2)) {
            shapeRenderer.setColor(darkWallColor);
            shapeRenderer.rect(width * 0.3f, height * 0.3f, width * 0.4f, height * 0.4f);
        }

        // --- Render closer walls (1 tile away) ---
        if (isWallAtRelative(0, 1)) {
            shapeRenderer.setColor(darkWallColor);
            shapeRenderer.rect(width * 0.1f, height * 0.1f, width * 0.8f, height * 0.8f);
        } else {
            // If there's no wall directly in front, draw the corridor ahead
            shapeRenderer.setColor(floorColor);
            // Draw distant floor
            shapeRenderer.rect(width * 0.1f, height * 0.1f, width * 0.8f, height * 0.2f);
            // Draw distant ceiling
            shapeRenderer.rect(width * 0.1f, height * 0.7f, width * 0.8f, height * 0.2f);

            if (isWallAtRelative(-1, 1)) { // Wall to the left, one tile ahead
                shapeRenderer.setColor(lightWallColor);
                shapeRenderer.rect(width * 0.1f, height * 0.1f, width * 0.2f, height * 0.8f);
            }
            if (isWallAtRelative(1, 1)) { // Wall to the right, one tile ahead
                shapeRenderer.setColor(lightWallColor);
                shapeRenderer.rect(width * 0.7f, height * 0.1f, width * 0.2f, height * 0.8f);
            }
        }

        // --- Render immediate side walls (current tile) ---
        if (isWallAtRelative(-1, 0)) {
            shapeRenderer.setColor(lightWallColor);
            shapeRenderer.rect(0, 0, width * 0.1f, height);
        }
        if (isWallAtRelative(1, 0)) {
            shapeRenderer.setColor(lightWallColor);
            shapeRenderer.rect(width * 0.9f, 0, width * 0.1f, height);
        }

        shapeRenderer.end();
    }

    /**
     * Checks if there is a wall at a position relative to the player.
     * @param right How many tiles to the right (negative for left).
     * @param forward How many tiles forward.
     * @return true if a wall is at that relative position.
     */
    private boolean isWallAtRelative(int right, int forward) {
        Tile targetTile = getRelativeTile(right, forward);
        return targetTile != null && targetTile.getType() == TileType.WALL;
    }

    /**
     * Gets a tile at a position relative to the player's current position and direction.
     * @param right How many tiles to the right (negative for left).
     * @param forward How many tiles forward.
     * @return The Tile at that position, or null if out of bounds.
     */
    private Tile getRelativeTile(int right, int forward) {
        Player player = gameWorld.getPlayer();
        Direction facing = player.getFacing();
        int currentX = player.getX();
        int currentY = player.getY();

        int targetX = currentX;
        int targetY = currentY;

        // This logic translates relative coordinates (right, forward) to absolute maze coordinates (x, y)
        switch (facing) {
            case NORTH:
                targetX -= right;
                targetY += forward;
                break;
            case SOUTH:
                targetX += right;
                targetY -= forward;
                break;
            case EAST:
                targetX += forward;
                targetY += right;
                break;
            case WEST:
                targetX -= forward;
                targetY -= right;
                break;
        }

        return gameWorld.getMaze().getTile(targetX, targetY);
    }

    /**
     * Disposes of resources used by the renderer.
     */
    public void dispose() {
        shapeRenderer.dispose();
    }
}
