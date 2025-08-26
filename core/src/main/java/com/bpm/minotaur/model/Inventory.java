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

    /**
     * Attempts to pick up an item from the floor.
     * The item will be placed in the right hand if it's empty.
     * If the right hand is full, this method does nothing for now.
     * @param item The item to pick up.
     * @return true if the item was successfully picked up, false otherwise.
     */
    public boolean pickup(Item item) {
        if (rightHand == null) {
            setRightHand(item);
            return true;
        }
        // We will add logic for a full hand (swapping) later.
        return false;
    }

    /**
     * Drops the item currently held in the right hand.
     * @return The item that was dropped, or null if the hand was empty.
     */
    public Item dropRightHand() {
        Item itemToDrop = rightHand;
        if (itemToDrop != null) {
            setRightHand(null);
        }
        return itemToDrop;
    }

    // We will add methods for swapping and rotating items here later.

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
