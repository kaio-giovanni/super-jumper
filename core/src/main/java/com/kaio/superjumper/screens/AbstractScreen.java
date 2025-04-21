package com.kaio.superjumper.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kaio.superjumper.config.Config;

public abstract class AbstractScreen extends ScreenAdapter {

    private final Game game;
    private final SpriteBatch batch;
    protected final Texture spriteSheet;
    protected final OrthographicCamera camera;
    private final Viewport viewport;

    protected AbstractScreen(Game game, SpriteBatch batch, Texture spriteSheet) {
        this.game = game;
        this.batch = batch;
        this.spriteSheet = spriteSheet;
        this.camera = new OrthographicCamera();
        this.camera.position.set(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2, 0);
        this.viewport = new StretchViewport(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, camera);
    }

    public void setScreen(ScreenAdapter screen) {
        this.game.setScreen(screen);
    }

    protected abstract void drawObjects(SpriteBatch batch, float delta);

    protected abstract void drawBackground(SpriteBatch batch);

    @Override
    public void render(float delta) {
        camera.update();
        this.batch.setProjectionMatrix(camera.combined);

        this.batch.disableBlending();
        this.batch.begin();
        drawBackground(batch);
        this.batch.end();

        this.batch.enableBlending();
        this.batch.begin();
        drawObjects(batch, delta);
        this.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
