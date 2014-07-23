package com.mygdx.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.mygdx.game.inputlisteners.ActorInputListener;
import com.mygdx.game.screens.MyGame;

/**
 * Created by Niki on 09.07.2014.
 */

public class Bug extends GameObject {


    private float elapsedTime;
    private Animation animation;
    public int direction;//1-left, 2-right, 3-up, 4-down
    private MoveToAction moveToAction;
    public float offsetTime;
    public SpriteBatch batch;
    public boolean checked;
    public boolean dead;

    public Bug(float posX, float posY, int direction, Animation animation, MyGame game) {
        super(game);
        this.direction = direction;
        elapsedTime = 0;
        this.animation = animation;
        boundingRectagle = getBoundingRectangle();

        setX(posX);
        setY(posY);
        move();

        checked = false;
        ActorInputListener actorInputListener = new ActorInputListener(this);
        this.addListener(actorInputListener);
    }

    public void move() {
        if (!dead) {
            //if (!game.pause) {
            switch (direction) {
                case 1://goin left
                    moveToAction = new MoveToAction();
                    moveToAction.setPosition(10f, getY());
                    moveToAction.setDuration(10f);
                    addAction(moveToAction);
                    break;

                case 2://goin right
                    moveToAction = new MoveToAction();
                    moveToAction.setPosition(500f, getY());
                    moveToAction.setDuration(10f);
                    addAction(moveToAction);
                    break;

                case 3://goin up
                    moveToAction = new MoveToAction();
                    moveToAction.setPosition(getX(), 800f);
                    moveToAction.setDuration(10f);
                    addAction(moveToAction);
                    break;

                case 4://goin dooooown
                    moveToAction = new MoveToAction();
                    moveToAction.setPosition(getX(), 10f);
                    moveToAction.setDuration(10f);
                    addAction(moveToAction);
                    break;
            }
        } else {
            if (offsetTime >= 5)
                setVisible(false);
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        if (!dead) {
            batch.draw(animation.getKeyFrame(elapsedTime, true), getX(), getY());
            boundingRectagle.move(getX(), getY());
        } else {
            batch.draw(new Texture(Gdx.files.internal("bugs/dead.png")), getX(), getY());
            offsetTime++;
            move();
        }
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (!isVisible() || getTouchable() == Touchable.disabled)
            return null;

        float centerX = getBoundingRectangle().width / 2;
        float centerY = getBoundingRectangle().height / 2;

        // Calculate radius of circle
        float radius = (float) Math.sqrt(centerX * centerX + centerY * centerY);
        radius = radius + 20;

        // And distance of point from the center of the circle
        float distance = (float) Math.sqrt(((centerX - x) * (centerX - x)) + ((centerY - y) * (centerY - y)));

        // If the distance is less than the circle radius, it's a hit
        if (distance <= radius + 20)
            return this;
        // Otherwise, it isnt
        return null;
    }
}
