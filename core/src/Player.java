
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player extends PlayerBase {
	private Array<Ship> ships;

	Player(String name) {
		super(name);
		ships = new Array<>();
	}

	public void handleMouseClick(Vector2 mouse) {
		for (Ship ship : ships) {
			ship.handleClick(mouse);
		}
		HitsUpdate();
	}

	public int HitsUpdate() {
		int hits = 0;
		for (Ship ship : ships) {
			hits += ship.touches();
		}
		System.out.println(getPlayerName() + ":" + hits);
		return hits;
	}

	public void setRenderer(ShapeRenderer renderer) {
		for (Ship ship : ships) {
			ship.addShapeRenderer(renderer);
		}
	}

	public void drawShips(Batch batch) {
		for (Ship ship : ships) {
			ship.draw(batch);
		}
	}

	public void renderShips() {
		for (Ship ship : ships) {
			ship.render();
			// ship.renderBounds();
		}
	}

	public void addShip(Ship ship) {
		ships.add(ship);
	}

	public Array<Ship> Ships() {
		return ships;
	}
}
