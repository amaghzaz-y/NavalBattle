import java.io.PrintWriter;
import java.util.HashMap;
import com.badlogic.gdx.utils.Json;

import payloads.Player;
import payloads.Session;

public class Handler {
	private Json json = new Json();
	private HashMap<String, Session> sessions;
	public static HashMap<String, PrintWriter> users;

	public Handler() {
		users = new HashMap<>();
		sessions = new HashMap<>();
	}

	public void AddUser(String username, PrintWriter writer) {
		users.put(username, writer);
	}

	private void handleReadyProcess(Session session) {
		if (session.isReady())
			return;
		for (Player player : session.getPlayers()) {
			if (player.isReady())
				continue;
		}
	}

	public void HandleRequest(String payload) {
		var session = getSession(payload);
		sessions.put(session.getID(), session);
	}

	public Session getSession(String payload) {
		return json.fromJson(Session.class, payload);
	}
}
