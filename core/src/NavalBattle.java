import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class NavalBattle extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Array<Ship> ships = new Array<>();
	Sea sea;
	Bounds bounds;
	Gui gui;
	ShapeRenderer shapeRenderer;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		// 1
		ships.add(new Ship(new Vector2(350, 50), ShipBase.Direction.Vertical, ShipBase.Type.VerySmall));
		ships.add(new Ship(new Vector2(500, 50), ShipBase.Direction.Vertical,
				ShipBase.Type.Small));
		ships.add(new Ship(new Vector2(350, 250), ShipBase.Direction.Horizontal, ShipBase.Type.Medium));
		ships.add(new Ship(new Vector2(550, 250), ShipBase.Direction.Vertical, ShipBase.Type.Big));
		// 2
		ships.add(new Ship(new Vector2(50, 200), ShipBase.Direction.Vertical, ShipBase.Type.VerySmall));
		ships.add(new Ship(new Vector2(150, 60), ShipBase.Direction.Horizontal,

				ShipBase.Type.Small));
		ships.add(new Ship(new Vector2(20, 20), ShipBase.Direction.Vertical, ShipBase.Type.Medium));
		ships.add(new Ship(new Vector2(150, 350), ShipBase.Direction.Horizontal, ShipBase.Type.Big));
		bounds = new Bounds();
		sea = new Sea();
		shapeRenderer = new ShapeRenderer();
		bounds.addShapeRenderer(shapeRenderer);
		for (Ship ship : ships) {
			ship.addShapeRenderer(shapeRenderer);
		}
		gui = new Gui();
		gui.addShapeRenderer(shapeRenderer);
		// scene = new MainScene();
		// scene.create();
	}

	@Override
	public void render() {
		// for Sprite Batch : Textures
		batch.begin();
		ScreenUtils.clear(Color.SLATE);
		sea.draw(batch);
		for (Ship ship : ships) {
			ship.draw(batch);
		}
		gui.drawFont(batch);
		batch.end();
		// for ShapeRenderer
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setAutoShapeType(true);
		bounds.render();
		gui.render();
		for (Ship ship : ships) {
			ship.render();
			ship.renderBounds();
		}
		shapeRenderer.end();
		// scene.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for (Ship ship : ships) {
			ship.handleClick(new Vector2(screenX, Gdx.graphics.getHeight() - screenY));
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
