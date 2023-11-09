import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(30);
		config.setWindowedMode(960, 520);
		config.setTitle("Naval Battle 2D - By AMAGHZAZ and LEON");
		config.setResizable(false);
		new Lwjgl3Application(new NavalBattle(), config);
	}
}
