package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.inputlisteners.ButtonHandler;

/**
 * Created by Niki on 18.07.2014.
 */
public class HelpScreen extends MyScreen{
    public ButtonHandler buttonHandler;
    public SpriteBatch batch;
    Image text;
    public Stage stage;

    public HelpScreen(MyGame game) {
        this.game = game;
        buttonHandler = new ButtonHandler(game, game.menu, "gotit.txt", 170, 100, "gotitbutton", "gotitbuttonclicked", true, false);
        batch = new SpriteBatch();
        stage = new Stage();
        Image text = new Image(new Texture(Gdx.files.internal("text.png")));
        text.setX(20);
        text.setY(450);
        stage.addActor(new Image(background));
        stage.addActor(text);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.draw();
        buttonHandler.draw();
        batch.end();
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(buttonHandler);
    }
}
