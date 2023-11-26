import java.util.UUID;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(30);
		config.setWindowedMode(640, 520);
		config.setTitle("Naval Battle 2D - By AMAGHZAZ and LEON");
		config.setResizable(false);
		if (args.length > 0) {
			new Lwjgl3Application(new NavalBattle(args[0], args[1], args[2], args[3]), config);
		} else {
			new Lwjgl3Application(
					new NavalBattle(UUID.randomUUID().toString().substring(0, 3), "1234", "3", "#2"), config);
		}
	}
}
