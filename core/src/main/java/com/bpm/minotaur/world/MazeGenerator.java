package com.bpm.minotaur.world;

import com.bpm.minotaur.model.Maze;
import com.bpm.minotaur.model.Quadrant;
import com.bpm.minotaur.model.Tile;
import com.bpm.minotaur.model.TileType;

/**
 * Responsible for procedurally generating maze levels.
 * It uses the Quadrant-based Assembly algorithm defined in the design document.
 */
public class MazeGenerator {

    /**
     * Generates a new maze for a given level.
     * @param level The dungeon level to generate.
     * @return A newly generated Maze object.
     */
    public Maze generate(int level) {
        Maze maze = new Maze(level);

        // For now, create a simple, hardcoded test quadrant.
        // Later, we will load a library of these from a JSON file.
        TileType[][] quadrantGrid = new TileType[][]{
            {TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL},
            {TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL},
            {TileType.WALL, TileType.FLOOR, TileType.WALL, TileType.FLOOR, TileType.WALL},
            {TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL},
            {TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL}
        };
        Quadrant testQuadrant = new Quadrant(quadrantGrid);

        // Assemble the maze using the test quadrant for all four sections.
        assembleMaze(maze, testQuadrant, testQuadrant, testQuadrant, testQuadrant);

        return maze;
    }

    /**
     * Assembles a maze by stamping four quadrants into the central 10x10 area.
     * @param maze The Maze object to modify.
     * @param nw The northwest quadrant.
     * @param ne The northeast quadrant.
     * @param sw The southwest quadrant.
     * @param se The southeast quadrant.
     */
    private void assembleMaze(Maze maze, Quadrant nw, Quadrant ne, Quadrant sw, Quadrant se) {
        // Build the outer corridor wall (a 12x12 ring of walls)
        for (int x = 0; x < Maze.WIDTH; x++) {
            for (int y = 0; y < Maze.HEIGHT; y++) {
                if (x == 0 || x == Maze.WIDTH - 1 || y == 0 || y == Maze.HEIGHT - 1) {
                    maze.setTile(x, y, new Tile(TileType.WALL));
                }
            }
        }

        // Stamp the four 5x5 quadrants into the 10x10 center
        stampQuadrant(maze, nw, 1, 1); // Northwest
        stampQuadrant(maze, ne, 6, 1); // Northeast
        stampQuadrant(maze, sw, 1, 6); // Southwest
        stampQuadrant(maze, se, 6, 6); // Southeast
    }

    /**
     * Stamps a single quadrant's tile data onto the main maze grid.
     * @param maze The maze to modify.
     * @param quadrant The quadrant template to use.
     * @param offsetX The starting x-coordinate in the main maze grid.
     * @param offsetY The starting y-coordinate in the main maze grid.
     */
    private void stampQuadrant(Maze maze, Quadrant quadrant, int offsetX, int offsetY) {
        for (int x = 0; x < Quadrant.QUADRANT_SIZE; x++) {
            for (int y = 0; y < Quadrant.QUADRANT_SIZE; y++) {
                TileType type = quadrant.getTileType(x, y);
                maze.setTile(x + offsetX, y + offsetY, new Tile(type));
            }
        }
    }
}
