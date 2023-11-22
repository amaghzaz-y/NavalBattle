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

	public void HandleRequest(String payload) {
		var session = getSession(payload);
		sessions.put(session.getID(), session);
	}

	private void addUser(String username, PrintWriter writer) {
		users.put(username, writer);
	}

	private void handleReadyProcess(Session cSession) {
		// checks and ignores if both players in session are already ready
		if (cSession.isReady() || sessions.get(cSession.getID()).isReady())
			return;
		var cs = sessions.get(cSession.getID());
		// update the local map if playres are ready
		for (Player player : cSession.getPlayers().values()) {
			if (player.isReady()) {
				cs.getPlayers().put(player.getUsername(), player);
				cs.setReady(true);
				continue;
			}
			cs.setReady(false);
		}
		// overwrite the new update
		sessions.put(cs.getID(), cs);
	}

	private Session getSession(String payload) {
		return json.fromJson(Session.class, payload);
	}
}
