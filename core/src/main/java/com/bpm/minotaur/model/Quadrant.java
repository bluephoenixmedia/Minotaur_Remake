package com.bpm.minotaur.model;

/**
 * Represents a 5x5 pre-designed template for a section of the maze.
 * This is a simple data holder class that will be used by the MazeGenerator.
 */
public class Quadrant {

    public static final int QUADRANT_SIZE = 5;

    private final TileType[][] grid;

    /**
     * Constructor for a Quadrant.
     * @param grid A 5x5 2D array of TileTypes defining the layout of this quadrant.
     */
    public Quadrant(TileType[][] grid) {
        if (grid.length != QUADRANT_SIZE || grid[0].length != QUADRANT_SIZE) {
            throw new IllegalArgumentException("Quadrant grid must be " + QUADRANT_SIZE + "x" + QUADRANT_SIZE);
        }
        this.grid = grid;
    }

    /**
     * Gets the TileType at a specific coordinate within the quadrant.
     * @param x The local x-coordinate (0-4).
     * @param y The local y-coordinate (0-4).
     * @return The TileType at that position.
     */
    public TileType getTileType(int x, int y) {
        return grid[x][y];
    }
}
