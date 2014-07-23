package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameengine.GameApp;
import com.mygdx.game.inputlisteners.ButtonHandler;

/**
 * Created by Niki on 09.07.2014.
 */

public class GameScreen extends MyScreen {

    public GameApp gameApp;
    public ButtonHandler buttonHandlerMenu;
    public ButtonHandler buttonHandlerPause;
    public ButtonHandler buttonHandlerPlay;
    public SpriteBatch batch;
    private BitmapFont font;
    InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public GameScreen(MyGame game) {

        this.game = game;
        gameApp = new GameApp(22, game);

        buttonHandlerMenu= new ButtonHandler(game, game.menu, "menu.txt", 10, 10, "menubutton", "menubuttonclicked",true, false);
        buttonHandlerPause = new ButtonHandler(game, this, "paused.txt", 110, 10, "pause", "pauseclciked",true, false);
        buttonHandlerPlay = new ButtonHandler(game, this, "resume.txt", 210, 10, "playbutton", "playbuttonclicked",false, false);

        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("default.fnt"));
        font.setColor(Color.RED);
        font.setScale(2.4f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 2, 2, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!game.pause) {
            gameApp.levels.get(gameApp.currentLevel).act();
            gameApp.levels.get(gameApp.currentLevel).collision();
        }
        gameApp.lost();

        batch.begin();

        gameApp.levels.get(gameApp.currentLevel).draw();
        buttonHandlerMenu.draw();
        buttonHandlerPause.draw();
        buttonHandlerPlay.draw();
        batch.end();

        gameApp.updateScore();
        batch.begin();
        font.draw(batch, "Score: "+ Integer.toString(gameApp.score), 20, Gdx.graphics.getHeight() - 30);
        font.draw(batch, "Level: "+ Integer.toString(gameApp.currentLevel+1), Gdx.graphics.getWidth()-180, Gdx.graphics.getHeight() - 30);
        batch.end();

        inputMultiplexer.addProcessor(gameApp.levels.get(gameApp.currentLevel));
        if(gameApp.currentLevel > 0)
           inputMultiplexer.removeProcessor(gameApp.levels.get(gameApp.currentLevel-1));

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        inputMultiplexer.addProcessor(buttonHandlerMenu);
        inputMultiplexer.addProcessor(buttonHandlerPause);
        inputMultiplexer.addProcessor(buttonHandlerPlay);

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

}
