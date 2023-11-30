
import java.io.IOException;

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
	Session session;
	SocketClient.ClientHandler client;
	boolean turnRequest = true;
	Json json = new Json();
	boolean start = false;

	public NavalBattle(String Username, String Session, String Server, String Port) {
		username = Username;
		server = Server;
		port = Port;
		sessionID = Session;
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
			socket.setServerAddr(server, port);
			socket.setUsername(session.getPlayer().getPlayerName());
			socket.setSession(session.getSessionID());
			socket.start();
			client = socket.getClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new TurnThread().start();
	}

	public class TurnThread extends Thread {

		@Override
		public void run() {
			try {
				client.waitTillReady();
				if (!client.sendSession(session.serialize()))
					System.exit(0);
				while (!client.requestSession()) {
					Thread.sleep(500);
				}
				start = true;
				while (turnRequest) {
					Thread.sleep(100);
					System.out.println("waiting...");
				}
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
				System.out.println("error");
			}
		}

	}

	private void updateOpponent() {
		try {
			var s = client.readSession();
			session.updateOpponent(s);
			turnRequest = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render() {
		// for Sprite Batch : Textures
		ScreenUtils.clear(Color.SLATE);
		if (start) {
			updateOpponent();
			start = false;
		}
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
