package com.bpm.minotaur.model;

/**
 * Manages the player's inventory, including items in hand and in the backpack.
 */
public class Inventory {

    private static final int BACKPACK_SIZE = 6;

    private Item rightHand;
    private Item leftHand;
    private final Item[] backpack;

    /**
     * Constructor for the Inventory.
     * Initializes an empty inventory.
     */
    public Inventory() {
        this.backpack = new Item[BACKPACK_SIZE];
        this.rightHand = null;
        this.leftHand = null;
    }

    // We will add methods for swapping, rotating, picking up, and dropping items here later.

    public Item getRightHand() {
        return rightHand;
    }

    public void setRightHand(Item rightHand) {
        this.rightHand = rightHand;
    }

    public Item getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Item leftHand) {
        this.leftHand = leftHand;
    }

    public Item[] getBackpack() {
        return backpack;
    }
}
