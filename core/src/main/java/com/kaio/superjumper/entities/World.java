package com.kaio.superjumper.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kaio.superjumper.config.Config;
import com.kaio.superjumper.entities.platforms.BluePlatform;
import com.kaio.superjumper.entities.platforms.GreenPlatform;
import com.kaio.superjumper.entities.platforms.RedPlatform;
import com.kaio.superjumper.enums.WorldStateEnum;
import com.kaio.superjumper.interfaces.IPlatform;
import com.kaio.superjumper.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World extends AbstractWorld {

    private final Texture spriteSheet;
    private final Jumper player;
    private final List<IPlatform> platforms;
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

    private void updatePlatforms(float deltaTime, SpriteBatch batch) {
        float cameraPosY = getCameraPosition().y;
        for (IPlatform p : platforms) {
            AbstractGameObject platform = p.getSprite();
            platform.update(deltaTime);
            if (playerHitPlatform(platform)) {
                player.jump();
            }
            if (platform.getY() < cameraPosY &&
                isPointOutOfCameraView(200, platform.getY() + platform.getHeight())) {
                boolean isStatic = p.isStaticPlatform();
                float newPosX = isStatic ? random.nextInt(400) : random.nextInt(340);
                platform.setPos(newPosX, cameraPosY + Config.SCREEN_HEIGHT / 2);
            }
            p.renderPlatform(batch);
        }
    }

    private boolean playerHitPlatform(AbstractGameObject gameObject) {
        return player.isFalling() &&
            gameObject.getY() + gameObject.getHeight() >= player.getY() &&
            gameObject.getY() < player.getY() && player.checkCollision(gameObject.getBoundingRectangle());
    }

    @Override
    protected boolean keydown(int keyCode) {
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
        this.platforms.add(new GreenPlatform(spriteSheet, 10f, 180f));
        this.platforms.add(new BluePlatform(spriteSheet, 150f, 340f));
        this.platforms.add(new RedPlatform(spriteSheet, 300f, 500f));
        this.platforms.add(new RedPlatform(spriteSheet, 380f, 660f));
        this.platforms.add(new GreenPlatform(spriteSheet, 80f, 820f));
    }

    @Override
    public void update(float deltaTime, SpriteBatch batch) {
        movePlayer();
        player.update(deltaTime);
        if (player.getY() >= getCameraPosition().y + 150) {
            this.translateCameraY(100 * deltaTime);
            score = Utils.formatNumber(score + deltaTime);
        }

        if (isPointOutOfCameraView(200, player.getY() + 10)) {
            setWorldState(WorldStateEnum.GAME_OVER);
        }
        batch.begin();
        updatePlatforms(deltaTime, batch);
        player.draw(batch);
        batch.end();
    }

}
