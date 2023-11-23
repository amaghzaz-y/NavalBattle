package payloads;

import com.badlogic.gdx.utils.Array;

public class Player {
	public String username;
	public Array<Ship> ships;
	public int score = 0;

	public Player() {
	}

	public Player(String username, Array<Ship> ships) {
		this.username = username;
		this.ships = ships;
	}
}