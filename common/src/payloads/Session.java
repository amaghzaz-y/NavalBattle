package payloads;

import com.badlogic.gdx.utils.Array;

public class Session {
	private String ID;
	private String PIN;
	private Array<Player> players = new Array<>();
	private boolean ready = false;

	public Session() {
	}

	public Session(Player player, Player opp) {
		players.add(player);
		players.add(opp);
	}

	public String getID() {
		return ID;
	}

	public Array<Player> getPlayers() {
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