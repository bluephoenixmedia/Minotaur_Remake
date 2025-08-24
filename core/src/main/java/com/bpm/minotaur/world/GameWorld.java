package com.bpm.minotaur.world;

import com.bpm.minotaur.model.Maze;
import com.bpm.minotaur.model.Player;

/**
 * Acts as a container for all the game's model objects.
 * This class holds the current state of the game world.
 */
public class GameWorld {

    private final Maze maze;
    private final Player player;

    /**
     * Constructor for the GameWorld.
     */
    public GameWorld() {
        MazeGenerator mazeGenerator = new MazeGenerator();
        this.maze = mazeGenerator.generate(1);
        this.player = new Player();
    }

    public Maze getMaze() {
        return maze;
    }

    public Player getPlayer() {
        return player;
    }
}
