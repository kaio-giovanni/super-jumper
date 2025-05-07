package com.kaio.superjumper.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.kaio.superjumper.config.Config;
import com.kaio.superjumper.enums.JumperActionStateEnum;
import com.kaio.superjumper.enums.JumperStateEnum;
import com.kaio.superjumper.model.Shape;

import java.util.Map;

public class Jumper extends AbstractGameObject {

    // --- Physics Constants ---
    public static final Vector2 GRAVITY = new Vector2(0, -250);
    public static final float MAX_JUMP_DURATION = 1.2f;
    public static final float JUMP_START_VELOCITY = 340.0f;
    public static final float MAX_FALL_SPEED = -450.0f;
    public static final float MOVE_SPEED = 8.0f;

    // --- State Variables ---
    private float jumpTimer = 0.0f;
    private boolean jumpAnimationStarted = false;
    private JumperStateEnum currentState;
    private JumperActionStateEnum currentAction;
    private final Map<String, TextureRegion> clips;

    public Jumper(Texture spriteSheet) {
        super(new Shape(10, 370f, 62, 60),
            new TextureRegion(spriteSheet, 2702, 30, 92, 90));
        this.clips = this.getPlayerClips(spriteSheet);
        this.currentState = JumperStateEnum.FALLING;
        this.currentAction = JumperActionStateEnum.TURNING_LEFT;
    }

    private Map<String, TextureRegion> getPlayerClips(Texture spriteSheet) {
        return Map.of(
            "LEFT_STANDING", new TextureRegion(spriteSheet, 2702, 30, 92, 90),
            "LEFT_KNEELING", new TextureRegion(spriteSheet, 2826, 38, 92, 82),
            "SHOOT_STANDING", new TextureRegion(spriteSheet, 3008, 2, 60, 118),
            "SHOOT_KNEELING", new TextureRegion(spriteSheet, 3132, 8, 60, 112),
            "RIGHT_STANDING", new TextureRegion(spriteSheet, 3258, 30, 92, 90),
            "RIGHT_KNEELING", new TextureRegion(spriteSheet, 3382, 38, 92, 82)
        );
    }

    private void setClip(String clipName) {
        TextureRegion img = clips.get(clipName);
        this.setRegion(img);
        // resizing rect
        this.setSize((float) Math.ceil(img.getRegionWidth() * 0.67f),
            (float) Math.ceil(img.getRegionHeight() * 0.67f));
    }

    public void moveLeft() {
        this.velocity.add(-MOVE_SPEED, 0);
        this.setClip("LEFT_STANDING");
        this.currentAction = JumperActionStateEnum.TURNING_LEFT;
    }

    public void moveRight() {
        this.velocity.add(MOVE_SPEED, 0);
        this.setClip("RIGHT_STANDING");
        this.currentAction = JumperActionStateEnum.TURNING_RIGHT;
    }

    public void shoot() {
        this.setClip("SHOOT_STANDING");
        this.currentAction = JumperActionStateEnum.SHOOTING;
    }

    public void jump() {
        velocity.y = JUMP_START_VELOCITY;
        currentState = JumperStateEnum.JUMPING;
        jumpTimer = 0f;
        jumpAnimationStarted = true;
        jumpAnimation();
    }

    public boolean isJumping() {
        return currentState == JumperStateEnum.JUMPING;
    }

    public boolean isFalling() {
        return currentState == JumperStateEnum.FALLING;
    }

    private void jumpAnimation() {
        switch (currentAction) {
            case TURNING_LEFT:
                setClip("LEFT_KNEELING");
                break;
            case TURNING_RIGHT:
                setClip("RIGHT_KNEELING");
                break;
            case SHOOTING:
                setClip("SHOOT_KNEELING");
                break;
            default:
                Gdx.app.error("Jumper", "Invalid jumper state");
        }
    }

    private void resetJumpAnimation() {
        switch (currentAction) {
            case TURNING_LEFT:
                setClip("LEFT_STANDING");
                break;
            case TURNING_RIGHT:
                setClip("RIGHT_STANDING");
                break;
            case SHOOTING:
                setClip("SHOOT_STANDING");
                break;
            default:
                Gdx.app.error("Jumper", "Invalid jumper state");
        }
        jumpAnimationStarted = false;
    }

    private void updatePosition(float deltaTime) {
        if (currentState == JumperStateEnum.JUMPING) {
            jumpTimer += deltaTime;

            if (jumpAnimationStarted && jumpTimer > deltaTime * 4 && jumpTimer <= deltaTime * 12) {
                resetJumpAnimation();
            }

            if (jumpTimer >= MAX_JUMP_DURATION && velocity.y > 0) {
                currentState = JumperStateEnum.FALLING;
            }
        }

        if (currentState != JumperStateEnum.JUMPING || velocity.y > 0) {
            velocity.add(GRAVITY.x * deltaTime, GRAVITY.y * deltaTime);
        }

        if (velocity.y < MAX_FALL_SPEED) {
            velocity.y = MAX_FALL_SPEED;
        }

        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        if (position.x + getWidth() <= 1) {
            position.add(Config.SCREEN_WIDTH, 0);
        }

        if (position.x >= Config.SCREEN_WIDTH) {
            position.sub(Config.SCREEN_WIDTH, 0);
        }

        this.setPosition(position.x, position.y);
    }

    @Override
    public void update(float deltaTime) {
        updatePosition(deltaTime);
    }
}
