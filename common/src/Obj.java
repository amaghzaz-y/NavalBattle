import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Obj {
	public static class ShipObj {
		public int x;
		public int y;
		public int direction;
		public int size;
		public Array<Vector2> hits;

		public ShipObj(int x, int y, int direction, int size, Array<Vector2> hits) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.size = size;
			this.hits = hits;
		}
	}

	public static class PlayerObj {
		public String username;
		public Array<ShipObj> ships;

		public PlayerObj(String username, Array<ShipObj> ships) {
			this.username = username;
			this.ships = ships;
		}
	}
}
