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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.myownguild.game.Main;
import com.myownguild.game.Missons;
import com.myownguild.game.StringSplit;
import com.myownguild.game.StylesUI;
import com.myownguild.game.UI.CustomLabel;
import com.myownguild.game.UI.CustomWindow;

public class GameScreen implements Screen {

    protected Main game; // protected, как я понял, позволяет в дочерних классах юзать себя
    protected Stage stage;  // каждый screen имеет свой stage
    protected OrthographicCamera camera; //камера
    protected SpriteBatch batch; // рисовалка

    //init ui
    private Skin skin = StylesUI.skin;
    private BitmapFont font = StylesUI.FONT;
    private Window.WindowStyle windowStyle;
    private TextButton.TextButtonStyle textButtonStyle;
    private Label.LabelStyle labelStyle;

    private Table table;
    // finals
    final int pad = 15;

    private Window stats;
    private Window guildInfo;
    private Window missionInfo;
    private Window clickZone;
    private Window navigation;

    //stats
    private CustomLabel curGold;
    private CustomLabel curDay;

    // guildInfo

    private CustomLabel guildLvl;
    private CustomLabel guildStats;

    // misson info
    private Missons missons;
    private CustomLabel missionName;
    private CustomLabel missionLvl;
    private CustomLabel missionHp;
    private ProgressBar barHp;
    private ProgressBar.ProgressBarStyle progressBarStyle;
    private CustomLabel missionReward;

    private Integer curHP;


    // bar
    private Texture iconHome;
    private Texture iconUpgrade;
    private TextureRegionDrawable trdHome;
    private TextureRegionDrawable trdUpgrade;

    private ImageButton.ImageButtonStyle imageButtonStyle;
    private ImageButton.ImageButtonStyle imageButtonStyle2;
    private ImageButton home;
    private ImageButton upgrades;

    //
    private Sprite spriteHome;

