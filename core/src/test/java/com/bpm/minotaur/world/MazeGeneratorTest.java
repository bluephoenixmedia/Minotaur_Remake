package com.bpm.minotaur.world;

import com.bpm.minotaur.model.Maze;
import com.bpm.minotaur.model.Tile;
import com.bpm.minotaur.model.TileType;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the MazeGenerator class.
 */
public class MazeGeneratorTest {

    private MazeGenerator mazeGenerator;

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        mazeGenerator = new MazeGenerator();
    }

    /**
     * Tests that the generate method creates a valid Maze object with the correct dimensions.
     */
    @Test
    public void testGenerateCreatesValidMaze() {
        // 1. Act - Generate a new maze
        Maze maze = mazeGenerator.generate(1);

        // 2. Assert - Check that the maze is not null and has the correct dimensions
        assertNotNull("Generated maze should not be null", maze);
        assertEquals("Generated maze should be level 1", 1, maze.getLevel());

        for (int x = 0; x < Maze.WIDTH; x++) {
            for (int y = 0; y < Maze.HEIGHT; y++) {
                assertNotNull("All tiles in the grid should be initialized", maze.getTile(x, y));
            }
        }
    }

    /**
     * Tests that the generated maze has a solid outer wall.
     */
    @Test
    public void testMazeHasCorrectOuterWall() {
        // 1. Act
        Maze maze = mazeGenerator.generate(1);

        // 2. Assert
        for (int x = 0; x < Maze.WIDTH; x++) {
            assertEquals(TileType.WALL, maze.getTile(x, 0).getType());
            assertEquals(TileType.WALL, maze.getTile(x, Maze.HEIGHT - 1).getType());
        }
        for (int y = 0; y < Maze.HEIGHT; y++) {
            assertEquals(TileType.WALL, maze.getTile(0, y).getType());
            assertEquals(TileType.WALL, maze.getTile(Maze.WIDTH - 1, y).getType());
        }
    }

    /**
     * Tests that the quadrant stamping logic correctly places tile types.
     */
    @Test
    public void testQuadrantStamping() {
        // 1. Act
        Maze maze = mazeGenerator.generate(1);

        // 2. Assert - Check a few key tiles against the hardcoded test quadrant
        // Check a WALL tile from the quadrant's interior
        assertEquals(TileType.WALL, maze.getTile(3, 3).getType());
        // Check a FLOOR tile from the quadrant's interior
        assertEquals(TileType.FLOOR, maze.getTile(2, 2).getType());
    }
}
