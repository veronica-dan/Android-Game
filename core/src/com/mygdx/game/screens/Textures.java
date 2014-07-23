package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Sorin on 20.07.2014.
 */
public class Textures {
    public static Texture backgroundWood = new Texture(Gdx.files.internal("background.png"));
    public static Texture homeScreen;
    public static Texture deadBug = new Texture(Gdx.files.internal("bugs/dead.png"));
    public static Texture eatenFood = new Texture(Gdx.files.internal("food/2eaten.png"));
    public static Texture food = new Texture(Gdx.files.internal("food/2.png"));
    public static Texture pauseScreen =  new Texture(Gdx.files.internal("background3.png"));
    public static Texture looseScreen =  new Texture(Gdx.files.internal("youLose.png"));
    public static Texture levelFinished =  new Texture(Gdx.files.internal("levelFinished.png"));
    public static Texture logoHomeScreen;
    public static Texture logoLoadingScreen;

}
