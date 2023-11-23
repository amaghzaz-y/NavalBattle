package payloads;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Ship {
	private int x;
	private int y;
	private int direction;
	private int type;
	private int size;
	private Array<Vector2> hits;

	public Ship() {

	}

	public Ship(int x, int y, int direction, int size, int type, Array<Vector2> hits) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.size = size;
		this.type = type;
		this.hits = hits;
	}

	public int getType() {
		return type;
	}

	public Array<Vector2> getHits() {
		return hits;
	}

	public int getSize() {
		return size;
	}

	public int getDirection() {
		return direction;
	}

	public Vector2 getPosition() {
		return new Vector2(x, y);
	}
}