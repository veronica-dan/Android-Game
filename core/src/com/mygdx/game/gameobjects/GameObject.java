package com.mygdx.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyAwesomeRectangle;
import com.mygdx.game.screens.MyGame;
import com.mygdx.game.screens.Textures;

/**
 * Created by Niki on 20.07.2014.
 */
public class GameObject extends Actor {

    public MyGame game;
    public Sprite sprite;
    public MyAwesomeRectangle boundingRectagle;

    public GameObject (MyGame game){
        this.game = game;
    }

    public GameObject(){
        super();
    }

    public MyAwesomeRectangle getBoundingRectangle() {
        sprite = new Sprite(Textures.deadBug);
        Rectangle rectangle = new Rectangle(sprite.getBoundingRectangle());
        boundingRectagle = new MyAwesomeRectangle(getX(), getY(), rectangle.getWidth(), rectangle.getHeight());
        return boundingRectagle;
    }
}
