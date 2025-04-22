package com.kaio.superjumper.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kaio.superjumper.config.Config;

public abstract class AbstractScreen extends ScreenAdapter {

    private final Game game;
    private final SpriteBatch batch;
    protected final Texture spriteSheet;
    private final OrthographicCamera camera;
    protected final Viewport viewport;
    private final Vector3 clickedPoint;

    protected AbstractScreen(Game game, SpriteBatch batch, Texture spriteSheet) {
        this.game = game;
        this.batch = batch;
        this.spriteSheet = spriteSheet;
        this.camera = new OrthographicCamera();
        this.camera.position.set(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2, 0);
        this.viewport = new StretchViewport(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, camera);
        this.clickedPoint = new Vector3();
    }

    public void setScreen(AbstractScreen screen) {
        this.game.setScreen(screen);
    }

    private void watchMouseEvents() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            this.clickedPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mouseButtonPressed(camera.unproject(this.clickedPoint));
        }
    }

    protected abstract void mouseButtonPressed(Vector3 clickedPoint);

    protected abstract void drawBackground(SpriteBatch batch);

    protected abstract void update(float delta);

    protected abstract void disposeObjects();

    @Override
    public void render(float delta) {
        watchMouseEvents();
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
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        disposeObjects();
    }
}
