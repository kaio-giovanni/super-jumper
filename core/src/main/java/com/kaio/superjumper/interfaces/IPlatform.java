package com.kaio.superjumper.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kaio.superjumper.entities.AbstractGameObject;

public interface IPlatform {

    AbstractGameObject getSprite();

    void renderPlatform(SpriteBatch batch);

    boolean isStaticPlatform();
}
