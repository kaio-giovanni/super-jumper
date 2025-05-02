package com.kaio.superjumper.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kaio.superjumper.config.Config;

public class GameOverScreen extends AbstractScreen {

    private final TextureRegion background;
    private final BitmapFont myFont;
    private final Stage screenStage;

    public GameOverScreen(Game game, SpriteBatch batch, Texture spriteSheet, float score) {
        super(game, batch, spriteSheet);
        this.background = new TextureRegion(spriteSheet, 531, 155, 532, 850);
        this.screenStage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(screenStage);

        this.myFont = new BitmapFont(Gdx.files.internal("ui/font.fnt"));
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        Label.LabelStyle subtitleLabelStyle = new Label.LabelStyle();

        titleLabelStyle.font = myFont;
        titleLabelStyle.fontColor = Color.RED;

        subtitleLabelStyle.font = myFont;
        subtitleLabelStyle.fontColor = Color.BLACK;

        Label title = new Label("Game Over !!!", titleLabelStyle);
        title.setSize(50, 50);
        title.setPosition(235, 500);
        title.setAlignment(Align.center);

        String formattedScore = String.format("%.2f", score);
        Label subtitle = new Label("Your Score: " + formattedScore, subtitleLabelStyle);
        subtitle.setSize(50, 50);
        subtitle.setPosition(235, 430);
        subtitle.setAlignment(Align.center);

        screenStage.addActor(title);
        screenStage.addActor(subtitle);
    }

    @Override
    protected void drawBackground(SpriteBatch batch) {
        batch.draw(background, 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    }

    @Override
    protected void update(float delta) {
        screenStage.act();
        screenStage.draw();
    }

    @Override
    protected void disposeObjects() {
        myFont.dispose();
        screenStage.dispose();
    }
}
