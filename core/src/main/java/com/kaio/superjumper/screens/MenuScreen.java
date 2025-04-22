package com.kaio.superjumper.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kaio.superjumper.config.Config;

public class MenuScreen extends AbstractScreen {
    private final TextureRegion background;
    private final ImageButton playBtn;
    private final Stage screenStage;

    public MenuScreen(Game game, SpriteBatch batch, Texture spriteSheet) {
        super(game, batch, spriteSheet);
        this.background = new TextureRegion(spriteSheet, 4628, 0, 640, 1024);

        this.screenStage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(screenStage);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(spriteSheet, 1036, 0, 222, 80));
        style.imageDown = new TextureRegionDrawable(new TextureRegion(spriteSheet, 1258, 0, 222, 80));

        this.playBtn = new ImageButton(style);
        this.playBtn.setSize(220, 80); // Set size
        this.playBtn.setPosition(150, 80); // Set position
        this.playBtn.addListener(new ClickListener(Input.Buttons.LEFT) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Execute action when clicked
                Gdx.app.log("clicked", "(" + x + ", " + y + ")");
            }
        });
        screenStage.addActor(playBtn);
    }

    @Override
    protected void mouseButtonPressed(Vector3 clickedPoint) {
        // handle mouse button pressed in the screen
    }

    @Override
    protected void update(float delta) {
        screenStage.act();
        screenStage.draw();
    }

    @Override
    protected void drawBackground(SpriteBatch batch) {
        batch.draw(background, 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    }

    @Override
    protected void disposeObjects() {
        screenStage.dispose();
    }
}
