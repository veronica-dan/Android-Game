package com.mygdx.game.gameengine;

import com.mygdx.game.gameobjects.Bug;
import com.mygdx.game.gameobjects.Food;
import com.mygdx.game.screens.MyGame;

import java.util.ArrayList;

/**
 * Created by Niki on 10.07.2014.
 */

public class GameApp {

    public ArrayList<Level> levels;
    public int nrOfLevels;
    public int currentLevel;
    public int score;
    public boolean lost;
    MyGame game;

    public GameApp(int nrOfLevels, MyGame game) {
        this.nrOfLevels = nrOfLevels;
        this.game = game;
        levels = new ArrayList<Level>(23);
    }

    public void newGame() {
        currentLevel = 0;
        score = 0;
        lost = false;
        levels = new ArrayList<Level>(nrOfLevels);
        for (int i = 0; i < nrOfLevels; i++) {
            levels.add(new Level(5, 10, 1 - 6/10 * i, game));
            new Thread(levels.get(i)).start();
        }
    }

    public void lost() {
        int count = 0;
       for (Food food : levels.get(currentLevel).foods)
            if (food.isEaten) {
                count ++;
            }
        if (count >= levels.get(currentLevel).nrOfDonuts) {
            lost = true;
        }
    }

    public void updateScore(){
        for (Bug bug : levels.get(currentLevel).bugs) {
                if (bug.dead && !bug.checked){
                    score += 100;
                    bug.checked = true;
            }
        }
    }
}
