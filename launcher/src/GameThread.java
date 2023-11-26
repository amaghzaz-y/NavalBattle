
public class GameThread extends Thread {
	static String[] args = new String[2];

	public GameThread(String username, String session) {
		args[0] = username;
		args[1] = session;
	}

	@Override
	public void run() {
		try {
			var game = new DesktopLauncher();
			game.main(args);
			// handleInput();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
