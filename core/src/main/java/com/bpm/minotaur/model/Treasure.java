package com.bpm.minotaur.model;

/**
 * Represents a treasure item in the game.
 * This class extends the base Item class and adds an attribute for its value.
 */
public class Treasure extends Item {

    private final int value;

    /**
     * Constructor for a Treasure item.
     * @param name The name of the treasure.
     * @param value The value of the treasure.
     */
    public Treasure(String name, int value) {
        super(name, ItemType.TREASURE);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
