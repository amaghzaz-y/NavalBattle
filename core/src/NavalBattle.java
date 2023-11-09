import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class NavalBattle extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Array<Ship> ships = new Array<>();
	Sea sea;
	Bounds bounds;
	ShapeRenderer shapeRenderer;
	// MainScene scene;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);
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
		// scene = new MainScene();
		// scene.create();
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
		// for (Ship ship : ships) {
		// ship.set(false);
		// }
		// scene.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		System.out.println(screenX + ":" + screenY + ":" + pointer + ":" + button);
		for (Ship ship : ships) {
			ship.handleClick(new Vector2(screenX, screenY));
		}
		return true;
	}

	@Override
	public boolean keyDown(int keycode) {
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
