package com.kaio.superjumper.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kaio.superjumper.model.Shape;

public class FlyingEnemy extends AbstractGameObject {

    public FlyingEnemy(Shape shape, TextureRegion texture) {
        super(shape, texture);
    }

    @Override
    public void update(float deltaTime) {
        //
    }
}
