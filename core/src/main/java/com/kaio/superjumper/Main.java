package com.kaio.superjumper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kaio.superjumper.screens.MenuScreen;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends Game {
    private SpriteBatch batch;
    private Texture spriteSheet;

    @Override
    public void create() {
        batch = new SpriteBatch();
        spriteSheet = new Texture("spritesheet.png");
        setScreen(new MenuScreen(this, batch, spriteSheet));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        spriteSheet.dispose();
    }
}
