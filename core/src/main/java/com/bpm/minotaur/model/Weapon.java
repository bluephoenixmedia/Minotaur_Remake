package com.bpm.minotaur.model;

/**
 * Represents a weapon item in the game.
 * This class extends the base Item class and adds attributes for combat power.
 */
public class Weapon extends Item {

    private final int warPower;
    private final int spiritualPower;

    /**
     * Constructor for a Weapon.
     * @param name The name of the weapon.
     * @param type The type of the item (should be WAR_WEAPON or SPIRITUAL_WEAPON).
     * @param warPower The weapon's power in physical combat.
     * @param spiritualPower The weapon's power in spiritual combat.
     */
    public Weapon(String name, ItemType type, int warPower, int spiritualPower) {
        super(name, type);
        this.warPower = warPower;
        this.spiritualPower = spiritualPower;
    }

    public int getWarPower() {
        return warPower;
    }

    public int getSpiritualPower() {
        return spiritualPower;
    }
}
