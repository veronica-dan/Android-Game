package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.inputlisteners.ButtonHandler;

/**
 * Created by Niki on 09.07.2014.
 */

public class SplashScreen extends MyScreen {

    public ButtonHandler buttonHandler, buttonHandlerHelp;
    public SpriteBatch batch;
    public Stage stage;

    public SplashScreen(MyGame game) {
        this.game = game;
        buttonHandler = new ButtonHandler(game, game.gameScreen, "play.txt", 150, 300, "button", "buttonclicked", false, true);
        buttonHandlerHelp = new ButtonHandler(game, game.helpScreen, "help.txt", 200, 130, "helpbutton", "helpbuttonclicked", true, false);

        batch = new SpriteBatch();
        stage = new Stage();
        stage.addActor(new Image(menuBackground));
        Image logo = new Image(new Texture(Gdx.files.internal("logo.png")));
        logo.setX(100);
        logo.setY(650);
        stage.addActor(logo);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.draw();
        buttonHandler.draw();
        buttonHandlerHelp.draw();
        batch.end();
    }

    @Override
    public void show() {
        super.show();
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(buttonHandler);
        inputMultiplexer.addProcessor(buttonHandlerHelp);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
}