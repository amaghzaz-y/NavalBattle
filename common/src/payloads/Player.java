package payloads;

import com.badlogic.gdx.utils.Array;

public class Player {
	private String username;
	private Array<Ship> ships;
	private int score = 0;

	public Player() {
	}

	public Player(String username, Array<Ship> ships) {
		this.username = username;
		this.ships = ships;
	}

	public Player(String username, Array<Ship> ships, int score) {
		this.username = username;
		this.ships = ships;
		this.score = score;
	}

	public Array<Ship> getShips() {
		return ships;
	}

	public int getScore() {
		return score;
	}

	public String getUsername() {
		return username;
	}

}