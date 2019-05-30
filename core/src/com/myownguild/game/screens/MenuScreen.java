package com.myownguild.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.myownguild.game.Main;
import com.myownguild.game.StylesUI;
import com.myownguild.game.UI.CustomImageButton;

import javax.xml.soap.Text;

public class MenuScreen implements Screen {

    //init all
    private Integer curWindow = 0; /*
    0 - ничего
    1 - инфа о гильдии
    2 - настройки
    */
    // debug things
    private Label debugText;
    private String debugString;
    //stage and camera
    protected Main game; // protected, как я понял, позволяет в дочерних классах юзать себя
    protected Stage stage;  // каждый screen имеет свой stage
    protected OrthographicCamera camera; //камера
    protected SpriteBatch batch; // рисовалка
    // basics
    Table table;
    Skin skin;
    // инцилизация UI
    private TextButton play;
    private TextButton createGuild;
    private TextButton GuildMenu;
    private TextButton settings;

    private Slider musicSlider;
    private Slider soundSlider;

    private TextButton musicButton;
    private TextButton soundButton;

    private Integer musicValue;
    private Integer soundValue;

    private Label musicLabel;
    private Label soundLabel;

    private ImageButton exit;
    private ImageButton info;

    private Texture iconEdit;
    private Texture iconAccept;
    private TextureRegionDrawable trd1;
    private TextureRegionDrawable trd2;

    private ImageButton.ImageButtonStyle imageButtonStyle;
    private ImageButton edit;
    private ImageButton accept;

    Window windowGuildInfo;
    Window windowSettings;

    TextField guildName;

    //
    //
    /*константы

     */
    private final float MIN = 0;
    private final float MAX = 100;
    private final float INC = 10;
    //


