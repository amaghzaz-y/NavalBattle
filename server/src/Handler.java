import java.io.PrintWriter;
import java.util.HashMap;
import com.badlogic.gdx.utils.Json;

import payloads.Session;

public class Handler {
	public Json json = new Json();
	public HashMap<String, Session> sessions;
	public HashMap<String, PrintWriter> users;
	public int count = 0;

	public Handler() {
		users = new HashMap<>();
		sessions = new HashMap<>();
	}

	public void HandleRequest(String payload, PrintWriter writer) {
		System.out.println("req" + " : " + count++);
		var ctx = getSession(payload);
		// server rules !
		// add writer pipe
		if (!users.containsKey(ctx.getSender()))
			users.put(ctx.getSender(), writer);

		// add session
		if (!sessions.containsKey(ctx.getID()))
			sessions.put(ctx.getID(), ctx);

		// reject if session is full
		if (!isValid(ctx)) {
			System.out.println("not valid");
			return;
		}

		// handling the actual request is here !
		signUser(ctx);
		updateSender(ctx);
		updateSession(ctx);
		// handleReadyProcess(ctx);
		var s = sessions.get(ctx.getID());
		// sendMessage(ctx.getSender(), json.toJson(s));
		System.out.println(json.toJson(ctx));
		System.out.println(json.toJson(s));
		broadcast(ctx, json.toJson(s));
	}

	// ignores 2+ players
	public boolean isValid(Session ctx) {
		var s = sessions.get(ctx.getID());
		if (s.getPlayers().size() > 2)
			return false;
		return true;
	}

	public void updateSession(Session ctx) {
		var s = sessions.get(ctx.getID());
		for (var player : s.getPlayers().values()) {
			for (var ship : player.getShips()) {
				System.out.println(ship.getHits().size);
			}
			if (player.getUsername().matches(ctx.getSender()))
				continue;
			if (!ctx.getPlayers().containsKey(player.getUsername()))
				continue;
			var cp = ctx.getPlayers().get(player.getUsername());
			System.out.println(player.getUsername() + " : " + cp.getScore());
			// opponent
			s.updatePlayer(cp);
		}
		sessions.put(ctx.getID(), s);
	}

	// updates the head of the session
	public void updateSender(Session ctx) {
		var s = sessions.get(ctx.getID());
		s.setSender(ctx.getSender());
		sessions.put(ctx.getID(), s);
	}

	// add user to session with the same key
	public void signUser(Session ctx) {
		// user is already signed
		var sender = ctx.getSender();
		System.out.println("active: " + sessions.get(ctx.getID()).getPlayers().size());
		if (sessions.get(ctx.getID()).getPlayers().containsKey(sender)) {
			return;
		}
		var cs = sessions.get(ctx.getID());
		cs.updatePlayer(ctx.getPlayers().get(sender));
		sessions.put(ctx.getID(), cs);
	}

	public void sendMessage(String username, String message) {
		var printer = users.get(username);
		printer.println(message);
		printer.flush();
	}

	// sends message to all players in the same session
	public void broadcast(Session ctx, String message) {
		for (var user : sessions.get(ctx.getID()).getPlayers().keySet()) {
			System.out.println("to: " + user);
			sendMessage(user, message);
		}
	}

	// get json from session
	public Session getSession(String payload) {
		return json.fromJson(Session.class, payload);
	}
}
