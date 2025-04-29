package com.kaio.superjumper.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kaio.superjumper.model.Shape;

public class Platform extends AbstractGameObject {

    public Platform(Shape shape, TextureRegion region) {
        super(shape, region);
    }

    @Override
    public void update(float deltaTime) {
        // nothing
    }
}
