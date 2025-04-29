package com.kaio.superjumper.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kaio.superjumper.enums.WorldStateEnum;

public abstract class AbstractWorld {

    protected WorldStateEnum worldState;

    protected AbstractWorld() {
        Gdx.app.log("AbstractWorld", "Starting Abstract World");
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

    protected abstract boolean keydown(int keyCode);

    protected abstract boolean keyup(int keyCode);

    protected abstract void generateLevel();

    protected abstract void update(float deltaTime, SpriteBatch batch);
}
