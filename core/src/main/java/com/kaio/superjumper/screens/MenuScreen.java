package com.kaio.superjumper.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kaio.superjumper.config.Config;

public class MenuScreen extends AbstractScreen {
    private final TextureRegion background;


    public MenuScreen(Game game, SpriteBatch batch, Texture spriteSheet) {
        super(game, batch, spriteSheet);
        this.background = new TextureRegion(spriteSheet, 4628, 0, 640, 1024);
    }

    @Override
    protected void drawObjects(SpriteBatch batch, float delta) {
        // nothing
    }

    @Override
    protected void drawBackground(SpriteBatch batch) {
        batch.draw(background, 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    }
}
