package payloads;

import com.badlogic.gdx.utils.Array;

public class Player {
	public String username;
	public Array<Ship> ships;
	public int score;

	public Player(String username, Array<Ship> ships, int score) {
		this.username = username;
		this.ships = ships;
		this.score = score;
	}
}