    public MenuScreen(Main game) {
        this.game = game;
        createCamera();
        stage = new Stage(new StretchViewport(Main.WIDTH, Main.HEIGHT, camera)); // скретч - обрезает, фит - сохраняеет соотношения
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage); // загружаем stage (???)
        init(); // проводим инцилизацию нужных компонентов
    }

    private void init() {
        initUI();
        textField();
    }

    private void initUI(){
        table = new Table();
        //
        table.setSize(game.WIDTH, game.HEIGHT);
        //
        int pad = 5;
        int elements = 4;

        float height = ((table.getHeight()-50)/elements)-pad*2;
        int firstCollumnWidth = (int) (table.getWidth()/8*3);
        int secondCollumnWidth = (int) (table.getWidth()/8*4);
        // инцилизация  стилей
        TextButton.TextButtonStyle textButtonStyle;
        Slider.SliderStyle sliderStyle;
        TextField.TextFieldStyle textFieldStyle;

        BitmapFont font;
        Skin skin;
        TextureAtlas buttonAtlas;

        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("skins/curSkin/skin-composer-ui.atlas"));
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = StylesUI.FONT;
        textButtonStyle.up = skin.getDrawable("button");
        textButtonStyle.down = skin.getDrawable("button-pressed");

        sliderStyle = new Slider.SliderStyle();
        sliderStyle.knobDown = skin.getDrawable("slider-knob-pressed");
        sliderStyle.knobOver = skin.getDrawable("slider-knob-over");
        sliderStyle.knob = skin.getDrawable("slider-knob");
        //sliderStyle.knobAfter = skin.getDrawable("slider-knob-over");
        //sliderStyle.knobBefore = skin.getDrawable("");
        sliderStyle.background = skin.getDrawable("textfield");

        musicSlider = new Slider(MIN, MAX, INC, false, sliderStyle);
        soundSlider = new Slider(MIN, MAX, INC, false, sliderStyle);

        musicButton = new TextButton(((Integer)(int)(musicSlider.getValue())).toString(), textButtonStyle);
        soundButton = new TextButton("0", textButtonStyle);
        //image button styles
        imageButtonStyle = StylesUI.imageButtonStyle;

        imageButtonStyle.down = skin.getDrawable("button-pressed");
        imageButtonStyle.up = skin.getDrawable("button");

        //
        iconAccept = new Texture("icons/checked.png");
        iconEdit = new Texture("icons/pencil-edit-button.png");

        trd1 = new TextureRegionDrawable(iconAccept);
        trd2 = new TextureRegionDrawable(iconEdit);

        imageButtonStyle.imageDown = new TextureRegionDrawable(trd2);
        imageButtonStyle.imageUp = new TextureRegionDrawable(trd2);

        //
        edit = new ImageButton(imageButtonStyle);



        /*
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("skins/curSkin/skin-composer-ui.atlas")));
        sliderStyle.knobDown = skin.getDrawable("slider-knob");
        musicSlider = new Slider(MIN, MAX, INC, false, sliderStyle);
        stage.addActor(musicSlider);
        musicSlider.setPosition(150, 50);
         */
        //
        textFieldStyle = new TextField.TextFieldStyle();

        textFieldStyle.font = StylesUI.FONT;
        textFieldStyle.fontColor = Color.BLACK;
        textFieldStyle.background = skin.getDrawable("textfield");
        textFieldStyle.disabledFontColor = Color.GRAY;
        //
        guildName = new TextField("", textFieldStyle);
        guildName.setText("YourGuild");
        guildName.setDisabled(true);
        // Стиль окон
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background = skin.getDrawable("window");
        windowStyle.titleFont = StylesUI.FONT;
        //
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = StylesUI.FONT;
        labelStyle.fontColor = Color.BLACK;
        // лейблы в настройках
        musicLabel = new Label("Music: ", labelStyle);
        soundLabel = new Label("Sound: ", labelStyle);
        //
        // Инцилизация окон
        windowGuildInfo = new Window("Guild Info", windowStyle);
        windowSettings = new Window("Settings", windowStyle);
        // таблицы для окон

        Label textGuildName = new Label("Guild Name: ", labelStyle);
        textGuildName.setAlignment(Align.center);
        guildName.setAlignment(Align.center);

        //tableWindow.add(textCreateGuild).pad(pad).width(firstCollumnWidth).height(height/4).align(Align.top);
        //tableWindow.add(guildName).pad(pad).width(firstCollumnWidth).height(height/4).align(Align.top);

        windowGuildInfo.setMovable(false);
        windowGuildInfo.getTitleLabel().setAlignment(0);
        windowGuildInfo.setDebug(true);

        windowSettings.defaults().expand();
        windowSettings.setMovable(false);
        windowSettings.getTitleLabel().setAlignment(0);
        windowSettings.setDebug(true);

        windowSettings.row();
        windowSettings.add(musicLabel).pad(pad);
        windowSettings.add(musicSlider).width(secondCollumnWidth/2);
        windowSettings.add(musicButton).width(firstCollumnWidth/5).row();
        windowSettings.add(soundLabel).pad(pad);
        windowSettings.add(soundSlider).width(secondCollumnWidth/2);
        windowSettings.add(soundButton).width(firstCollumnWidth/5).row();


        // инцилизация содержимого окон настройки и информации о гильдии

        //

        play = new TextButton("Play", textButtonStyle);
        createGuild = new TextButton("Create Guild", textButtonStyle);
        GuildMenu = new TextButton("Guild Menu", textButtonStyle);
        settings = new TextButton("Settings", textButtonStyle);

        //guildName = new TextField("Hi", textFieldStyle);

        //window.add(guildName);

        //table.setFillParent(true);
        table.setDebug(true);
        //table.defaults().expand();
        //table.add(textGuildName).pad(pad).width(firstCollumnWidth).height(height/4);
        Table editTable = new Table();
        editTable.setSize(firstCollumnWidth+secondCollumnWidth, height/4);

        editTable.add(edit).pad(pad).height(height/4).width(height/4);
        //editTable.add(guildName);
        table.add(editTable).pad(pad);
        table.add(guildName).pad(pad).width(secondCollumnWidth).height(height/4);

        table.row();
        table.add(play).pad(pad).width(firstCollumnWidth).height(height/2); // тип создание ячеек
        table.add(createGuild).pad(pad).width(secondCollumnWidth).height(height/2);// тип создание ячеек
        table.row();
        table.add(GuildMenu).pad(pad).width(firstCollumnWidth).height(height/2);
        table.add(settings).pad(pad).width(secondCollumnWidth).height(height/2);

        table.row();
        table.add(windowGuildInfo).pad(pad).center().width(firstCollumnWidth).height(height*1.5f);
        table.add(windowSettings).pad(pad).center().width(secondCollumnWidth).height(height*1.5f);
        //

        //
        stage.addActor(table);

        table.setPosition(0, 0);
    }

    private void GuildInfo(boolean visible){
        windowGuildInfo.setVisible(visible);
    }

    private void createCamera(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
        camera.update(); // обновление камеры
    }

    private void buttonClickListeners(){

        GuildMenu.addListener(new ClickListener(){ //кнопка вскрытия/закрытия инфо меню с гильдиями
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (windowGuildInfo.isVisible()) {
                    windowGuildInfo.setVisible(false);
                } else {
                    windowGuildInfo.setVisible(true);
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        settings.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (windowSettings.isVisible()) {
                    windowSettings.setVisible(false);
                } else {
                    windowSettings.setVisible(true);
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        play.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        edit.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (guildName.isDisabled()){
                    guildName.setDisabled(false);
                    imageButtonStyle.imageUp = trd1;
                    imageButtonStyle.imageDown = trd1;
                } else {
                    guildName.setDisabled(true);
                    imageButtonStyle.imageUp = trd1;
                    imageButtonStyle.imageDown = trd1;
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });


    }

    private void sliderChangeListeners(){
        // Slider listener
        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                musicValue = (Integer) (int) musicSlider.getValue();
                System.out.println(musicValue);
                musicButton.setText(musicValue.toString());
            }
        });

        soundSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                soundValue = (Integer) (int) soundSlider.getValue();
                System.out.println(soundValue);
                soundButton.setText(soundValue.toString());
            }
        });

    }

    private void textField(){
        guildName.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                game.guildName = guildName.getText();
            }
        });
    }

    @Override
    public void show() {
        buttonClickListeners();
        sliderChangeListeners();

    }

    @Override
    public void render(float delta) {
        update(delta);
        batch.begin();
        batch.end();
    }

    private void update(float delta){
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
