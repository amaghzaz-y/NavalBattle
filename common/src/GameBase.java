import java.util.HashMap;

public class GameBase {
	public int GameId;
	public static final int missileImpactArea = 20;
	public static final int boardWidth = 720;
	public static final int boardHeight = 720;
	public static final int cellSize = 20;
	public HashMap<String, PlayerBase> players;

	public GameBase(PlayerBase firstPlayer, PlayerBase secondPlayer) {
		players.put(firstPlayer.getPlayerName(), firstPlayer);
		players.put(secondPlayer.getPlayerName(), secondPlayer);
	}

	public boolean AddShipToPlayer(String playerName, ShipBase ship) {
		if (!players.containsKey(playerName))
			return false;
		var player = players.get(playerName);
		if (!isPositionAvailable(ship))
			return false;
		player.addShip(ship);
		players.put(playerName, player);
		return true;
	}

	public boolean isPositionAvailable(ShipBase ship) {
		for (PlayerBase player : players.values()) {
			for (ShipBase otherShip : player.getShips()) {
				if (ship.isCollision(otherShip)) {
					return false;
				}
			}
		}
		return true;
	}
}
