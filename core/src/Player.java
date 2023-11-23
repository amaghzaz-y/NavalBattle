
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player extends PlayerBase {
	private Array<Ship> ships;
	private int score = 0;
	private boolean turn = true;
	private boolean ready = false;
	private ShapeRenderer renderer;

	Player(String name) {
		super(name);
		ships = new Array<>();
	}

	public void setName(String username) {
		setPlayerName(username);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int newScore) {
		score = newScore;
	}

	public void onTouchDown(Vector2 mouse, int type) {
		for (Ship ship : ships) {
			ship.onTouchDown(mouse, type);
		}
	}

	public int HitsUpdate() {
		int hits = 0;
		for (Ship ship : ships) {
			hits += ship.touches();
		}
		return hits;
	}

	public boolean nextTurn() {
		turn = !turn;
		return turn;
	}

	public boolean getTurn() {
		return turn;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean state) {
		ready = state;
	}

	public payloads.Player serialize() {
		Array<payloads.Ship> shipobjs = new Array<>();
		for (Ship ship : ships) {
			shipobjs.add(ship.serialize());
		}
		var payload = new payloads.Player(getPlayerName(), shipobjs);
		return payload;
	}

	public void setRenderer(ShapeRenderer renderer) {
		this.renderer = renderer;

	}

	public void addShip(Ship ship) {
		ships.add(ship);
	}

	public Array<Ship> Ships() {
		return ships;
	}

	public void draw(Batch batch) {
		for (Ship ship : ships) {
			ship.draw(batch);
		}
	}

	public void render() {
		for (Ship ship : ships) {
			ship.addShapeRenderer(renderer);
			ship.render();
			ship.renderBounds();
		}
	}
}
