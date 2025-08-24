package com.bpm.minotaur.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bpm.minotaur.MinotaurGame;

/**
 * The main menu screen of the game.
 * It displays the game title and provides an option to start a new game.
 */
public class MainMenuScreen extends BaseScreen {

    private BitmapFont font;

    public MainMenuScreen(MinotaurGame game) {
        super(game);
    }

    @Override
    public void show() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    @Override
    public void render(float delta) {
        // Clear the screen with a black color.
        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();
        font.draw(game.batch, "Treasures of Tarmin", 100, 200);
        font.draw(game.batch, "Click anywhere to begin!", 100, 150);
        game.batch.end();

        // Check if the user has clicked the screen.
        if (Gdx.input.isTouched()) {
            // Switch to the GameScreen.
            game.setScreen(new GameScreen(game));
            // Dispose of this screen's resources as we are leaving it.
            dispose();
        }
    }

    @Override
    public void dispose() {
        if (font != null) {
            font.dispose();
        }
    }
}
