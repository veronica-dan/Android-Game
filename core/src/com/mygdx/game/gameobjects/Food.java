package com.mygdx.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Niki on 10.07.2014.
 */

public class Food extends GameObject {

    public Texture texture, textureEaten;
    public boolean isEaten;
    public SpriteBatch batch;

    public Food(Texture texture, Texture textureEaten, float posX, float posY) {
        batch = new SpriteBatch();
        this.texture = texture;
        this.textureEaten = textureEaten;
        setX(posX);
        setY(posY);
    }

    public void Collision() {
        isEaten = true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if (!isEaten)
            batch.draw(texture, getX(), getY());
        else {
            batch.draw(textureEaten, getX(), getY());
        }
    }
}
