package com.kaio.superjumper.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kaio.superjumper.model.Shape;

public abstract class AbstractGameObject extends Sprite {

    protected AbstractGameObject(Shape shape, TextureRegion texture) {
        super(texture);
        this.setPosition(shape.getX(), shape.getY());
        this.setSize(shape.getWidth(), shape.getHeight());

    }

}
