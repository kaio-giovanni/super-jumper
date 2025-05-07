package com.kaio.superjumper.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kaio.superjumper.model.Shape;

public class Bullet extends AbstractGameObject {
    private static final float SPEED = 30f;
    public Bullet(Texture spriteSheet, float x, float y) {
        super(new Shape(x, y, 11, 18),
            new TextureRegion(spriteSheet, 2644, 66, 11, 18));
    }

    @Override
    public void update(float deltaTime) {
        velocity.add(0, SPEED);
        position.add(0, velocity.y * deltaTime);
        setPosition(position.x, position.y);
    }
}
