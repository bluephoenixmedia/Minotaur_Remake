package com.bpm.minotaur.world;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;

public class MonsterGenerator implements Disposable {
    // Intellivision color palette (approximations)
    private final Color[] INTELLIVISION_PALETTE = {
        new Color(0x000000FF), // Black
        new Color(0x0055FFFF), // Blue
        new Color(0xFF5500FF), // Red
        new Color(0x884400FF), // Dark Brown (Primary)
        new Color(0x00AA00FF), // Dark Green
        new Color(0x00FF00FF), // Green
        new Color(0xFFFF00FF), // Yellow
        new Color(0xFFFFFFFF), // White
        new Color(0x555555FF), // Gray (Pastel)
        new Color(0x55FFFFFF), // Light Blue (Pastel)
        new Color(0xFF5555FF), // Pink (Pastel)
        new Color(0xCC8844FF), // Light Brown (Pastel)
        new Color(0x55FF55FF), // Light Green (Pastel)
        new Color(0xAAAA55FF), // Light Yellow (Pastel)
        new Color(0xAAFFFFFF), // Light Cyan (Pastel)
        new Color(0xAAAAAAFF)  // Light Gray (Pastel)
    };

    private Pixmap antPixmap;
    private Texture antTexture;

    public MonsterGenerator() {
        // Initialize with a default ant
        generateGiantAnt();
    }

    public void generateGiantAnt() {
        // Create an 8x16 pixmap (Intellivision sprite size)
        if (antPixmap != null) {
            antPixmap.dispose();
        }
        antPixmap = new Pixmap(8, 16, Pixmap.Format.RGBA8888);

        // Define the ant sprite pattern
        // Using a 2D array to represent the sprite
        int[][] antPattern = {
            {0, 0, 0, 0, 1, 0, 0, 1}, // Row 1: Top of head
            {0, 0, 0, 1, 0, 0, 1, 0}, // Row 2: Head with mandibles
            {0, 1, 1, 0, 1, 1, 1, 0}, // Row 3: Head with eye details
            {0, 1, 1, 1, 1, 1, 1, 1}, // Row 4: Head/thorax connection
            {1, 1, 1, 1, 1, 1, 1, 1}, // Row 5: Thorax top
            {1, 1, 1, 1, 0, 1, 0, 1}, // Row 6: Thorax with segmentation
            {1, 1, 1, 1, 1, 0, 1, 0}, // Row 7: Thorax middle
            {0, 1, 1, 1, 0, 0, 0, 1}, // Row 8: Thorax bottom
            {0, 0, 1, 1, 1, 0, 1, 0}, // Row 9: Abdomen top
            {0, 0, 1, 1, 1, 1, 0, 0}, // Row 10: Abdomen upper middle
            {0, 0, 1, 1, 1, 1, 0, 0}, // Row 11: Abdomen middle
            {0, 0, 1, 1, 1, 1, 0, 0}, // Row 12: Abdomen lower middle
            {1, 0, 1, 1, 1, 1, 1, 0}, // Row 13: Abdomen bottom
            {1, 0, 1, 0, 1, 0, 0, 1}, // Row 14: Abdomen tip
            {0, 1, 0, 1, 0, 1, 0, 1}, // Row 15: Legs (middle set)
            {0, 1, 0, 1, 0, 1, 0, 1}  // Row 16: Legs (front/back sets) and antennae
        };

        // Draw the ant pattern
        for (int y = 0; y < 16; y++) {
            for (int x = 0; x < 8; x++) {
                int colorIndex = antPattern[y][x];
                if (colorIndex != 0) { // 0 is transparent
                    antPixmap.setColor(INTELLIVISION_PALETTE[colorIndex]);
                    antPixmap.drawPixel(x, y);
                }
            }
        }

        // Create texture from pixmap
        if (antTexture != null) {
            antTexture.dispose();
        }
        antTexture = new Texture(antPixmap);
    }

    public Texture getAntTexture() {
        return antTexture;
    }

    public Texture getAntTexture(int scale) {
        if (scale <= 1) {
            return antTexture;
        }

        // Create a scaled version
        Pixmap scaledPixmap = new Pixmap(8 * scale, 16 * scale, Pixmap.Format.RGBA8888);
        scaledPixmap.setFilter(Pixmap.Filter.NearestNeighbour); // Keep pixelated look

        // Draw the original pixmap scaled up
        scaledPixmap.drawPixmap(antPixmap,
            0, 0, antPixmap.getWidth(), antPixmap.getHeight(),
            0, 0, scaledPixmap.getWidth(), scaledPixmap.getHeight());

        Texture scaledTexture = new Texture(scaledPixmap);
        scaledPixmap.dispose();

        return scaledTexture;
    }

    @Override
    public void dispose() {
        if (antPixmap != null) {
            antPixmap.dispose();
        }
        if (antTexture != null) {
            antTexture.dispose();
        }
    }

    // Method to generate variations (placeholder for future expansion)
    public Texture generateMonsterVariation(String type, int variation) {
        // For now, return the standard ant
        // This method can be expanded to create different monsters
        return getAntTexture();
    }
}
