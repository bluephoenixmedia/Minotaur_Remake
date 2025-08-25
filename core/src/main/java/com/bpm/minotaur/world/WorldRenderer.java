package com.bpm.minotaur.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bpm.minotaur.model.Maze;
import com.bpm.minotaur.model.Player;
import com.bpm.minotaur.model.Tile;
import com.bpm.minotaur.model.TileType;

/**
 * Responsible for rendering the game world, including the maze, player, and monsters.
 */
public class WorldRenderer {

    private final GameWorld gameWorld;
    private final ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private final MonsterGenerator monsterGenerator;
    private Texture antTexture;


    /**
     * Constructor for the WorldRenderer.
     * @param gameWorld The GameWorld object to render.
     */
    public WorldRenderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.shapeRenderer = new ShapeRenderer();
        this.batch = new SpriteBatch();

        // Create camera matching your world coordinates
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 1920, 1080); // Adjust to your game's dimensions

        this.monsterGenerator = new MonsterGenerator();

    }

    /**
     * Renders the game world.
     */
    public void render() {
        Maze maze = gameWorld.getMaze();
        Player player = gameWorld.getPlayer();
        Texture antTexture = monsterGenerator.getAntTexture(32);

        camera.update();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Render the maze
        for (int x = 0; x < Maze.WIDTH; x++) {
            for (int y = 0; y < Maze.HEIGHT; y++) {
                Tile tile = maze.getTile(x, y);
                if (tile.getType() == TileType.WALL) {
                    shapeRenderer.setColor(Color.GRAY);
                } else {
                    shapeRenderer.setColor(Color.WHITE);
                }
                shapeRenderer.rect(x * 20, y * 20, 20, 20);
            }
        }

        // Render the player
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(player.getX() * 20, player.getY() * 20, 20, 20);

        shapeRenderer.end();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(antTexture, 800, 400);
        batch.end();
        //antTexture.draw(monsterGenerator.getAntTexture());


    }

    /**
     * Disposes of resources used by the renderer.
     */
    public void dispose() {
        shapeRenderer.dispose();
        monsterGenerator.dispose();
    }
}
