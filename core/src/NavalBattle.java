import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class NavalBattle extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Sea sea;
	Bounds bounds;
	Gui gui;
	ShapeRenderer shapeRenderer;
	Player player1;
	Player player2;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		// 1
		player1 = new Player("ElonMusk");
		player1.addShip(new Ship(new Vector2(50, 200), ShipBase.Direction.Horizontal, ShipBase.Type.VerySmall));
		player1.addShip(new Ship(new Vector2(120, 400), ShipBase.Direction.Horizontal, ShipBase.Type.Small));
		player1.addShip(new Ship(new Vector2(200, 80), ShipBase.Direction.Vertical, ShipBase.Type.Medium));
		player1.addShip(new Ship(new Vector2(250, 300), ShipBase.Direction.Horizontal, ShipBase.Type.Big));
		// 2
		player2 = new Player("JeffBezos");
		player2.addShip(new Ship(new Vector2(360, 50), ShipBase.Direction.Vertical, ShipBase.Type.VerySmall));
		player2.addShip(new Ship(new Vector2(580, 120), ShipBase.Direction.Vertical, ShipBase.Type.Small));
		player2.addShip(new Ship(new Vector2(420, 250), ShipBase.Direction.Horizontal, ShipBase.Type.Medium));
		player2.addShip(new Ship(new Vector2(550, 340), ShipBase.Direction.Vertical, ShipBase.Type.Big));
		bounds = new Bounds();
		sea = new Sea();
		shapeRenderer = new ShapeRenderer();
		bounds.addShapeRenderer(shapeRenderer);
		player1.setRenderer(shapeRenderer);
		player2.setRenderer(shapeRenderer);
		gui = new Gui();
		gui.addShapeRenderer(shapeRenderer);
	}

	@Override
	public void render() {
		// for Sprite Batch : Textures
		batch.begin();
		ScreenUtils.clear(Color.SLATE);
		sea.draw(batch);
		player1.drawShips(batch);
		player2.drawShips(batch);
		gui.drawFont(batch);
		batch.end();
		// for ShapeRenderer
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setAutoShapeType(true);
		bounds.render();
		gui.render();
		player1.renderShips();
		player2.renderShips();
		shapeRenderer.end();
		// scene.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		var mouse = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
		player1.handleMouseClick(mouse);
		player2.handleMouseClick(mouse);
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
