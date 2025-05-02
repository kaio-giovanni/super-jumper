package com.kaio.superjumper.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.kaio.superjumper.config.Config;
import com.kaio.superjumper.enums.WorldStateEnum;
import com.kaio.superjumper.model.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World extends AbstractWorld {

    private final Texture spriteSheet;
    private final Jumper player;
    private final List<Platform> platforms;
    private final Random random;
    private float score;

    public World(OrthographicCamera camera, Texture spriteSheet) {
        super(camera);
        this.spriteSheet = spriteSheet;
        this.player = new Jumper(spriteSheet);
        this.platforms = new ArrayList<>();
        this.random = new Random();
        this.score = 0;
    }

    public float getScore() {
        return score;
    }

    private void movePlayer() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.moveRight();
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveLeft();
        }
    }

    private void updatePlatforms(SpriteBatch batch) {
        float cameraPosY = getCameraPosition().y;
        for (Platform p : platforms) {
            p.draw(batch);
            if (playerHitPlatform(p)) {
                player.jump();
            }
            if (p.getY() < cameraPosY && isPointOutOfCameraView(p.getX(), p.getY() + p.getHeight())) {
                float newPosX = random.nextInt(400);
                p.setPosition(newPosX, cameraPosY + Config.SCREEN_HEIGHT / 2);
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
        this.platforms.add(new Platform(new Shape(10f, 180f, 114, 30),
            new TextureRegion(spriteSheet, 5472, 2, 114, 30)));

        this.platforms.add(new Platform(new Shape(150f, 340f, 114, 30),
            new TextureRegion(spriteSheet, 5472, 36, 114, 30)));

        this.platforms.add(new Platform(new Shape(300f, 500f, 114, 30),
            new TextureRegion(spriteSheet, 5472, 513, 114, 30)));

        this.platforms.add(new Platform(new Shape(380, 660, 114, 30),
            new TextureRegion(spriteSheet, 5472, 2, 114, 30)));

        this.platforms.add(new Platform(new Shape(80, 820, 114, 30),
            new TextureRegion(spriteSheet, 5472, 36, 114, 30)));
    }

    @Override
    public void update(float deltaTime, SpriteBatch batch) {
        movePlayer();
        player.update(deltaTime);
        if (player.getY() >= getCameraPosition().y + 150) {
            this.translateCameraY(100 * deltaTime);
            this.score += deltaTime;
        }

        if (isPointOutOfCameraView(200, player.getY() + 10)) {
            setWorldState(WorldStateEnum.GAME_OVER);
        }
        batch.begin();
        updatePlatforms(batch);
        player.draw(batch);
        batch.end();
    }

}
