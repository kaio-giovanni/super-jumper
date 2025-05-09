package com.kaio.superjumper.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kaio.superjumper.config.Config;

public abstract class AbstractScreen extends ScreenAdapter {

    protected final Game game;
    protected final SpriteBatch batch;
    protected final Texture spriteSheet;
    protected final OrthographicCamera camera;
    protected final Viewport viewport;

    protected AbstractScreen(Game game, SpriteBatch batch, Texture spriteSheet) {
        Gdx.app.log("AbstractScreen", "Building Abstract Screen");
        this.game = game;
        this.batch = batch;
        this.spriteSheet = spriteSheet;
        this.camera = new OrthographicCamera();
        this.camera.position.set(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2, 0);
        this.camera.setToOrtho(false, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.viewport = new StretchViewport(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, camera);
    }

    public void setScreen(AbstractScreen screen) {
        this.game.setScreen(screen);
    }

    protected abstract void drawBackground(SpriteBatch batch);

    protected abstract void update(float delta);

    protected abstract void disposeObjects();

    protected Vector3 getCameraPosition() {
        return this.camera.position;
    }

    @Override
    public void render(float delta) {
        camera.update();
        this.batch.setProjectionMatrix(camera.combined);

        this.batch.disableBlending();
        this.batch.begin();
        drawBackground(batch);
        this.batch.end();

        this.batch.enableBlending();
        update(delta);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("AbstractScreen", "Resizing the screen. New size (" + width + ", " + height + ")");
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        disposeObjects();
    }
}
