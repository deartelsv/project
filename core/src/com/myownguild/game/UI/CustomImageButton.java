package com.myownguild.game.UI;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.myownguild.game.StylesUI;

public class CustomImageButton extends ImageButton {

    private Skin skin;
    private ImageButton.ImageButtonStyle imageButtonStyle;
    private Drawable image;



    public CustomImageButton(Drawable imageUp) {
        super(imageUp);
        init();
    }

    @Override
    public void setStyle(ButtonStyle style) {
        super.setStyle(style);
    }

    private void init() {
        skin = StylesUI.skin;
        skin.addRegions(StylesUI.ATLAS);
        imageButtonStyle = StylesUI.imageButtonStyle;

        imageButtonStyle.down = skin.getDrawable("button-pressed");
        imageButtonStyle.up = skin.getDrawable("button");
        setStyle(imageButtonStyle);
    }
}
