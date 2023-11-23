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
		if (!users.containsKey(ctx.player.username))
			users.put(ctx.player.username, writer);

		// add session
		if (!sessions.containsKey(ctx.ID))
			sessions.put(ctx.ID, ctx);

		// reject if session is full
		if (!isValid(ctx)) {
			System.out.println("not valid");
			return;
		}

		// handleReadyProcess(ctx);
		var s = sessions.get(ctx.ID);
		// sendMessage(ctx.getSender(), json.toJson(s));
		System.out.println(json.toJson(ctx));
		System.out.println(json.toJson(s));
		broadcast(ctx, json.toJson(s));
	}

	// ignores 2+ players
	public boolean isValid(Session ctx) {
		var s = sessions.get(ctx.ID);
		if (s.player.username != ctx.player.username && s.opponent.username != ctx.player.username)
			return false;
		return true;
	}

	public void sendMessage(String username, String message) {
		var printer = users.get(username);
		printer.println(message);
		printer.flush();
	}

	// sends message to all players in the same session
	public void broadcast(Session ctx, String message) {
		var s = sessions.get(ctx.ID);
		sendMessage(s.player.username, message);
		sendMessage(s.opponent.username, message);
	}

	// get json from session
	public Session getSession(String payload) {
		return json.fromJson(Session.class, payload);
	}
}
