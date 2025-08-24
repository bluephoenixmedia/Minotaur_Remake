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
 */
public class MainMenuScreen extends BaseScreen {

    private BitmapFont titleFont;
    private BitmapFont regularFont;
    private BitmapFont smallFont;
    private ShapeRenderer shapeRenderer;
    private GlyphLayout layout;
    private float animationTimer;
    private boolean textBlink;

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
        // Load your custom TTF font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/intellivision.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        // Create different sized fonts
        parameter.size = 24;
        parameter.color = INTV_WHITE;
        parameter.minFilter = Texture.TextureFilter.Nearest;
        parameter.magFilter = Texture.TextureFilter.Nearest;
        titleFont = generator.generateFont(parameter);

        parameter.size = 18;
        regularFont = generator.generateFont(parameter);

        parameter.size = 14;
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

        // Blinking effect for instruction text
        //if (animationTimer > 1.2f) {
       //     textBlink = !textBlink;
       //     animationTimer = 0f;
       // }

        // Clear screen with exact background color
        ScreenUtils.clear(OLIVE_GREEN.r, OLIVE_GREEN.g, OLIVE_GREEN.b, 1);

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // Draw the 8 colored rectangles banner at the top
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        float rectWidth = screenWidth / 20f; // Make them a bit smaller than 1/8 of screen
        float rectHeight = 50f;
        float startY = screenHeight - rectHeight - 20f;
        float startX = screenWidth / 6f; // Center the banner

        // First group (left side) - 4 rectangles
        Color[] leftColors = {INTV_WHITE, INTV_YELLOW, INTV_GREEN, INTV_DARK_GREEN};
        for (int i = 0; i < 4; i++) {
            shapeRenderer.setColor(leftColors[i]);
            shapeRenderer.rect(startX + (i * rectWidth), startY, rectWidth - 2, rectHeight);
        }

        // Second group (right side) - 4 rectangles
        Color[] rightColors = {INTV_TAN, INTV_RED, INTV_BLUE, INTV_BLACK};
        float rightStartX = startX + (10 * rectWidth); // Gap between groups
        for (int i = 0; i < 4; i++) {
            shapeRenderer.setColor(rightColors[i]);
            shapeRenderer.rect(rightStartX + (i * rectWidth), startY, rectWidth - 2, rectHeight);
        }

        shapeRenderer.end();

        // Draw all text elements
        game.batch.begin();

        float currentY = startY - 40f;

        // "Mattel Electronics"
        titleFont.setColor(INTV_WHITE);
        drawCenteredText(titleFont, "Mattel Electronics", currentY);
        currentY -= 30f;

        // "presents"
        titleFont.setColor(INTV_WHITE);
        drawCenteredText(titleFont, "presents", currentY);
        currentY -= 60f;

        // Main title section
        titleFont.setColor(INTV_WHITE);
        drawCenteredText(titleFont, "TREASURE OF TARMIN 2", currentY);
        currentY -= 65f;

        //drawCenteredText(titleFont, "DUNGEONS & DRAGONS", currentY);
       // currentY -= 35f;

       // drawCenteredText(titleFont, "CARTRIDGE", currentY);
        //currentY -= 50f;

        // Trademark and copyright info
        regularFont.setColor(INTV_WHITE);
        drawCenteredText(regularFont, "TM of TSR HOBBIES", currentY);
        currentY -= 25f;

        drawCenteredText(regularFont, "Copr @ 1982 Mattel", currentY);
        currentY -= 25f;

        drawCenteredText(regularFont, "Copr @ 1982 TSR", currentY);
        currentY -= 60f;

        // Modern instruction text with blinking
        if (textBlink) {
            regularFont.setColor(INTV_YELLOW);
            drawCenteredText(regularFont, "PRESS ACTION BUTTON", currentY);
            currentY -= 30f;

            smallFont.setColor(INTV_GREEN);
            drawCenteredText(smallFont, "(TOUCH SCREEN TO BEGIN)", currentY);
        }

        game.batch.end();

        // Check for input to start game
        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.ANY_KEY)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    /**
     * Helper method to draw centered text on screen
     */
    private void drawCenteredText(BitmapFont font, String text, float y) {
        float screenWidth = Gdx.graphics.getWidth();
        layout.setText(font, text);
        float x = (screenWidth - layout.width) / 2f;
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
