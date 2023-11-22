package payloads;

import java.util.HashMap;

public class Session {
	private String ID;
	private String sender;
	private HashMap<String, Player> players;
	private boolean ready = false;

	public Session() {
	}

	public Session(Player player, Player opp) {
		players = new HashMap<>();
		players.put(player.getUsername(), player);
		players.put(player.getUsername(), player);
	}

	public void setSender(String username) {
		sender = username;
	}

	public String getSender() {
		return sender;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getID() {
		return ID;
	}

	public HashMap<String, Player> getPlayers() {
		return players;
	}

	public void updatePlayer(Player player) {
		players.put(player.getUsername(), player);
	}

	public void setReady(boolean state) {
		ready = true;
	}

	public boolean isReady() {
		return ready;
	}
}