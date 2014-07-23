package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;

public class MyGame extends Game {

    public SplashScreen menu;
    public GameScreen gameScreen;
    LoadingScreen loadingScreen;
    HelpScreen helpScreen;
    public AssetManager manager;
    public boolean pause;

    @Override
    public void create() {
        pause = true;
        manager = new AssetManager();

        gameScreen = new GameScreen(this);
        menu = new SplashScreen(this);
        helpScreen = new HelpScreen(this);
        loadingScreen = new LoadingScreen(this);

        menu.game = this;
        helpScreen.game = this;
        helpScreen.buttonHandler.nextScreen = menu;
        menu.buttonHandlerHelp.nextScreen = helpScreen;
        menu.buttonHandlerHelp.inputListener.nextScreen = helpScreen;
        gameScreen.buttonHandlerPlay.nextScreen = gameScreen;
        gameScreen.buttonHandlerPlay.inputListener.nextScreen = gameScreen;
        gameScreen.buttonHandlerPause.nextScreen = gameScreen;
        gameScreen.buttonHandlerPause.inputListener.nextScreen = gameScreen;
        gameScreen.buttonHandlerMenu.nextScreen = menu;
        gameScreen.buttonHandlerMenu.inputListener.nextScreen = menu;
        gameScreen.game = this;

        this.setScreen(loadingScreen);
    }

    @Override
    public void dispose() {
        menu.dispose();
        gameScreen.dispose();
        helpScreen.dispose();
        loadingScreen.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getScreen().render(System.currentTimeMillis());
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
