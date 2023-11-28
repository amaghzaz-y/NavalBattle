
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

import common.SocketClient;
import common.Utils;
import payloads.Missile;

public class NavalBattle extends ApplicationAdapter implements InputProcessor {
	String username;
	String server;
	String port;
	String sessionID;
	SpriteBatch batch;
	Sea sea;
	Bounds bounds;
	Gui gui;
	ShapeRenderer shapeRenderer;
	static Session session;
	static SocketClient.ClientHandler client;
	Json json = new Json();

	public NavalBattle(String username, String session, String server, String port) {
		this.username = username;
		this.server = server;
		this.port = port;
		this.sessionID = session;
	}

	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		session = new Session(username, "fetching... ");
		session.setSessionID(sessionID);
		bounds = new Bounds();
		sea = new Sea();
		shapeRenderer = new ShapeRenderer();
		bounds.addShapeRenderer(shapeRenderer);
		session.setRenderer(shapeRenderer);
		gui = new Gui();
		gui.setPlayer(session.getPlayer());
		gui.setOpponent(session.getOpponent());
		try {
			var socket = new SocketClient();
			socket.setUsername(session.getPlayer().getPlayerName());
			socket.setSession(session.getSessionID());
			client = socket.getClient();
			while (!socket.getClient().isReady())
				;
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
			new TurnThread().start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static class TurnThread extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					if (!client.requestTurn()) {
						session.setTurn(false);
					} else {
						session.setTurn(true);
						if (client.requestMissile()) {
							var missile = client.readMissile();
							session.receiveMissile(new Vector2(missile.X * 40 + 330, missile.Y * 40 + 10), 0);
						}
						session.updateScore();
					}
					// to limit ddosing our little server :)
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void render() {
		// for Sprite Batch : Textures
		ScreenUtils.clear(Color.SLATE);
		if (session.getOpponent().getScore() < 16 && session.getPlayer().getScore() < 16) {
			batch.begin();
			sea.draw(batch);
			session.draw(batch);
			gui.draw(batch);
			batch.end();
			// for ShapeRenderer
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setAutoShapeType(true);
			session.render();
			bounds.render();
			bounds.renderTurn(session.getTurn());
			shapeRenderer.end();
		} else {
			batch.begin();
			sea.draw(batch);
			gui.draw(batch);
			session.draw(batch);
			batch.end();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		try {
			if (session.getTurn()) {
				var mouse = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
				if (!session.handleMissileClick(mouse, button))
					return true;
				var missile = new Missile();
				var npos = Utils.serializeClick(mouse);
				missile.X = (int) npos.x;
				missile.Y = (int) npos.y;
				missile.player = session.getPlayer().getPlayerName();
				missile.opponent = session.getOpponent().getPlayerName();
				missile.type = 3;
				missile.session = session.getSessionID();
				client.sendMissile(missile);
				session.setTurn(false);
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
