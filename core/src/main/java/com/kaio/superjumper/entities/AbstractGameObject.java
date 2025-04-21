package com.kaio.superjumper.entities;

import com.badlogic.gdx.math.Rectangle;
import com.kaio.superjumper.interfaces.IObjectRenderer;

public abstract class AbstractGameObject implements IObjectRenderer {
    private final Rectangle rect;

    protected AbstractGameObject(Rectangle rect) {
        this.rect = rect;
    }

    public Rectangle getRect() {
        return rect;
    }

    public float getX() {
        return rect.getX();
    }

    public float getY() {
        return rect.getY();
    }

    public float getWidth() {
        return rect.getWidth();
    }

    public float getHeight() {
        return rect.getHeight();
    }

    public boolean checkCollision(AbstractGameObject other) {
        return this.rect.overlaps(other.getRect());
    }
}
