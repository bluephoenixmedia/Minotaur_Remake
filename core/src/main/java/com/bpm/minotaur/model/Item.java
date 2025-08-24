package com.bpm.minotaur.model;

/**
 * An abstract base class for all items in the game.
 * It holds the common properties shared by all items, such as name and type.
 */
public abstract class Item {

    private final String name;
    private final ItemType type;

    /**
     * Constructor for an Item.
     * @param name The name of the item.
     * @param type The type of the item.
     */
    public Item(String name, ItemType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }
}
