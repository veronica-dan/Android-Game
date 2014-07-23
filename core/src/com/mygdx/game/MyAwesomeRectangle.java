package com.mygdx.game;

/**
 * Created by Niki on 11.07.2014.
 */
public class MyAwesomeRectangle {
    public float x;
    public float y;
    public float width;
    public float height;

    public MyAwesomeRectangle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public MyAwesomeRectangle move(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public boolean intersects(MyAwesomeRectangle r) {
        return r.width > 0 && r.height > 0 && width > 0 && height > 0
                && r.x < (x + width/2) && (r.x + r.width/2) > x
                && r.y < (y + height/2) && (r.y + r.height/2) > y;
    }
}
