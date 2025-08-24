package com.bpm.minotaur.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bpm.minotaur.MinotaurGame;

/**
 * Authentic Intellivision title screen recreation based on the original
 * Advanced Dungeons & Dragons cartridge title screen format.
 * Optimized for 1920x1080 resolution.
 */
public class MainMenuScreen extends BaseScreen {

    private BitmapFont titleFont;
    private BitmapFont regularFont;
    private BitmapFont smallFont;
    private ShapeRenderer shapeRenderer;
    private GlyphLayout layout;
    private float animationTimer;
    private boolean textBlink;

    // Target resolution constants
    private static final float TARGET_WIDTH = 1920f;
    private static final float TARGET_HEIGHT = 1080f;

    // Exact colors from the original Intellivision title screen
    private static final Color BACKGROUND_COLOR = new Color(12/255f, 12/255f, 12/255f, 1.0f);
    private static final Color INTV_WHITE = new Color(253/255f, 253/255f, 253/255f, 1.0f);
    private static final Color INTV_YELLOW = new Color(249/255f, 234/255f, 79/255f, 1.0f);
    private static final Color INTV_GREEN = new Color(0/255f, 167/255f, 88/255f, 1.0f);
    private static final Color INTV_DARK_GREEN = new Color(56/255f, 107/255f, 64/255f, 1.0f);
    private static final Color INTV_TAN = new Color(200/255f, 207/255f, 172/255f, 1.0f);
    private static final Color INTV_RED = new Color(254/255f, 60/255f, 17/255f, 1.0f);
    private static final Color INTV_BLUE = new Color(0/255f, 44/255f, 254/255f, 1.0f);
    private static final Color INTV_BLACK = new Color(1/255f, 1/255f, 0/255f, 1.0f);
    private static final Color OLIVE_GREEN = new Color(85/255f, 110/255f, 0/255f, 1.0f);

    public MainMenuScreen(MinotaurGame game) {
        super(game);
    }

    @Override
    public void show() {
        // Load your custom TTF font with sizes scaled for 1920x1080
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/intellivision.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        // Create different sized fonts - scaled up for 1920x1080 (3x larger than original 640x480)
        parameter.size = 72; // Was 24, now 72 (3x scale)
        parameter.color = INTV_WHITE;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        titleFont = generator.generateFont(parameter);

        parameter.size = 54; // Was 18, now 54 (3x scale)
        regularFont = generator.generateFont(parameter);

        parameter.size = 42; // Was 14, now 42 (3x scale)
        smallFont = generator.generateFont(parameter);

        generator.dispose();

        shapeRenderer = new ShapeRenderer();
        layout = new GlyphLayout();
        animationTimer = 0f;
        textBlink = false;
    }

    @Override
    public void render(float delta) {
        animationTimer += delta;

        // Update blinking text every 0.8 seconds
        if (animationTimer % 1.6f < 0.8f) {
            textBlink = true;
        } else {
            textBlink = false;
        }

        // Clear screen with exact background color
        ScreenUtils.clear(OLIVE_GREEN.r, OLIVE_GREEN.g, OLIVE_GREEN.b, 1);

        // Draw the 8 colored rectangles banner at the top
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Rectangle dimensions scaled for 1920x1080
        float rectWidth = TARGET_WIDTH / 16f; // 120px
        float rectHeight = TARGET_HEIGHT / 20f; // 54px
        float bannerY = TARGET_HEIGHT * 0.85f; // 918px from bottom

        // Calculate total banner width to center properly
        float totalBannerWidth = (rectWidth * 8) + (rectWidth * 2); // 8 rects + 2 rect gap
        float startX = (TARGET_WIDTH - totalBannerWidth) / 2f;

        // First group (left side) - 4 rectangles
        Color[] leftColors = {INTV_WHITE, INTV_YELLOW, INTV_GREEN, INTV_DARK_GREEN};
        for (int i = 0; i < 4; i++) {
            shapeRenderer.setColor(leftColors[i]);
            shapeRenderer.rect(startX + (i * rectWidth), bannerY, rectWidth - 6, rectHeight); // 6px gap scaled from 2px
        }

        // Second group (right side) - 4 rectangles with proper gap
        Color[] rightColors = {INTV_TAN, INTV_RED, INTV_BLUE, INTV_BLACK};
        float rightStartX = startX + (4 * rectWidth) + (rectWidth * 2);
        for (int i = 0; i < 4; i++) {
            shapeRenderer.setColor(rightColors[i]);
            shapeRenderer.rect(rightStartX + (i * rectWidth), bannerY, rectWidth - 6, rectHeight);
        }

        shapeRenderer.end();

        // Draw all text elements
        game.batch.begin();

        // "Mattel Electronics" - positioned at 75% of screen height
        titleFont.setColor(INTV_WHITE);
        drawCenteredText(titleFont, "Mattel Electronics", TARGET_HEIGHT * 0.75f);

        // "presents" - positioned at 68% of screen height
        titleFont.setColor(INTV_WHITE);
        drawCenteredText(titleFont, "presents", TARGET_HEIGHT * 0.68f);

        // Main title section - positioned at 55% of screen height
        titleFont.setColor(INTV_WHITE);
        drawCenteredText(titleFont, "TREASURE OF TARMIN 2", TARGET_HEIGHT * 0.55f);

        // Trademark and copyright info
        regularFont.setColor(INTV_WHITE);
        drawCenteredText(regularFont, "TM of TSR HOBBIES", TARGET_HEIGHT * 0.42f);

        drawCenteredText(regularFont, "Copr @ 1982 Mattel", TARGET_HEIGHT * 0.35f);

        drawCenteredText(regularFont, "Copr @ 1982 TSR", TARGET_HEIGHT * 0.28f);

        game.batch.end();

        // Check for input to start game
        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.ANY_KEY)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    /**
     * Helper method to draw centered text on screen for 1920x1080
     */
    private void drawCenteredText(BitmapFont font, String text, float y) {
        layout.setText(font, text);
        float x = (TARGET_WIDTH - layout.width) / 2f;
        font.draw(game.batch, text, x, y);
    }

    @Override
    public void dispose() {
        if (titleFont != null) {
            titleFont.dispose();
        }
        if (regularFont != null) {
            regularFont.dispose();
        }
        if (smallFont != null) {
            smallFont.dispose();
        }
        if (shapeRenderer != null) {
            shapeRenderer.dispose();
        }
    }
}
