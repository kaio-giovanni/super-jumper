package com.kaio.superjumper.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Jumper extends AbstractGameObject {
    private final TextureRegion texture;
    private final float velocity;

    public Jumper(TextureRegion texture, Rectangle rect, float velocity) {
        super(rect);
        this.texture = texture;
        this.velocity = velocity;
    }

    public float getVelocity() {
        return velocity;
    }

    @Override
    public void renderObject(SpriteBatch batch) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

}
