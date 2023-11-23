package payloads;

public class Ship {
	public int x;
	public int y;
	public int direction;
	public int type;

	public Ship() {

	}

	public Ship(int x, int y, int direction, int type) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.type = type;
	}
}