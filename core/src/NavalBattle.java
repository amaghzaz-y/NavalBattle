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

import common.Utils;
import payloads.Missile;

public class NavalBattle extends ApplicationAdapter implements InputProcessor {
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
		session = new Session(UUID.randomUUID().toString().substring(0, 4), "Waiting... ");
		session.setSessionID("12345");
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
			sc.setUsername(session.getPlayer().getPlayerName());
			sc.setSession(session.getSessionID());
			while (!sc.getClient().isReady())
				;
			client = sc.getClient();
			if (client.sendSession(session.serialize())) {
				System.out.println("session accepted");
			} else {
				System.exit(0);
			}
			while (!client.requestSession()) {
				System.out.println("waiting for session");
				Thread.sleep(500);
			}
			payloads.Session s = client.readSession();
			System.out.println("session received :" + s.session);
			System.out.println("opponent :" + s.opponent.username);
			session.updateOpponent(s);
			// session.setTurn(client.requestTurn());
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
		bounds.renderTurn(session.getTurn());
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
		try {
			if (client.requestTurn()) {
				session.setTurn(true);
				var mouse = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
				if (!session.handleMissileClick(mouse, button))
					return true;
				session.updateScore();
				var missile = new Missile();
				var npos = Utils.serializeClick(mouse);
				missile.X = (int) npos.x;
				missile.Y = (int) npos.y;
				missile.player = session.getPlayer().getPlayerName();
				missile.opponent = session.getOpponent().getPlayerName();
				missile.type = 3;
				missile.session = session.getSessionID();
				if (client.sendMissile(missile)) {
					System.out.println("missile sent");
				} else {
					System.out.println("missile NOT sent");
				}
			} else {
				session.setTurn(false);
				System.out.println("WAIT FOR TURN !!");
			}
		} catch (Exception e) {
			System.out.println(e);
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
