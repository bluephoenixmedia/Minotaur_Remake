package com.bpm.minotaur.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the Player class.
 */
public class PlayerTest {

    /**
     * Tests that the Player constructor correctly initializes a player
     * with the default starting stats.
     */
    @Test
    public void testPlayerInitialization() {
        // 1. Arrange - Create a new player instance
        Player player = new Player();

        // 2. Assert - Verify that all initial values match the design
        assertEquals(12, player.getWarStrength());
        assertEquals(6, player.getSpiritualStrength());
        assertEquals(6, player.getFood());
        assertEquals(6, player.getArrows());
        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
        assertEquals(Direction.EAST, player.getFacing());
    }
}
