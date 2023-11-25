import java.util.UUID;

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
	String playerName;
	String opponentName;
	SpriteBatch batch;
	Sea sea;
	Bounds bounds;
	Gui gui;
	ShapeRenderer shapeRenderer;
	Session session;
	SocketClient.ClientHandler client;
	Json json = new Json();

	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		playerName = UUID.randomUUID().toString().substring(0, 4);
		opponentName = "Waiting... ";
		session = new Session(playerName, opponentName);
		bounds = new Bounds();
		sea = new Sea();
		shapeRenderer = new ShapeRenderer();
		bounds.addShapeRenderer(shapeRenderer);
		session.setRenderer(shapeRenderer);
		gui = new Gui();
		gui.setPlayer(session.getPlayer());
		gui.setOpponent(session.getOpponent());
		gui.addShapeRenderer(shapeRenderer);
		try {
			var sc = new SocketClient();
			while (!sc.getClient().isReady())
				;
			client = sc.getClient();
			if (client.sendSession(session.serialize())) {
				System.out.println("session accepted");
			}
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
		bounds.renderTurn(session.getPlayer().getTurn());
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
		session.updateScore();
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
