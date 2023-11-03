import com.badlogic.gdx.utils.Array;

public class PlayerBase {
	private String playerName;
	private Array<ShipBase> ships;
	private int score;

	PlayerBase(String name) {
		this.playerName = name;
	}

	public void addShip(ShipBase ship) {
		ships.add(ship);
	}

	public final Array<ShipBase> getShips() {
		return ships;
	}

	public void setScore(int newScore) {
		score = newScore;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getScore() {
		return score;
	}
}
