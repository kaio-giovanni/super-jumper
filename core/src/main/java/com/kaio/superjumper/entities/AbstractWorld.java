package com.kaio.superjumper.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.kaio.superjumper.enums.WorldStateEnum;

public abstract class AbstractWorld {

    private WorldStateEnum worldState;
    private final OrthographicCamera camera;

    protected AbstractWorld(OrthographicCamera camera) {
        Gdx.app.log("AbstractWorld", "Starting Abstract World");
        this.camera = camera;
        this.worldState = WorldStateEnum.RUNNING;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                return keydown(keycode);
            }

            @Override
            public boolean keyUp(int keycode) {
                return keyup(keycode);
            }
        });
    }

    protected void setWorldState(WorldStateEnum worldState) {
        this.worldState = worldState;
    }

    public WorldStateEnum getWorldState() {
        return worldState;
    }

    protected void translateCameraY(float y) {
        this.camera.translate(0, y);
    }

    protected Vector3 getCameraPosition() {
        return this.camera.position;
    }

    protected boolean isPointOutOfCameraView(float x, float y) {
        return !camera.frustum.pointInFrustum(x, y, 0); // Check for intersection
    }

    protected abstract boolean keydown(int keyCode);

    protected abstract boolean keyup(int keyCode);

    protected abstract void generateLevel();

    protected abstract void update(float deltaTime, SpriteBatch batch);
}
