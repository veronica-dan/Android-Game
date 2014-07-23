package com.mygdx.game.inputlisteners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.gameobjects.Bug;

/**
 * Created by Niki on 15.07.2014.
 */

public class ActorInputListener extends InputListener {

    private Bug bug;

    public ActorInputListener(Bug bug) {
        super();
        this.bug = bug;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
        bug.dead = true;
        return true;
    }

}
