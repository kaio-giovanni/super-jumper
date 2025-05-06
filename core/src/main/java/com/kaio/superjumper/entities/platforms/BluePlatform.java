package com.kaio.superjumper.entities.platforms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kaio.superjumper.entities.AbstractGameObject;
import com.kaio.superjumper.interfaces.IPlatform;
import com.kaio.superjumper.model.Shape;

public class BluePlatform extends AbstractGameObject implements IPlatform {

    private static final float MOVE_SPEED = 1.2f;
    private static final float MAX_MOVEMENT_DURATION = 1.7f;
    private float moveTimer;
    private int direction;

    public BluePlatform(Texture spriteSheet, float x, float y) {
        super(new Shape(x, y, 114, 30),
            new TextureRegion(spriteSheet, 5472, 36, 114, 30));
        this.moveTimer = 0;
        this.direction = 1;
    }

    @Override
    public void update(float deltaTime) {
        // dynamic platform, it does move
        moveTimer += deltaTime;
        if (moveTimer >= MAX_MOVEMENT_DURATION) {
            moveTimer = 0;
            direction *= -1;
        }

        this.position.add(MOVE_SPEED * direction, 0);
        this.setPosition(position.x, position.y);
    }

    @Override
    public boolean isStaticPlatform() {
        return false;
    }

    @Override
    public AbstractGameObject getSprite() {
        return this;
    }

    @Override
    public void renderPlatform(SpriteBatch batch) {
        this.draw(batch);
    }
}
