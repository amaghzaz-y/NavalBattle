package payloads;

import com.badlogic.gdx.utils.Array;

public class Session {
	public String ID;
	public String PIN;
	public Array<Player> players = new Array<>();

	public Session() {
	}

	public Session(Player player, Player opp) {
		players.add(player);
		players.add(opp);
	}
}