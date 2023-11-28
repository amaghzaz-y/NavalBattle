import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;

import com.badlogic.gdx.utils.Json;

import payloads.Generic;
import payloads.Message;
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
	public HashMap<String, HashSet<Message>> Messages;
	public int count = 0;

	public Handler() {
		Users = new HashMap<>();
		Sessions = new HashMap<>();
		ReadySessions = new HashSet<>();
		TurnPlayers = new HashSet<>();
		Missiles = new HashMap<>();
		Messages = new HashMap<>();
		json = new Json();
		json.setIgnoreUnknownFields(true);
	}

	public void Handle(String payload, PrintWriter writer) {
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
			// Message Handler
			case 5:
				var message = json.fromJson(Message.class, payload);
				MessageHandler(message, writer);
				break;
			// Bad Request
			default:
				sendStatus(writer, new Status(2));
				break;
		}
	}

	private void MessageHandler(Message request, PrintWriter writer) {
		// ignores if its not the right session
		if (!Sessions.containsKey(request.session)) {
			System.out.println("incorrect session");
			sendStatus(writer, new Status(2));
			return;
		}
		var messages = Messages.get(request.session);
		messages.add(request);
		Messages.put(request.session, messages);
		sendStatus(writer, new Status(1));
	}

	// receives request - sends status codes
	private void MissileHandler(Missile request, PrintWriter writer) {
		// ignores if its not the right session
		if (!Sessions.containsKey(request.session)) {
			System.out.println("player: " + request.player + "incorrect session -> ignore missile");
			sendStatus(writer, new Status(2));
			return;
		}

		// tell player to wait
		if (!TurnPlayers.contains(request.player)) {
			System.out.println("player: " + request.player + " wait");
			sendStatus(writer, new Status(3));
			return;
		}

		// assign new turn
		System.out.println("turn: remove: " + request.player);
		TurnPlayers.remove(request.player);
		System.out.println("turn: add: " + request.opponent);
		TurnPlayers.add(request.opponent);

		// update missile
		Missiles.put(request.session, request);
		System.out.println("player: " + request.player + " missile accepted");
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
				var current = Sessions.get(request.session);
				var player = current.player.username;
				var opponent = current.opponent.username;
				// sender has a turn
				if (TurnPlayers.contains(request.sender)) {
					System.out.println("turn: ok " + request.sender);
					sendStatus(writer, new Status(1));
				} else
				// no one has a turn
				if (!TurnPlayers.contains(player) && !TurnPlayers.contains(opponent)) {
					System.out.println("turn: giving perm to " + player);
					TurnPlayers.add(player);
					if (request.sender.matches(player)) {
						sendStatus(writer, new Status(1));
					} else {
						sendStatus(writer, new Status(3));
					}
				} else
				// Not having a turn
				{
					System.out.println("turn: no " + request.sender);
					sendStatus(writer, new Status(3));
				}

				// ScoreBoard
			case 7:
				return;
			// Chat
			case 8:
				if (!Messages.containsKey(request.session)) {
					sendStatus(writer, new Status(2));
				} else {
					var messages = Messages.get(request.session);
					sendStatus(writer, new Status(1));
					sendMessages(writer, messages);
				}
				return;
			case 9:

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
			Messages.put(request.session, new HashSet<>());
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

	private void sendMessages(PrintWriter writer, HashSet<Message> messages) {
		var pm = new payloads.Messages();
		pm.messages = messages;
		var paylaod = json.toJson(pm);
		writer.println(paylaod);
		writer.flush();
	}

	private void sendAllSessions(PrintWriter writer, HashSet<Session> sessions) {
		var s = new payloads.Sessions();
		s.sessions = sessions;
		var paylaod = json.toJson(s);
		writer.println(paylaod);
		writer.flush();
	}
}