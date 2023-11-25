import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;

import com.badlogic.gdx.utils.Json;

import payloads.Generic;
import payloads.Missile;
import payloads.Session;
import payloads.Status;

public class Handler {
	public Json json;
	public HashMap<String, Session> Sessions; // (sessionID, Session)
	public HashMap<String, PrintWriter> Users; // (usernames, writer)
	public HashSet<String> ReadySessions; // sessionsID
	public HashSet<String> TurnPlayers; // usernames
	public HashMap<String, Missile> Missiles; // (sessionID, missile)
	public int count = 0;

	public Handler() {
		Users = new HashMap<>();
		Sessions = new HashMap<>();
		ReadySessions = new HashSet<>();
		TurnPlayers = new HashSet<>();
		Missiles = new HashMap<>();
		json = new Json();
		json.setIgnoreUnknownFields(true);
	}

	public void Handle(String payload, PrintWriter writer) {
		// System.out.println(payload);
		var generic = json.fromJson(Generic.class, payload);
		System.out.println("REQ:" + count++ + " TYPE:" + generic.type + " SIZE:" + payload.length());
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
				MissileHandler(missile, writer);
				break;
			// Score Handler
			case 4:
				// var score = json.fromJson(Score.class, payload);s
				break;
			// Bad Request
			default:
				sendStatus(writer, new Status(2));
				break;
		}
	}

	// receives request - sends status codes
	private void MissileHandler(Missile request, PrintWriter writer) {
		// ignores if its not the player turn to play
		if (!Sessions.containsKey(request.session)) {
			System.out.println("player: " + request.player + " ignore missile");
			sendStatus(writer, new Status(2));
			return;
		}
		if (!TurnPlayers.contains(request.player) && TurnPlayers.contains(request.opponent)) {
			System.out.println("player: " + request.player + " wait");
			sendStatus(writer, new Status(3));
			return;
		}
		// assign new turn
		TurnPlayers.remove(request.player);
		TurnPlayers.add(request.opponent);
		// update missile
		Missiles.put(request.session, request);
		System.out.println("player: " + request.player + "missile accepted");
		// sending corresponding requests
		// var res = request;
		// res.opponent = request.player;
		// res.player = request.opponent;
		sendStatus(writer, new Status(1));
	}

	private void StatusHandler(Status request, PrintWriter writer) {
		switch (request.code) {
			// Session
			case 4:
				if (ReadySessions.contains(request.session)) {
					sendStatus(writer, new Status(1));
					var s = Sessions.get(request.session);
					if (request.sender.matches(s.player.username)) {
						sendSession(writer, s);
					} else {
						// reverse roles
						Session rs = new Session(s.opponent, s.player);
						rs.type = 2;
						rs.session = s.session;
						sendSession(writer, rs);
					}
					return;
				}
				sendStatus(writer, new Status(3));
				return;
			// Missile
			case 5:
				// sender is able to receive missile attacks
				if (TurnPlayers.contains(request.sender)) {
					if (Missiles.containsKey(request.session)) {
						var missile = Missiles.get(request.session);
						Missiles.remove(request.session);
						sendStatus(writer, new Status(1));
						sendMissile(request.sender, missile);
						return;
					}
					// tell the player that there is a sync error
					sendStatus(writer, new Status(2));
					return;
				}
				// tell the player to wait for udpate
				sendStatus(writer, new Status(3));
				return;
			// Turn
			case 6:
				if (TurnPlayers.contains(request.sender)) {
					sendStatus(writer, new Status(1));
				} else {
					var current = Sessions.get(request.session);
					// gives turn perm if no one has the privilege
					if (!TurnPlayers.contains(current.opponent.username)) {
						TurnPlayers.add(request.sender);
						sendStatus(writer, new Status(1));
						return;
					}
					sendStatus(writer, new Status(3));
				}
				return;
			// Score
			case 7:
				return;
			// ScoreBoard
			case 8:
				return;
		}
	}

	// receives request / sends status codes
	private void SessionHandler(Session request, PrintWriter writer) {
		if (!Sessions.containsKey(request.session)) {
			Sessions.put(request.session, request);
			System.out.println("session: " + request.session + " set up");
		}
		var current = Sessions.get(request.session);
		var username = request.player.username;
		// registered as player or opponent
		if (username.matches(current.player.username) || username.matches(current.opponent.username)) {
			Users.put(request.player.username, writer);
			System.out.println("player: " + request.player.username + " set up");
			sendStatus(writer, new Status(1));
			return;
		}
		// neither player nor opponent
		if (!username.matches(current.player.username) && !username.matches(current.opponent.username)) {
			// ignores if player is a third player
			if (Users.containsKey(current.opponent.username)) {
				System.out.println("player: " + request.player.username + " cannot join session");
				sendStatus(writer, new Status(2));
				return;
			}
			current.opponent = request.player;
			Sessions.put(current.session, current);
			ReadySessions.add(current.session);
			Users.put(request.player.username, writer);
			System.out.println("player: " + request.player.username + " set up");
			sendStatus(writer, new Status(1));
			return;
		}
		sendStatus(writer, new Status(2));
	}

	private void sendMissile(String username, Missile missile) {
		var printer = Users.get(username);
		printer.println(json.toJson(missile));
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
}
