package com.bpm.minotaur.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.bpm.minotaur.model.Maze;
import com.bpm.minotaur.model.Quadrant;
import com.bpm.minotaur.model.Tile;
import com.bpm.minotaur.model.TileType;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for procedurally generating maze levels.
 * It uses the Quadrant-based Assembly algorithm defined in the design document.
 */
public class MazeGenerator {

    private final List<Quadrant> quadrantLibrary;

    /**
     * Constructor for the MazeGenerator.
     * It loads the quadrant templates from the JSON file.
     */
    public MazeGenerator() {
        quadrantLibrary = new ArrayList<>();
        loadQuadrants();
    }

    /**
     * Loads quadrant definitions from the quadrants.json file.
     */
    private void loadQuadrants() {
        JsonReader jsonReader = new JsonReader();
        JsonValue base = jsonReader.parse(Gdx.files.internal("quadrants.json"));
        JsonValue quadrantsArray = base.get("quadrants");

        for (JsonValue quadrantValue : quadrantsArray) {
            String[] gridStrings = quadrantValue.get("grid").asStringArray();
            TileType[][] grid = new TileType[Quadrant.QUADRANT_SIZE][Quadrant.QUADRANT_SIZE];

            for (int y = 0; y < Quadrant.QUADRANT_SIZE; y++) {
                for (int x = 0; x < Quadrant.QUADRANT_SIZE; x++) {
                    char tileChar = gridStrings[y].charAt(x);
                    if (tileChar == 'W') {
                        grid[x][y] = TileType.WALL;
                    } else {
                        grid[x][y] = TileType.FLOOR;
                    }
                }
            }
            quadrantLibrary.add(new Quadrant(grid));
        }
    }

    /**
     * Generates a new maze for a given level.
     * @param level The dungeon level to generate.
     * @return A newly generated Maze object.
     */
    public Maze generate(int level) {
        Maze maze = new Maze(level);

        // Select a quadrant from our loaded library.
        // Later, we will add logic to select based on difficulty.
        Quadrant quadrant = quadrantLibrary.get(0);

        // Assemble the maze using the selected quadrant for all four sections.
        assembleMaze(maze, quadrant, quadrant, quadrant, quadrant);

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
