import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

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
		// TODO!
		// NOT FULLY IMPLEMENTED

		if (!players.containsKey(playerName)) {
			return false;
		}
		var player = players.get(playerName);
		player.addShip(ship);
		players.put(playerName, player);
		return true;
	}

	public boolean isPositionAvailable(ShipBase ship) {
		// TODO!
		// NOT FULLY IMPLEMENTED
		players.values().forEach(player -> {
			player.getShips().forEach(playerShip -> {
				var pos = playerShip.getPosition();
				// TODO!
			});
		});
		return false;
	}
}
