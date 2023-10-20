package com.neonnebula.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.neonnebula.game.NeonNebulaApplication;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
        config.setWindowedMode(1024, 1024);
		config.setTitle("neon-nebula");
		new Lwjgl3Application(new NeonNebulaApplication(), config);
	}
}
