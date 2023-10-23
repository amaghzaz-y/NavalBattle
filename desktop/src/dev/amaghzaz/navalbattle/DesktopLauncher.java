package dev.amaghzaz.navalbattle;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import dev.amaghzaz.navalbattle.NavalBattle;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] args) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(960, 540);
		config.setTitle("Naval Battle 2D - By AMAGHZAZ and LEON");
		new Lwjgl3Application(new NavalBattle(), config);
	}
}