    public GameScreen(Main game) {
        this.game = game;
        createCamera();
        stage = new Stage(new StretchViewport(Main.WIDTH, Main.HEIGHT, camera)); // скретч - обрезает, фит - сохраняеет соотношения
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage); // загружаем stage (???)
         // проводим инцилизацию нужных компонентов
    }

    private void init() {
        skin.addRegions(StylesUI.ATLAS);

        //
        Styles();
        //
        table = new Table();
        table.setSize(game.WIDTH, game.HEIGHT);
        table.setDebug(true);
        //

        initIntegers();
        newMission();

        initLabels();
        initWindows();

        buttonListener();

        initImageButtons();
        initHomeImageButton();

        initWindowsActors();

        initTable();



        clickZoneListener();

        stage.addActor(table);
    }
    private void Styles(){
        //window style
        windowStyle = new Window.WindowStyle();
        windowStyle.background = skin.getDrawable("window");
        windowStyle.titleFont = StylesUI.FONT;
        //Label style
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.BLACK;
        //Bar style
        TextureRegionDrawable textureBar = textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/barRed.png"))));;
        progressBarStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
        barHp = new ProgressBar(0, 300, 30, false, progressBarStyle);
        //table.row();
        //table.add(barHp);

    }

    private void initTable(){
        table.add(stats).pad(pad).width(game.WIDTH/1.5f).height(game.HEIGHT/10);
        table.row();
        table.add(guildInfo).pad(pad).width(game.WIDTH/1.5f).height(game.HEIGHT/8);
        table.row();
        table.add(missionInfo).pad(pad).width(game.WIDTH/1.5f).height(game.HEIGHT/4);
        table.row();
        table.add(clickZone).pad(pad).width(game.WIDTH/1.5f).height(game.HEIGHT/6);
        table.row();
        table.add(navigation).pad(pad).width(game.WIDTH/1.5f).height(game.HEIGHT/10);
        table.align(Align.center);


    }

    private void initLabels(){
        Label.LabelStyle statsLabelStyle = new Label.LabelStyle();
        Label.LabelStyle statsLabelStyle1 = new Label.LabelStyle();
        Label.LabelStyle statsLabelStyle2 = new Label.LabelStyle();
        Label.LabelStyle statsLabelStyle3 = new Label.LabelStyle();
        statsLabelStyle.font = StylesUI.FONT;
        statsLabelStyle1.font = StylesUI.FONT;
        statsLabelStyle2.font = StylesUI.FONT;
        statsLabelStyle3.font = StylesUI.FONT;
        statsLabelStyle.fontColor = Color.BLACK;
        statsLabelStyle1.fontColor = Color.GOLD;
        statsLabelStyle2.fontColor = Color.SKY;
        statsLabelStyle3.fontColor = Color.VIOLET;
        curGold = new CustomLabel("Gold: " + game.gold, statsLabelStyle1);
        curDay = new CustomLabel("Day: "+game.day, statsLabelStyle);

        guildLvl = new CustomLabel("Guild LVL: " + game.guildLvl, labelStyle);

        guildStats = new CustomLabel("Army " + game.curArmy + "/" + game.maxArmy + " | " + "Power " + game.powerTouch + "tp", labelStyle);

        missons = new Missons(game.guildLvl);
        missionName = new CustomLabel(game.missionName, statsLabelStyle2);
        missionLvl = new CustomLabel("Mission lvl: " + game.missionLvl, labelStyle);
        missionHp = new CustomLabel("HP: " + curHP + "/" + game.missionHp, statsLabelStyle3);
        missionReward = new CustomLabel("Reward: " + missons.getReward().toString(), labelStyle);
    }

    private void initWindows(){
        guildInfo = new Window("Guild Info", windowStyle);
        guildInfo.setMovable(false);
        guildInfo.getTitleLabel().setAlignment(0);

        stats = new Window(game.guildName, windowStyle);
        stats.setMovable(false);
        stats.getTitleLabel().setAlignment(0);

        missionInfo = new Window("Mission", windowStyle);
        missionInfo.setMovable(false);
        missionInfo.getTitleLabel().setAlignment(0);

        clickZone = new Window("Click Zone", windowStyle);
        clickZone.setMovable(false);
        clickZone.getTitleLabel().setAlignment(0);

        navigation = new Window("Navigation", windowStyle);
        navigation.setMovable(false);
        navigation.getTitleLabel().setAlignment(0);
    }

    private void initWindowsActors(){
        stats.add(curGold).pad(5).expand();
        stats.add(curDay).pad(5).expand();
        guildInfo.add(guildLvl).pad(5).row();
        guildInfo.add(guildStats).pad(5);

        missionInfo.add(missionName).colspan(2).pad(10).row();
        missionInfo.add(missionLvl).pad(10);
        missionInfo.add(missionReward).pad(10).row();
        missionInfo.add(missionHp).colspan(2).pad(10);
        missionInfo.row();
        missionInfo.add(barHp).height(100).center();

        navigation.add(home).pad(pad);
        navigation.add(upgrades).pad(pad);

    }

    private void initImageButtons(){
        imageButtonStyle = StylesUI.imageButtonStyle;


        imageButtonStyle.down = skin.getDrawable("button-pressed");
        imageButtonStyle.up = skin.getDrawable("button");



        iconHome = new Texture("icons/house128.png");


        trdHome = new TextureRegionDrawable(iconHome);


        imageButtonStyle.imageDown = new TextureRegionDrawable(trdHome);
        imageButtonStyle.imageUp = new TextureRegionDrawable(trdHome);



        home = new ImageButton(imageButtonStyle);

    }
    private void initHomeImageButton(){
        imageButtonStyle2 = StylesUI.imageButtonStyle;

        imageButtonStyle2.up = skin.getDrawable("button");
        imageButtonStyle2.down = skin.getDrawable("button-pressed");

        iconUpgrade = new Texture("icons/pencil-edit-button.png");
        trdUpgrade = new TextureRegionDrawable(iconUpgrade);

        imageButtonStyle2.imageDown = new TextureRegionDrawable(trdUpgrade);
        imageButtonStyle2.imageUp = new TextureRegionDrawable(trdUpgrade);

        upgrades = new ImageButton(imageButtonStyle2);
    }

    private void createCamera(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
        camera.update(); // обновление камеры
    }

    private void clickZoneListener(){
        clickZone.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                curHP -= game.powerTouch;
                missionHp.updateText("HP: " + curHP + "/" + game.missionHp);
                System.out.println(curHP);
                if (curHP <= 0){
                    game.gold += game.missionReward;
                    game.newMisson();
                    initIntegers();
                    onLvlUp();
                    missionHp.updateText("HP: " + curHP + "/" + game.missionHp);
                    game.plusMission();
                    textUpdate();
                    newGuildLvl();
                }

                //barHp.setAnimateDuration(2);
                //missionHp.setText(0); if (curHP < 1){
                //                    game.newMisson();
                //                    initIntegers();
                //                    onLvlUp();
                //                    //curHP++;
                //                    game.plusMission();
                //                    textUpdate();
                //
                //                }
                //
                //                if (curHP == 0){
                //                    onLvlUp();
                //                }

                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void progressBarListener(){

    }

    private void onLvlUp(){
        missionName.updateText(game.missionName);
        missionReward.updateText("Reward: " + missons.getReward().toString());
        missionLvl.updateText("Mission lvl: " + game.missionLvl);
        missionName.updateText(game.missionName);
    }

    private void textUpdate(){
        curDay.updateText("Day: " + game.day);
        curGold.updateText("Gold: " + game.gold);
    }

    private void newGuildLvl(){
        int lvl = (int)(game.guildLvl / 3);
        guildLvl.updateText("Guild LVL: " + lvl);
    }

    private void newMission(){ // не рабочие, костыли сила
        if (curHP < 0){
            game.newMisson();
            initIntegers();
        }
    }

    private void initIntegers(){
        curHP = game.missionHp;
    }

    private void buttonListener(){
        System.out.println("btnlistener - on");
    }

    @Override
    public void show() {
        init();

        home.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        upgrades.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new UpgradesScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void render(float delta) {
        update(delta);
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.end();
    }

    private void update(float delta){
        Gdx.gl.glClearColor(0, 1, 1, 0);
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
