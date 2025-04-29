package com.kaio.superjumper.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.kaio.superjumper.model.Shape;

import java.util.ArrayList;
import java.util.List;

public class World extends AbstractWorld {

    private final Texture spriteSheet;
    private final Jumper player;
    private final List<Platform> platforms = new ArrayList<>();

    public World(Texture spriteSheet) {
        super();
        this.spriteSheet = spriteSheet;
        this.player = new Jumper(spriteSheet);
        this.platforms.add(new Platform(new Shape(150f, 150f, 114, 30),
            new TextureRegion(spriteSheet, 5472, 2, 114, 30)));
    }

    private void movePlayer() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.moveRight();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveLeft();
        }
    }

    private void checkCollisions(SpriteBatch batch) {
        for (Platform p : platforms) {
            p.draw(batch);
            if (playerHitPlatform(p)) {
                player.jump();
            }
        }
    }

    private boolean playerHitPlatform(Platform platform) {
        Rectangle platformRec = platform.getBoundingRectangle();
        return player.isFalling() &&
            platformRec.getY() + platformRec.getHeight() >= player.getY() &&
            platform.getY() < player.getY() && player.checkCollision(platform);
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
        this.platforms.add(new Platform(new Shape(250f, 250f, 114, 30),
            new TextureRegion(spriteSheet, 5472, 36, 114, 30)));

        this.platforms.add(new Platform(new Shape(100f, 400f, 114, 30),
            new TextureRegion(spriteSheet, 5472, 513, 114, 30)));
    }

    @Override
    public void update(float deltaTime, SpriteBatch batch) {
        movePlayer();
        player.update(deltaTime);
        batch.begin();
        checkCollisions(batch);
        player.draw(batch);
        batch.end();
    }

}
