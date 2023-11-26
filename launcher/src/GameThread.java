
public class GameThread extends Thread {
	static String[] args = new String[4];

	public GameThread(String username, String session, String server, String port) {
		args[0] = username;
		args[1] = session;
		args[2] = server;
		args[3] = port;
	}

	@Override
	public void run() {
		try {
			DesktopLauncher.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
