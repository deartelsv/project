package com.myownguild.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.myownguild.game.Main;
import com.myownguild.game.StylesUI;
import com.myownguild.game.UI.CustomLabel;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class UpgradesScreen implements Screen {

    protected Main game; // protected, как я понял, позволяет в дочерних классах юзать себя
    protected Stage stage;  // каждый screen имеет свой stage
    protected OrthographicCamera camera; //камера
    protected SpriteBatch batch; // рисовалка

    private Window stats;
    private Window upgrades;
    private Window navigation;

    private CustomLabel curGold;
    private CustomLabel curDay;
    // ui
    private Label.LabelStyle labelStyle;
    private Window.WindowStyle windowStyle;
    private BitmapFont font = StylesUI.FONT;
    private Skin skin = StylesUI.skin;
    private TextButton.TextButtonStyle textButtonStyle;
    //tables
    private Table table;

    private int pad = 15;
    // dasd

    //upgrades
    private TextButton upgradeButton1;
    private TextButton upgradeButton2;
    private TextButton upgradeButton3;
    private TextButton upgradeButton4;

    private CustomLabel Label1;
    private CustomLabel Label2;
    private CustomLabel Label3;
    private CustomLabel Label4;

    private CustomLabel upgradeCost1;
    private CustomLabel upgradeCost2;
    private CustomLabel upgradeCost3;
    private CustomLabel upgradeCost4;

    private CustomLabel upgradeLvl1;
    private CustomLabel upgradeLvl2;
    private CustomLabel upgradeLvl3;
    private CustomLabel upgradeLvl4;

    //

    // bar
    private Texture iconHome;
    private Texture iconUpgrade;
    private TextureRegionDrawable trdHome;
    private TextureRegionDrawable trdUpgrade;

    private TextButton home;
    private TextButton upgrades1;
    private Texture bg;

    public UpgradesScreen(Main game) {
        this.game = game;
        createCamera();
        stage = new Stage(new StretchViewport(Main.WIDTH, Main.HEIGHT, camera)); // скретч - обрезает, фит - сохраняеет соотношения
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage); // загружаем stage (???)
        init();// проводим инцилизацию нужных компонентов
    }


    private void init() {
        Styles();

        initButtons();
        initTextButton();
        initLabels();
        initLabel();
        buttonListener();
        initWindows();
        initWindowsActors();
        //
        table = new Table();
        bg = new Texture("textures/background.png");
        table.setBackground((new TextureRegionDrawable(bg)));
        table.setSize(game.WIDTH, game.HEIGHT);
        table.setDebug(false);
        //
        initTable();




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
        labelStyle.background = skin.getDrawable("textfield-pressed");
        //button
        skin = StylesUI.skin;
        skin.addRegions(StylesUI.ATLAS);
        textButtonStyle = StylesUI.textButtonStyle;
        textButtonStyle.font = StylesUI.FONT;
        textButtonStyle.down = skin.getDrawable("button-pressed");
        textButtonStyle.up = skin.getDrawable("button");
        //Label textfield

    }

    private void initWindows() {
        stats = new Window(game.guildName, windowStyle);
        stats.setMovable(false);
        stats.getTitleLabel().setAlignment(0);

        upgrades = new Window("Upgrades", windowStyle);
        upgrades.setMovable(false);
        upgrades.getTitleLabel().setAlignment(0);

        navigation = new Window("Navigation", windowStyle);
        navigation.setMovable(false);
        navigation.getTitleLabel().setAlignment(0);
    }

    private void initTable(){
        table.add(stats).pad(pad).width(game.WIDTH/1.5f).height(game.HEIGHT/10);
        table.row();
        table.add(upgrades).pad(pad).width(game.WIDTH/1.5f).height(game.HEIGHT/2);
        table.row();
        table.add(navigation).pad(pad).width(game.WIDTH/1.5f).height(game.HEIGHT/10);

        table.align(Align.center);

    }


    private void initWindowsActors(){
        int x3Width = (int)(upgradeButton1.getWidth()+upgradeCost1.getWidth()+upgradeLvl1.getWidth() + 30);
        upgrades.add(upgradeButton1).pad(0, 0, 5 ,5).width(x3Width);
        upgrades.add(upgradeCost1).pad(0, 5, 5, 5).width(x3Width/2);
        upgrades.add(upgradeLvl1).width(x3Width/3).row();
        upgrades.add(Label1).colspan(3).width(x3Width*2).pad(0, 0, 5, 0);
        upgrades.row();
        upgrades.add(upgradeButton2).pad(0, 0, 5 ,5).width(x3Width);
        upgrades.add(upgradeCost2).pad(0, 5, 5, 5).width(x3Width/2);;
        upgrades.add(upgradeLvl2).width(x3Width/3).row();
        upgrades.add(Label2).colspan(3).width(x3Width*2).pad(0, 0, 5, 0);
        upgrades.row();
        upgrades.add(upgradeButton3).pad(0, 0, 5 ,5).width(x3Width);
        upgrades.add(upgradeCost3).pad(0, 5, 5, 5).width(x3Width/2);;
        upgrades.add(upgradeLvl3).width(x3Width/3).row();
        upgrades.add(Label3).colspan(3).width(x3Width*2).pad(0, 0, 5, 0);
        upgrades.row();
        upgrades.add(upgradeButton4).pad(0, 0, 5 ,5).width(x3Width);
        upgrades.add(upgradeCost4).pad(0, 5, 5, 5).width(x3Width/2);
        upgrades.add(upgradeLvl4).width(x3Width/3).row();
        upgrades.add(Label4).colspan(3).width(x3Width*2).pad(0, 0, 5, 0);

        stats.add(curGold).pad(5).expand();
        stats.add(curDay).pad(5).expand();

        navigation.add(home).pad(pad);
        navigation.add(upgrades1).pad(pad);
    }

    private void initButtons(){
        upgradeButton1 = new TextButton("BUY", textButtonStyle);
        upgradeButton2 = new TextButton("BUY", textButtonStyle);
        upgradeButton3 = new TextButton("BUY", textButtonStyle);
        upgradeButton4 = new TextButton("BUY", textButtonStyle);

    }

    private void initLabel(){
        Label1 = new CustomLabel("+click damage", labelStyle);
        Label2 = new CustomLabel("+army", labelStyle);
        Label3 = new CustomLabel("multiclick", labelStyle);
        Label4 = new CustomLabel("army attack speed", labelStyle);

        upgradeCost1 = new CustomLabel(""+(game.upgrade1 * 5 + 10), labelStyle);
        upgradeCost2 = new CustomLabel(""+(game.upgrade2 * 5 + 10), labelStyle);
        upgradeCost3 = new CustomLabel(""+(game.upgrade3 * 50 + 100), labelStyle);
        upgradeCost4 = new CustomLabel(""+(game.upgrade4 * 50 + 100), labelStyle);


        upgradeLvl1 = new CustomLabel(game.upgrade1.toString(), labelStyle);
        upgradeLvl2 = new CustomLabel(game.upgrade2.toString(), labelStyle);
        upgradeLvl3 = new CustomLabel(game.upgrade3.toString(), labelStyle);
        upgradeLvl4 = new CustomLabel(game.upgrade4.toString(), labelStyle);


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
        curDay = new CustomLabel("Day: " + game.day, statsLabelStyle);
    }

    private void buttonListener(){
        upgradeButton1.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (game.gold >= game.upgrade1 * 5 + 10){
                    game.gold -= game.upgrade1 * 5 + 10;
                    game.upgrade1++;
                    System.out.println(game.upgrade1);
                    curGold.updateText("Gold: " + game.gold);
                    upgradeLvl1.updateText(game.upgrade1.toString());
                    upgradeCost1.updateText(((Integer)(game.upgrade1 * 5 + 10)).toString());
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        upgradeButton2.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if ((game.upgrade2*2+1) < (game.guildLvl*4)){
                    if (game.gold >= game.upgrade2 * 5 + 10) {
                    game.gold -= game.upgrade2 * 5 + 10;
                    game.upgrade2++;
                    curGold.updateText("Gold: " + game.gold);
                    upgradeLvl2.updateText(game.upgrade2.toString());
                    upgradeCost2.updateText(((Integer) (game.upgrade2 * 5 + 10)).toString());
                    }
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        upgradeButton3.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (game.gold >= game.upgrade3 * 50 + 100){
                    game.gold -= game.upgrade3 * 50 + 100;
                    game.upgrade3++;
                    game.multiclick++;
                    curGold.updateText("Gold: " + game.gold);
                    upgradeLvl3.updateText(game.upgrade3.toString());
                    upgradeCost3.updateText(((Integer)(game.upgrade3 * 50 + 100)).toString());
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        upgradeButton4.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (game.gold >= game.upgrade4 * 50 + 100){
                    game.gold -= game.upgrade4 * 50 + 100;
                    game.upgrade4++;
                    game.attackSpeed = game.attackSpeed - 100;
                    curGold.updateText("Gold: " + game.gold);
                    upgradeLvl4.updateText(game.upgrade4.toString());
                    upgradeCost4.updateText(((Integer)(game.upgrade4 * 50 + 100)).toString());
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        home.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        upgrades1.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("upgrades1");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void initTextButton(){
        home = new TextButton("<- GAME", textButtonStyle);
        upgrades1 = new TextButton("SKLAD ->", textButtonStyle);
        upgrades1.setDisabled(true);
    }

    private void createCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
        camera.update(); // обновление камеры
    }

    @Override
    public void show() {

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
