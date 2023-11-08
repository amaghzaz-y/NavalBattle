import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class NavalBattle extends ApplicationAdapter {
	SpriteBatch batch;
	Array<Ship> ships = new Array<>();
	Sea sea;
	Bounds bounds;
	ShapeRenderer shapeRenderer;
	MainScene scene;

	@Override
	public void create() {
		batch = new SpriteBatch();
		ships.add(new Ship(new Vector2(300, 50), ShipBase.Direction.Vertical, ShipBase.Type.VerySmall));
		ships.add(new Ship(new Vector2(400, 50), ShipBase.Direction.Vertical,
				ShipBase.Type.Small));
		ships.add(new Ship(new Vector2(300, 250), ShipBase.Direction.Horizontal, ShipBase.Type.Medium));

		ships.add(new Ship(new Vector2(400, 250), ShipBase.Direction.Vertical, ShipBase.Type.Big));
		bounds = new Bounds();
		sea = new Sea();
		shapeRenderer = new ShapeRenderer();
		bounds.addShapeRenderer(shapeRenderer);
		for (Ship ship : ships) {
			ship.addShapeRenderer(shapeRenderer);
		}
		scene = new MainScene();
		scene.create();

	}

	@Override
	public void render() {
		batch.begin();
		ScreenUtils.clear(0, 0, 1, 1);
		sea.draw(batch);
		for (Ship ship : ships) {
			ship.draw(batch);
		}
		batch.end();
		bounds.draw();
		for (Ship ship : ships) {
			ship.boundingBox(true);
		}
		scene.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
