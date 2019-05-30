package com.myownguild.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class StylesUI {

    public static final String UI_FILE = "skins/curSkin/skin-composer-ui.json";
   public final static TextureAtlas ATLAS = new TextureAtlas(Gdx.files.internal("skins/curSkin/skin-composer-ui.atlas"));
   public static Skin skin = new Skin();
   public final static Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
   public final static Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
   public final static ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
   public final static TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
   public final static BitmapFont FONT = new BitmapFont(Gdx.files.internal("fonts/default/font.fnt"));

   public StylesUI(){
       init();
   }

   private void init(){
       skin.addRegions(ATLAS);
    }
}
