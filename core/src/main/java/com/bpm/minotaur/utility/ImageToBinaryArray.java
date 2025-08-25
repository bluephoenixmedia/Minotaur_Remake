package com.bpm.minotaur.utility;

import com.badlogic.gdx.Gdx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageToBinaryArray {
    public static int[][] convertImageToBinaryArray(String imagePath) throws IOException {
        File file = new File(imagePath);
        BufferedImage image = ImageIO.read(file);

        if (image == null) {
            throw new IOException("Could not read the image. Check the file path and format.");
        }

        int width = image.getWidth();
        int height = image.getHeight();
        int[][] binaryArray = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;

                // Check if the pixel is white (all channels are 255)
                if (red == 255 && green == 255 && blue == 255) {
                    binaryArray[y][x] = 0;
                } else {
                    binaryArray[y][x] = 1;
                }
            }
        }

        return binaryArray;
    }

    public static void main(String[] args) {

        String path = String.valueOf(Gdx.files.internal("images/ant.png"));
        try {
            int[][] result = convertImageToBinaryArray(path);
            // Print the 2D array
            for (int[] row : result) {
                for (int cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
