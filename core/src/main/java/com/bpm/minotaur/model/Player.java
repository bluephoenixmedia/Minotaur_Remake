package com.bpm.minotaur.model;

/**
 * Represents the player character in the game.
 * This class holds all the player's stats, inventory, and position in the maze.
 */
public class Player {

    // Player Attributes
    private int warStrength;
    private int spiritualStrength;
    private int food;
    private int arrows;

    // Player Position and Orientation
    private int x;
    private int y;
    private Direction facing;

    // We will create the Inventory class later.
    // private Inventory inventory;

    /**
     * Constructor for a new Player.
     * Initializes the player with starting stats for a given skill level.
     */
    public Player(/* We will add SkillLevel here later */) {
        // For now, we'll use placeholder starting values.
        this.warStrength = 12;
        this.spiritualStrength = 6;
        this.food = 6;
        this.arrows = 6;

        // Starting position in the 12x12 grid (top-left corner).
        this.x = 1;
        this.y = 1;

        // Player starts facing East according to the original manual.
        this.facing = Direction.EAST;
        // this.inventory = new Inventory();
    }

    // We will add methods for moving, attacking, etc., here later.
    // For now, we'll just add getters and setters for the attributes.

    public int getWarStrength() {
        return warStrength;
    }

    public void setWarStrength(int warStrength) {
        this.warStrength = warStrength;
    }

    public int getSpiritualStrength() {
        return spiritualStrength;
    }

    public void setSpiritualStrength(int spiritualStrength) {
        this.spiritualStrength = spiritualStrength;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getArrows() {
        return arrows;
    }

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getFacing() {
        return facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }
}
