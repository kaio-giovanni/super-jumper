package com.kaio.superjumper.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kaio.superjumper.config.Config;
import com.kaio.superjumper.entities.World;

public class GameScreen extends AbstractScreen {

    private final SpriteBatch batch;
    private final TextureRegion background;
    private final World world;

    protected GameScreen(Game game, SpriteBatch batch, Texture spriteSheet) {
        super(game, batch, spriteSheet);
        Gdx.app.log("MenuScreen", "Starting Game Screen");
        this.background = new TextureRegion(spriteSheet, 531, 155, 532, 850);
        this.batch = batch;
        this.world = new World(camera, spriteSheet);
        this.world.generateLevel();
    }

    @Override
    protected void drawBackground(SpriteBatch batch) {
        float posY = getCameraPosition().y;
        batch.draw(background, 0, posY - (viewport.getWorldHeight() / 2), Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    }

    @Override
    protected void update(float delta) {
        world.update(delta, batch);
    }

    @Override
    protected void disposeObjects() {
        // nothing
    }
}
