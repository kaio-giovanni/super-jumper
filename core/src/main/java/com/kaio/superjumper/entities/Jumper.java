package com.kaio.superjumper.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kaio.superjumper.model.Shape;

public class Jumper extends AbstractGameObject {
    private final float velocity = 10;

    public Jumper(Texture spriteSheet) {
        super(new Shape(0, 0, 0, 0), new TextureRegion(spriteSheet));
    }

    public float getVelocity() {
        return velocity;
    }

}
