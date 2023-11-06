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
	}

	@Override
	public void render() {
		batch.begin();
		ScreenUtils.clear(0, 0, 1, 1);
		sea.draw(batch);
		for (int i = 0; i < ships.size; i++) {
			ships.get(i).draw(batch);
		}
		batch.end();
		bounds.draw(shapeRenderer);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
