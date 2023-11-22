import java.io.PrintWriter;
import java.util.HashMap;
import com.badlogic.gdx.utils.Json;

import payloads.Player;
import payloads.Session;

public class Handler {
	public Json json = new Json();
	public HashMap<String, Session> sessions;
	public HashMap<String, PrintWriter> users;

	public Handler() {
		users = new HashMap<>();
		sessions = new HashMap<>();
	}

	public void HandleRequest(String payload, PrintWriter writer) {
		var ctx = getSession(payload);
		System.out.println(ctx);
		if (!users.containsKey(ctx.getSender())) {
			users.put(ctx.getSender(), writer);
			writer.println("registration successful !");
			writer.flush();
		}
		if (!sessions.containsKey(ctx.getID())) {
			sessions.put(ctx.getID(), ctx);
			writer.println("new session!");
			writer.flush();
		}
		signUser(ctx.getSender(), ctx);
		handleReadyProcess(ctx);
	}

	public void signUser(String username, Session ctx) {
		// user is already signed
		if (sessions.get(ctx.getID()).getPlayers().containsKey(username))
			return;
		var cs = sessions.get(ctx.getID());
		cs.updatePlayer(ctx.getPlayers().get(username));
		sessions.put(ctx.getID(), cs);
	}

	public void handleReadyProcess(Session ctx) {
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

	public Session getSession(String payload) {
		return json.fromJson(Session.class, payload);
	}
}
