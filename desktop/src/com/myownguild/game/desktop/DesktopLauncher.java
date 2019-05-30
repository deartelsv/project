package com.myownguild.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myownguild.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = Main.GAME_NAME;
		config.width = Main.WIDTH;
		config.height = Main.HEIGHT;
		config.resizable = false; // forcing to not resizable

		new LwjglApplication(new Main(), config);
	}
}
