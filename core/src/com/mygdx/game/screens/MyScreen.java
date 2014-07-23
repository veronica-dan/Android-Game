package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Niki on 09.07.2014.
 */

public abstract class MyScreen implements Screen {

    public MyGame game;

    public Texture background;
    public Texture menuBackground;
    public Texture pausedBackground;

    public MyScreen(MyGame game){this.game = game;}
    public MyScreen() {
        background = new Texture(Gdx.files.internal("background.png"));
        menuBackground = new Texture(Gdx.files.internal("background2.png"));
        pausedBackground = new Texture(Gdx.files.internal("background3.png"));
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}
