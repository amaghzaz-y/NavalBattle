import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;

public class NavalBattle extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Sea sea;
	Bounds bounds;
	Gui gui;
	ShapeRenderer shapeRenderer;
	Session session;
	SocketClient client;

	@Override
	public void create() {
		var json = new Json();
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		session = new Session("Elon", "Musk");
		bounds = new Bounds();
		sea = new Sea();
		shapeRenderer = new ShapeRenderer();
		bounds.addShapeRenderer(shapeRenderer);
		session.setRenderer(shapeRenderer);
		gui = new Gui();
		gui.setPlayerOne(session.getPlayer());
		gui.setPlayerTwo(session.getOpponent());
		gui.addShapeRenderer(shapeRenderer);

		try {
			client = new SocketClient();
			while (!client.getClient().isReady()) {
			}
			client.getClient().sendMessage(json.prettyPrint(session.getOpponent().Ships().get(0)));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void render() {
		// for Sprite Batch : Textures
		batch.begin();
		ScreenUtils.clear(Color.SLATE);
		sea.draw(batch);
		session.draw(batch);
		gui.drawFont(batch);
		batch.end();
		// for ShapeRenderer
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setAutoShapeType(true);
		bounds.render();
		gui.render();
		session.render();
		shapeRenderer.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		var mouse = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
		session.onTouchDown(mouse, button);
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
