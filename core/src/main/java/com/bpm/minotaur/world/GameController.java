package com.bpm.minotaur.world;

import com.badlogic.gdx.Gdx;
import com.bpm.minotaur.model.Direction;
import com.bpm.minotaur.model.Inventory;
import com.bpm.minotaur.model.Item;
import com.bpm.minotaur.model.Maze;
import com.bpm.minotaur.model.Player;
import com.bpm.minotaur.model.Tile;
import com.bpm.minotaur.model.TileType;

/**
 * Handles the game logic by updating the game world based on player input.
 * It acts as the controller in our MVC-like architecture.
 */
public class GameController {

    private final GameWorld gameWorld;
    private static final float MOVE_COOLDOWN = 0.2f; // Player can act once every 0.2 seconds
    private float moveTimer;

    /**
     * Constructor for the GameController.
     * @param gameWorld The game world to be controlled.
     */
    public GameController(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.moveTimer = 0f;
    }

    /**
     * Updates the game state. This method is called every frame from the GameScreen.
     * @param delta The time elapsed since the last frame.
     */
    public void update(float delta) {
        // Decrement the move timer every frame.
        if (moveTimer > 0) {
            moveTimer -= delta;
        }
    }

    /**
     * Attempts to pick up an item from the tile the player is currently on.
     */
    public void tryPickupItem() {
        Player player = gameWorld.getPlayer();
        Maze maze = gameWorld.getMaze();
        Tile currentTile = maze.getTile(player.getX(), player.getY());
        Item itemOnFloor = currentTile.getItem();

        if (itemOnFloor != null) {
            Inventory inventory = player.getInventory();
            boolean success = inventory.pickup(itemOnFloor);
            if (success) {
                // If the item was picked up, remove it from the floor.
                currentTile.setItem(null);
                Gdx.app.log("GameController", "Picked up: " + itemOnFloor.getName());
            } else {
                Gdx.app.log("GameController", "Could not pick up item, right hand is full.");
            }
        } else {
            Gdx.app.log("GameController", "No item to pick up.");
        }
    }

    /**
     * Attempts to move the player forward, but only if the cooldown timer has expired.
     */
    public void tryMoveForward() {
        if (moveTimer <= 0) {
            moveForward();
            moveTimer = MOVE_COOLDOWN; // Reset the timer after a successful move
        }
    }

    /**
     * Attempts to turn the player left, but only if the cooldown timer has expired.
     */
    public void tryTurnLeft() {
        if (moveTimer <= 0) {
            turnLeft();
            moveTimer = MOVE_COOLDOWN; // Reset the timer after a successful turn
        }
    }

    /**
     * Attempts to turn the player right, but only if the cooldown timer has expired.
     */
    public void tryTurnRight() {
        if (moveTimer <= 0) {
            turnRight();
            moveTimer = MOVE_COOLDOWN; // Reset the timer after a successful turn
        }
    }

    /**
     * Moves the player one tile forward in their current direction, if not blocked by a wall.
     */
    private void moveForward() {
        Player player = gameWorld.getPlayer();
        int currentX = player.getX();
        int currentY = player.getY();
        int targetX = currentX;
        int targetY = currentY;

        switch (player.getFacing()) {
            case NORTH:
                targetY++;
                break;
            case SOUTH:
                targetY--;
                break;
            case EAST:
                targetX++;
                break;
            case WEST:
                targetX--;
                break;
        }

        if (canMoveTo(targetX, targetY)) {
            player.setX(targetX);
            player.setY(targetY);
        }
    }

    /**
     * Turns the player 90 degrees to the left.
     */
    private void turnLeft() {
        Player player = gameWorld.getPlayer();
        Direction currentFacing = player.getFacing();
        switch (currentFacing) {
            case NORTH:
                player.setFacing(Direction.WEST);
                break;
            case SOUTH:
                player.setFacing(Direction.EAST);
                break;
            case EAST:
                player.setFacing(Direction.NORTH);
                break;
            case WEST:
                player.setFacing(Direction.SOUTH);
                break;
        }
    }

    /**
     * Turns the player 90 degrees to the right.
     */
    private void turnRight() {
        Player player = gameWorld.getPlayer();
        Direction currentFacing = player.getFacing();
        switch (currentFacing) {
            case NORTH:
                player.setFacing(Direction.EAST);
                break;
            case SOUTH:
                player.setFacing(Direction.WEST);
                break;
            case EAST:
                player.setFacing(Direction.SOUTH);
                break;
            case WEST:
                player.setFacing(Direction.NORTH);
                break;
        }
    }

    /**
     * Checks if a tile at the given coordinates is passable.
     * @param x The target x-coordinate.
     * @param y The target y-coordinate.
     * @return true if the tile is not a wall, false otherwise.
     */
    private boolean canMoveTo(int x, int y) {
        Maze maze = gameWorld.getMaze();
        Tile targetTile = maze.getTile(x, y);
        return targetTile != null && targetTile.getType() != TileType.WALL;
    }
}
