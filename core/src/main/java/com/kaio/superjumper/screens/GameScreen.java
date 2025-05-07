package com.kaio.superjumper.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kaio.superjumper.config.Config;
import com.kaio.superjumper.entities.World;
import com.kaio.superjumper.enums.WorldStateEnum;

public class GameScreen extends AbstractScreen {

    private final TextureRegion background;
    private final World world;
    private final BitmapFont myFont;

    protected GameScreen(Game game, SpriteBatch batch, Texture spriteSheet) {
        super(game, batch, spriteSheet);
        Gdx.app.log("MenuScreen", "Starting Game Screen");
        this.background = new TextureRegion(spriteSheet, 531, 155, 532, 850);
        this.myFont = new BitmapFont(Gdx.files.internal("ui/font.fnt"));
        myFont.setColor(Color.RED);
        myFont.getData().setScale(0.6f, 0.6f);
        this.world = new World(camera, spriteSheet);
        this.world.generateLevel();
    }

    @Override
    protected void drawBackground(SpriteBatch batch) {
        float posY = getCameraPosition().y;
        batch.draw(background, 0, posY - (viewport.getWorldHeight() / 2),
            Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    }

    private void checkGameOver() {
        if (world.getWorldState() == WorldStateEnum.GAME_OVER) {
            hide();
            dispose();
            setScreen(new GameOverScreen(game, batch, spriteSheet, world.getScore()));
        }
    }

    private float getScreenTopRightY() {
        return getCameraPosition().y + 395;
    }

    @Override
    protected void update(float delta) {
        world.update(delta, batch);
        batch.begin();
        myFont.draw(batch, "" + world.getScore(), 440f, getScreenTopRightY());
        batch.end();
        checkGameOver();
    }

    @Override
    protected void disposeObjects() {
        this.myFont.dispose();
    }
}
