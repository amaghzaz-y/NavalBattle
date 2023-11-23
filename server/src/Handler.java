import java.io.PrintWriter;
import java.util.HashMap;
import com.badlogic.gdx.utils.Json;

import payloads.Genereic;
import payloads.Missile;
import payloads.Score;
import payloads.Session;
import payloads.Status;

public class Handler {
	public Json json = new Json();
	// session ID, Session
	public HashMap<String, Session> Sessions;
	public HashMap<String, PrintWriter> Users;
	public int count = 0;

	public Handler() {
		Users = new HashMap<>();
		Sessions = new HashMap<>();
	}

	public void Handle(String payload, PrintWriter writer) {
		var generic = json.fromJson(Genereic.class, payload);
		switch (generic.type) {
			// Status Handler
			case 1:
				var status = json.fromJson(Status.class, payload);
				// TODO !
				return;
			// Session Handler
			case 2:
				var session = json.fromJson(Session.class, payload);
				SessionHandler(session);
				return;
			// Missile Handler
			case 3:
				var missile = json.fromJson(Missile.class, payload);
				// TODO !
				return;
			// Score Handler
			case 4:
				var score = json.fromJson(Score.class, payload);
				// TODO !
				return;

			// Bad Request
			default:
				break;
		}
	}

	private Status SessionHandler(Session ctx) {
		if (!Sessions.containsKey(ctx.ID))
			Sessions.put(ctx.ID, ctx);
		var current = Sessions.get(ctx.ID);
		var username = ctx.player.username;
		// registered as player or opponent
		if (username.matches(current.player.username) || username.matches(current.opponent.username))
			return new Status(1);
		// neither player nor opponent
		if (!username.matches(current.player.username) && !username.matches(current.opponent.username)) {
			// ignores if player is a third player
			if (Users.containsKey(current.opponent.username))
				return new Status(2);
			current.opponent = ctx.player;
			return new Status(1);
		}
		return new Status(2);

	}

	private void sendMessage(String username, String message) {
		var printer = Users.get(username);
		printer.println(message);
		printer.flush();
	}

	// sends message to all players in the same session
	private void broadcast(Session ctx, String message) {
		var s = Sessions.get(ctx.ID);
		sendMessage(s.player.username, message);
		sendMessage(s.opponent.username, message);
	}

	// get json from session
	private Session getSession(String payload) {
		return json.fromJson(Session.class, payload);
	}
}
