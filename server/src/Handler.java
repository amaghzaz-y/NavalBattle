import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;

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
	public HashSet<String> ReadySessions;
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
				StatusHandler(status, writer);
				break;
			// Session Handler
			case 2:
				var session = json.fromJson(Session.class, payload);
				SessionHandler(session, writer);
				break;
			// Missile Handler
			case 3:
				var missile = json.fromJson(Missile.class, payload);
				break;
			// Score Handler
			case 4:
				var score = json.fromJson(Score.class, payload);
				break;
			// Bad Request
			default:
				break;
		}
	}

	private void StatusHandler(Status request, PrintWriter writer) {
		switch (request.code) {
			// Update
			case 4:
				if (ReadySessions.contains(request.ID)) {
					sendSession(writer, Sessions.get(request.ID));
					return;
				}
				sendStatus(writer, new Status(3));
				return;
			// Turn
			case 5:
				return;
			// Score
			case 6:
				return;
			// Scoreboard
			case 7:
				return;
			// Chat
			case 8:
				return;
		}
	}

	private void SessionHandler(Session request, PrintWriter writer) {
		if (!Sessions.containsKey(request.ID))
			Sessions.put(request.ID, request);
		var current = Sessions.get(request.ID);
		var username = request.player.username;
		// registered as player or opponent
		if (username.matches(current.player.username) || username.matches(current.opponent.username)) {
			sendStatus(writer, new Status(1));
			return;
		}
		// neither player nor opponent
		if (!username.matches(current.player.username) && !username.matches(current.opponent.username)) {
			// ignores if player is a third player
			if (Users.containsKey(current.opponent.username)) {
				sendStatus(writer, new Status(2));
				return;
			}
			current.opponent = request.player;
			Sessions.put(request.ID, current);
			ReadySessions.add(request.ID);
			sendStatus(writer, new Status(1));
			return;
		}
		sendStatus(writer, new Status(2));
		return;
	}

	private void sendToPlayer(String username, String message) {
		var printer = Users.get(username);
		printer.println(message);
		printer.flush();
	}

	private void sendSession(PrintWriter writer, Session session) {
		var paylaod = json.toJson(session);
		writer.println(paylaod);
		writer.flush();
	}

	private void sendStatus(PrintWriter writer, Status status) {
		var paylaod = json.toJson(status);
		writer.println(paylaod);
		writer.flush();
	}

	// sends message to all players in the same session
	private void broadcast(Session ctx, String message) {
		var s = Sessions.get(ctx.ID);
		sendToPlayer(s.player.username, message);
		sendToPlayer(s.opponent.username, message);
	}

	// get json from session
	private Session getSession(String payload) {
		return json.fromJson(Session.class, payload);
	}
}
