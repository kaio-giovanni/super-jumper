package com.kaio.superjumper.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class World extends AbstractWorld {

    private final Jumper player;

    public World(Texture spriteSheet) {
        super();
        this.player = new Jumper(spriteSheet);
    }

    private void movePlayer() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.moveRight();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveLeft();
        }
    }

    @Override
    protected boolean keydown(int keyCode) {
        Gdx.app.debug("KeyDown", "KeyCode: " + keyCode);
        if (keyCode == Input.Keys.SPACE) {
            player.shoot();
            return true;
        }

        if (keyCode == Input.Keys.RIGHT) {
            player.moveRight();
            return true;
        }

        if (keyCode == Input.Keys.LEFT) {
            player.moveLeft();
            return true;
        }

        return false;
    }

    @Override
    protected boolean keyup(int keyCode) {
        return false;
    }

    @Override
    public void generateLevel() {
        // nothing
    }

    @Override
    public void update(float deltaTime, SpriteBatch batch) {
        movePlayer();
        player.update(deltaTime);
        batch.begin();
        player.draw(batch);
        batch.end();
    }

}
