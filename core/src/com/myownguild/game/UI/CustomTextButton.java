package com.myownguild.game.UI;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.myownguild.game.StylesUI;

public class CustomTextButton extends TextButton {

    private Skin skin;
    private TextButton.TextButtonStyle textButtonStyle;

    public CustomTextButton(String text, Skin skin) {
        super(text, skin);
        init();
    }


    @Override
    public void setStyle(ButtonStyle style) {
        super.setStyle(style);
    }

    private void init() {
        skin = StylesUI.skin;
        skin.addRegions(StylesUI.ATLAS);
        textButtonStyle = StylesUI.textButtonStyle;

        textButtonStyle.down = skin.getDrawable("button-pressed");
        textButtonStyle.up = skin.getDrawable("button");
    }
}
