package com.bpm.minotaur.world;

import com.bpm.minotaur.model.Item;
import com.bpm.minotaur.model.ItemType;
import com.bpm.minotaur.model.Maze;
import com.bpm.minotaur.model.Player;
import com.bpm.minotaur.model.Weapon;

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

        // Create a test weapon and place it on the floor for the player to find.
        Item testWeapon = new Weapon("Rusty Sword", ItemType.WAR_WEAPON, 5, 0);
        maze.getTile(3, 2).setItem(testWeapon);
    }

    public Maze getMaze() {
        return maze;
    }

    public Player getPlayer() {
        return player;
    }
}
