
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class Player extends PlayerBase {
	private Array<Ship> ships;

	Player(String name) {
		super(name);
		ships = new Array<>();
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

	public void drawShips() {

	}

	public Array<Ship> Ships() {
		return ships;
	}
}
