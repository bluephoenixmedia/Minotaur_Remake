package com.bpm.minotaur.model;

/**
 * Represents a single level of the dungeon.
 * It contains a 12x12 grid of Tile objects that define the level's layout.
 */
public class Maze {

    public static final int WIDTH = 12;
    public static final int HEIGHT = 12;

    private final Tile[][] grid;
    private final int level;

    /**
     * Constructor for a new Maze.
     * @param level The dungeon level number for this maze.
     */
    public Maze(int level) {
        this.level = level;
        this.grid = new Tile[WIDTH][HEIGHT];
        // We will populate the grid later using the MazeGenerator.
    }

    /**
     * Gets the tile at a specific coordinate.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The Tile at the given coordinates, or null if out of bounds.
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return null; // Out of bounds
        }
        return grid[x][y];
    }

    /**
     * Sets the tile at a specific coordinate.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param tile The Tile to place in the grid.
     */
    public void setTile(int x, int y, Tile tile) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            grid[x][y] = tile;
        }
    }

    public int getLevel() {
        return level;
    }
}
