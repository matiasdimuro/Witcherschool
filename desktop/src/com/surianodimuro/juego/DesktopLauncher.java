package com.surianodimuro.juego;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	
	public static void main (String[] arg) {
		
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.useVsync(true);
		config.setForegroundFPS(60);
		config.setTitle("Escape de Witcherschool");
		config.setWindowedMode(1400, 800);
		config.setResizable(true);
		config.setWindowIcon("LOGO.png");
		
		new Lwjgl3Application(new Juego(), config);
	}
}
