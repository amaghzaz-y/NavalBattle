
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player extends PlayerBase {
	private Array<Ship> ships;
	private int score;

	Player(String name) {
		super(name);
		ships = new Array<>();
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
		HitsUpdate();
	}

	public int HitsUpdate() {
		int hits = 0;
		for (Ship ship : ships) {
			hits += ship.touches();
		}
		return hits;
	}

	public void setRenderer(ShapeRenderer renderer) {
		for (Ship ship : ships) {
			ship.addShapeRenderer(renderer);
		}
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
			ship.render();
			ship.renderBounds();
		}
	}
}
