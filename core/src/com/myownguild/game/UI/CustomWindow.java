package com.myownguild.game.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.myownguild.game.StylesUI;

public class CustomWindow extends Window {

    private Skin skin;
    private Window.WindowStyle windowStyle;

    public CustomWindow(String title, Skin skin) {
        super(title, skin);
    }

    @Override
    public void setStyle(WindowStyle style) {
        super.setStyle(style);
    }

    private void init(){
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background = skin.getDrawable("window");
        windowStyle.titleFont = StylesUI.FONT;

        setStyle(windowStyle);
    }
}
