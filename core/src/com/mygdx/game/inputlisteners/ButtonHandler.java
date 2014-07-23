package com.mygdx.game.inputlisteners;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.screens.MyGame;
import com.mygdx.game.screens.MyScreen;

/**
 * Created by Niki on 15.07.2014.
 */
public class ButtonHandler extends Stage {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//
    public ImageButton button; //** the button - the only actor in program **//
    public MyGame game;
    public boolean newGame;
    public MyScreen nextScreen;
    public MyInputListener inputListener;


    public ButtonHandler(MyGame game, MyScreen nextScreen, String path, int x, int y, String but, String butClicked, boolean pause, boolean newGame) {

        this.game = game;
        this.nextScreen = nextScreen;

        inputListener = new MyInputListener(game, nextScreen, pause, newGame);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480); //** w/h ratio = 1.66 **//

        batch = new SpriteBatch();

        buttonsAtlas = new TextureAtlas(path); //** button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//

        this.clear();

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle(); //** Button properties **//
        style.up = buttonSkin.getDrawable(but);
        style.down = buttonSkin.getDrawable(butClicked);

        button = new ImageButton(style);
        button.setPosition(x, y);

        this.addActor(button);
        button.addListener(inputListener);
    }
}
