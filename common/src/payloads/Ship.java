package payloads;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Ship {
	public int x;
	public int y;
	public int direction;
	public int size;
	public Array<Vector2> hits;

	public Ship(int x, int y, int direction, int size, Array<Vector2> hits) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.size = size;
		this.hits = hits;
	}
}