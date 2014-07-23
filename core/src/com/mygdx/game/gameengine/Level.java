package com.mygdx.game.gameengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.gameobjects.Bug;
import com.mygdx.game.gameobjects.Food;
import com.mygdx.game.inputlisteners.ButtonHandler;
import com.mygdx.game.screens.MyGame;
import com.mygdx.game.screens.Textures;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * Created by Niki on 10.07.2014.
 */

public class Level extends Stage implements Runnable {

    public ArrayList<Food> foods;
    public ArrayList<Bug> bugs;
    public int nrOfDonuts;
    public int nrOfBugs;
    public int generatedBugs;
    public Image pause;
    public Image levelFinished;
    public Image youLose;
    public MyGame game;
    public ButtonHandler buttonHandlerNext;

    private final long delay;
    private TextureAtlas textureAtlasLeft, textureAtlasRight, textureAtlasUp, textureAtlasDown;
    private Animation animationLeft, animationRight, animationUp, animationDown;


    public Level(int nrOfDonuts, int nrOfBugs, long delay, MyGame game) {
        foods = new ArrayList<Food>();
        bugs = new ArrayList<Bug>();

        this.nrOfDonuts = nrOfDonuts;
        this.nrOfBugs = nrOfBugs;
        this.game = game;
        this.delay = delay;

        game.manager.load("bugs/walkLeft.txt", TextureAtlas.class);
        game.manager.load("bugs/walkRight.txt", TextureAtlas.class);
        game.manager.load("bugs/walkUp.txt", TextureAtlas.class);
        game.manager.load("bugs/walkDown.txt", TextureAtlas.class);
        game.manager.load("background.png", Texture.class);

        game.manager.finishLoading();

        textureAtlasLeft = game.manager.get("bugs/walkLeft.txt", TextureAtlas.class);
        textureAtlasLeft.findRegion("walkLeft");
        animationLeft = new Animation(1 / 10f, textureAtlasLeft.getRegions());

        textureAtlasRight = game.manager.get("bugs/walkRight.txt", TextureAtlas.class);
        textureAtlasRight.findRegion("walkRight");
        animationRight = new Animation(1 / 10f, textureAtlasRight.getRegions());

        textureAtlasUp = game.manager.get("bugs/walkUp.txt", TextureAtlas.class);
        textureAtlasUp.findRegion("walkUp");
        animationUp = new Animation(1 / 10f, textureAtlasUp.getRegions());

        textureAtlasDown = game.manager.get("bugs/walkDown.txt", TextureAtlas.class);
        textureAtlasDown.findRegion("walkDown");
        animationDown = new Animation(1 / 10f, textureAtlasDown.getRegions());

        pause = new Image(Textures.pauseScreen);
        pause.setX(Gdx.graphics.getWidth() / 2 - pause.getWidth() / 2);
        pause.setY(Gdx.graphics.getHeight() / 2 - pause.getHeight() / 2);
        addActor(new Image(Textures.backgroundWood));
        generateFood();
        generatedBugs = 0;
        levelFinished = new Image(Textures.levelFinished);
        levelFinished.setX(Gdx.graphics.getWidth()/2 - 200);
        levelFinished.setY(400);
        youLose = new Image(Textures.looseScreen);
        youLose.setX(Gdx.graphics.getWidth()/2 - 250);
        youLose.setY(400);
    }

    public void generateFood() {
        Random rand = new Random();
        int x0 = (Gdx.graphics.getWidth()) / 2 - 200;
        int x1 = (Gdx.graphics.getWidth()) / 2 + 100;
        int y0 = (Gdx.graphics.getHeight()) / 2 - 300;
        int y1 = (Gdx.graphics.getHeight()) / 2 + 300;

        for (int i = 0; i < nrOfDonuts; i++) {
            int id = 2;
            id++;
            foods.add(new Food(new Texture(Gdx.files.internal("food/2.png")),
                    new Texture(Gdx.files.internal("food/2eaten.png")),
                    rand.nextInt(x1 - x0) + x0,
                    rand.nextInt(y1 - y0) + y0));
        }
    }

    @Override
    public void run() {
        try {
            while (game.pause){
                TimeUnit.SECONDS.sleep(delay);
            }

            while (game.gameScreen.gameApp.levels.get(game.gameScreen.gameApp.currentLevel) != this ||
                   game.gameScreen.gameApp.levels.isEmpty()) {
                TimeUnit.SECONDS.sleep(delay);
            }

            if(game.gameScreen.gameApp.levels.get(game.gameScreen.gameApp.currentLevel) == this &&
               game.gameScreen.gameApp.currentLevel>0) {
                addActor(levelFinished);
                TimeUnit.SECONDS.sleep(delay+1);
            }

            for (int i = 0; i < nrOfDonuts; i++) {
                this.addActor(foods.get(i));
            }
            while (generatedBugs < this.nrOfBugs) {
                levelFinished.remove();
                while (game.pause) {
                    addActor(pause);
                    TimeUnit.SECONDS.sleep(delay);
                }
                if(game.gameScreen.gameApp.lost){
                    addActor(youLose);
                    TimeUnit.SECONDS.sleep(delay + 1);
                    game.setScreen(game.menu);//<-- problema e aici cand intra in meniu si incerc sa dau play
                }
                pause.remove();
                spawnBug();
                generatedBugs++;

                TimeUnit.SECONDS.sleep(delay);
            }

            this.clear();
            game.gameScreen.gameApp.currentLevel++;
            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void spawnBug() {
        Random rand = new Random();
        int direction = rand.nextInt(4);
        int low, high;
        Bug bug;
        direction++;

        switch (direction) {
            case 1://left
                low = 50;
                high = Gdx.graphics.getHeight() - 40;
                bug = new Bug(Gdx.graphics.getWidth(), rand.nextInt(high - low) + low, 1, animationLeft, game);
                bugs.add(bug);
                addActor(bug);
                break;
            case 2://right
                low = 50;
                high = Gdx.graphics.getHeight() - 40;
                bug = new Bug(0, rand.nextInt(high - low) + low, 2, animationRight, game);
                bugs.add(bug);
                addActor(bug);
                break;
            case 3://up
                low = 50;
                high = Gdx.graphics.getWidth() - 20;
                bug = new Bug(rand.nextInt(high - low) + low, 0, 3, animationUp, game);
                bugs.add(bug);
                addActor(bug);
                break;
            case 4://down
                low = 40;
                high = Gdx.graphics.getWidth() - 40;
                bug = new Bug(rand.nextInt(high - low), Gdx.graphics.getHeight(), 4, animationDown, game);
                bugs.add(bug);
                addActor(bug);
                break;
        }

    }

    public void collision() {
        Bug bug;

        for (int i = 0; i < generatedBugs; i++) {
            bug = this.bugs.get(i);
            for (Food food : foods) {
                if (!food.isEaten && bug.isVisible()) {
                    if (bug.getBoundingRectangle().intersects(food.getBoundingRectangle())) {
                        food.Collision();
                    }
                }
            }
        }

    }

}
