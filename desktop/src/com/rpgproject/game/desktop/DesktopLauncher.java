package com.rpgproject.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rpgproject.game.rpgProject;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "ProjectRPG";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new rpgProject(), config);
	}
}
