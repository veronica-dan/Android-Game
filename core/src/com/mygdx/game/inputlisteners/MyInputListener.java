package com.mygdx.game.inputlisteners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.screens.MyGame;
import com.mygdx.game.screens.MyScreen;

/**
 * Created by Niki on 15.07.2014.
 */
public class MyInputListener extends InputListener {

    public MyGame game;
    public MyScreen nextScreen;
    public boolean pause;
    public boolean newGame;

    public MyInputListener(MyGame game, MyScreen nextScreen, boolean pause, boolean newGame) {
        this.game = game;
        this.nextScreen = nextScreen;
        this.pause = pause;
        this.newGame = newGame;
    }

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }

    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        game.setScreen(nextScreen);
        if(pause)
            game.pause = true;
        else {
            if(newGame) {
                game.gameScreen.gameApp.levels.clear();
                game.gameScreen.gameApp.newGame();

            }
            game.pause = false;
        }

    }
}
