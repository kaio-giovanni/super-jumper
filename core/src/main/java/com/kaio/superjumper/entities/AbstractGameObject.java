package com.kaio.superjumper.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.kaio.superjumper.model.Shape;

public abstract class AbstractGameObject extends Sprite {

    protected final Vector2 velocity;
    protected final Vector2 position;

    protected AbstractGameObject(Shape shape, TextureRegion texture) {
        super(texture);
        this.velocity = new Vector2(0, 0);
        this.position = new Vector2(shape.getX(), shape.getY());
        this.setPosition(shape.getX(), shape.getY());
        this.setSize(shape.getWidth(), shape.getHeight());
    }

    public abstract void update(float deltaTime);

    public boolean checkCollision(AbstractGameObject other) {
        return this.getBoundingRectangle().overlaps(other.getBoundingRectangle());
    }

}
