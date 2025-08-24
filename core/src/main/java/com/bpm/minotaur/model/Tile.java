package com.bpm.minotaur.model;

/**
 * Represents a single tile in the maze grid.
 * A tile has a type (e.g., wall, floor) and can contain a monster or an item.
 */
public class Tile {

    private TileType type;
    // We will create the Monster and Item classes later.
    // private Monster monster;
    // private Item item;

    /**
     * Constructor for a new Tile.
     * @param type The type of the tile (WALL, FLOOR, etc.).
     */
    public Tile(TileType type) {
        this.type = type;
        // this.monster = null; // A tile is empty by default.
        // this.item = null;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    // We will add getters and setters for monster and item later.
}
