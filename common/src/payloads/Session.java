package payloads;

import java.util.Collection;
import java.util.HashMap;

public class Session {
	private String ID;
	private String PIN;
	private HashMap<String, Player> players;
	private boolean ready = false;

	public Session() {
	}

	public Session(Player player, Player opp) {
		players = new HashMap<>();
		players.put(player.getUsername(), player);
		players.put(player.getUsername(), player);
	}

	public String getID() {
		return ID;
	}

	public HashMap<String, Player> getPlayers() {
		return players;
	}

	public String getPIN() {
		return PIN;
	}

	public void setReady(boolean state) {
		ready = true;
	}

	public boolean isReady() {
		return ready;
	}
}