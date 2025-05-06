package com.kaio.superjumper.entities.platforms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kaio.superjumper.entities.AbstractGameObject;
import com.kaio.superjumper.interfaces.IPlatform;
import com.kaio.superjumper.model.Shape;

public class GreenPlatform extends AbstractGameObject implements IPlatform {

    public GreenPlatform(Texture spriteSheet, float x, float y) {
        super(new Shape(x, y, 114, 30),
            new TextureRegion(spriteSheet, 5472, 2, 114, 30));
    }

    @Override
    public void update(float deltaTime) {
        // static platform, it does not move
    }

    @Override
    public boolean isStaticPlatform() {
        return true;
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
