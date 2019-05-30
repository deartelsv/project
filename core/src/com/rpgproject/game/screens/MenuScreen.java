package com.rpgproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rpgproject.game.rpgProject;

public class MenuScreen implements Screen {

    private final rpgProject game; // логика игры
    public MenuScreen(rpgProject rpgProject) { // чо да как хз
        this.game = rpgProject;
    }

    //finals
    private final float AlphaBackground = 0.2f;
    //инц
    SpriteBatch batch;
    Texture backgroundMenuTexture;
    Texture grayTexture;
    Sprite backgroundMenuSprite;
    Sprite graySprite;
    //кнопка1 - выход
    Stage stage;
    TextButton button;
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    //кнопка1
    //кнопка2 - начать игру
    Stage stage1;
    TextButton button1;
    TextButton.TextButtonStyle textButtonStyle1;
    BitmapFont font1;
    Skin skin1;
    TextureAtlas buttonAtlas1;
    //кнопка2
    //кнопка3 - продолжить
    Stage stage2;
    TextButton button2;
    TextButton.TextButtonStyle textButtonStyle2;
    BitmapFont font2;
    Skin skin2;
    TextureAtlas buttonAtlas2;
    //кнопка3


    //camera and viewports
    private Viewport viewport1;
    private Camera camera1;
    //инц

    @Override
    public void show() {
        //инцилизация
        batch = new SpriteBatch();

        backgroundMenuTexture = new Texture(Gdx.files.internal("Graphics/bg/3png.png"), true);
        backgroundMenuSprite = new Sprite(backgroundMenuTexture);

        grayTexture = new Texture(Gdx.files.internal("Graphics/sdas.png"), true);
        graySprite = new Sprite(grayTexture);

        backgroundMenuSprite.setBounds(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        graySprite.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        graySprite.setAlpha(AlphaBackground);

        //инцилизация камеры и вьюпортов
        camera1 = new OrthographicCamera();
        viewport1 = new FitViewport(1280, 720, camera1);


        //кнопка1
        stage = new Stage(new FitViewport(1280, 720, camera1));
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("skins/skin4/skin4.atlas"));
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("up-button1");
        textButtonStyle.down = skin.getDrawable("down-button1");
        //кнопка2
        font1 = new BitmapFont();
        skin1 = new Skin();
        buttonAtlas1 = new TextureAtlas(Gdx.files.internal("skins/skin4/skin4.atlas"));
        skin1.addRegions(buttonAtlas);
        textButtonStyle1 = new TextButton.TextButtonStyle();
        textButtonStyle1.font = font;
        textButtonStyle1.up = skin.getDrawable("up-button1");
        textButtonStyle1.down = skin.getDrawable("down-button1");
        //кнопка3
        font2 = new BitmapFont();
        skin2 = new Skin();
        buttonAtlas2 = new TextureAtlas(Gdx.files.internal("skins/skin4/skin4.atlas"));
        skin2.addRegions(buttonAtlas);
        textButtonStyle2 = new TextButton.TextButtonStyle();
        textButtonStyle2.font = font;
        textButtonStyle2.up = skin.getDrawable("up-button1");
        textButtonStyle2.down = skin.getDrawable("down-button1");
        //textButtonStyle.checked = skin.getDrawable("checked-button");
        button = new TextButton("Exit Game", textButtonStyle);
        button1 = new TextButton("Start Game", textButtonStyle1);
        button2 = new TextButton("Continue", textButtonStyle1);
        stage.addActor(button);
        stage.addActor(button1);
        stage.addActor(button2);
        button.setPosition(50, 150);
        button1.setPosition(50, 250);
        button2.setPosition(50, 350);
        //читаем клик кнопки1
        button.addListener(new ClickListener() {
            //логика для кнопки
            int lox = 0;
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("test1 " + lox);
                lox++;
                System.exit(0);
            }
        });
        //читаем клик кнопки2
        button1.addListener(new ClickListener() {
            //логика для кнопки
            int lox = 0;
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("test2 " + lox);
                lox++;
                game.setScreen(new MainGameScreen(game));
            }
        });
        //читаем клик кнопки3
        button2.addListener(new ClickListener() {
            //логика для кнопки
            int lox = 0;
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("test3 " + lox);
                lox++;
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //123
        //123


        button.addListener(new ClickListener());

        batch.begin();
        //batch.draw(backgroundMenuTexture, 0,0);
            backgroundMenuSprite.draw(batch);
            graySprite.draw(batch);

        batch.end();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        game.dispose();

    }
}
