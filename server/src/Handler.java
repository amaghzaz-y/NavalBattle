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

	public void HandleRequest(String username, String payload) {
		var session = getSession(payload);
		if (!sessions.containsKey(session.getID()))
			sessions.put(session.getID(), session);
		signUser(username, session);
		handleReadyProcess(session);
	}

	public void signUser(String username, Session ctx) {
		// user is already signed
		if (sessions.get(ctx.getID()).getPlayers().containsKey(username))
			return;
		var cs = sessions.get(ctx.getID());
		cs.updatePlayer(ctx.getPlayers().get(username));
		sessions.put(ctx.getID(), cs);
	}

	public void adddUserWriter(String username, PrintWriter writer) {
		users.put(username, writer);
	}

	private void handleReadyProcess(Session ctx) {
		// checks and ignores if both players in session are already ready
		if (ctx.isReady() || sessions.get(ctx.getID()).isReady())
			return;
		var cs = sessions.get(ctx.getID());
		// update the local map if playres are ready
		for (Player player : ctx.getPlayers().values()) {
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
