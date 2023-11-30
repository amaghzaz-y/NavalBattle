
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import common.*;
import common.ShipBase.Direction;

public class Session {
	private String ID;
	private Player player;
	private Player opponent;
	private boolean turn;
	private Rectangle playerBounds = new Rectangle(320, 0, 320, 440);
	private Rectangle opponentBounds = new Rectangle(0, 0, 320, 440);
	private Hitzone zones = new Hitzone();

	public Session(String player, String opponent) {
		this.player = new Player(player);
		this.opponent = new Player(opponent);

		this.player
				.addShip(new Ship(new Vector2(360 + random(), 40 + random()), getRandomDirection(),
						ShipBase.Type.VerySmall));

		this.player.addShip(
				new Ship(new Vector2(580, 80 + random()), Direction.Vertical, ShipBase.Type.Small));

		this.player.addShip(new Ship(new Vector2(420 + random() - 40, 200
				+ random()), getRandomDirection(), ShipBase.Type.Medium));

		this.player.addShip(
				new Ship(new Vector2(550, 340 - random()), getRandomDirection(), ShipBase.Type.Big));
	}

	public boolean inBounds(Rectangle bounds, Vector2 mouse) {
		return bounds.contains(mouse);
	}

	public void setTurn(boolean state) {
		turn = state;
	}

	public boolean getTurn() {
		return turn;
	}

	public Direction getRandomDirection() {
		Random rand = new Random();
		int value = rand.nextInt(2);
		var dir = value == 1 ? Direction.Horizontal : Direction.Vertical;
		return dir;
	}

	public int random() {
		Random rand = new Random();
		int value = rand.nextInt(80);
		return value;
	}

	public Player getPlayer() {
		return player;
	}

	public Player getOpponent() {
		return opponent;
	}

	public void updateOpponent(payloads.Session session) {
		var op = session.opponent;
		opponent.setPlayerName(op.username);
		for (payloads.Ship s : op.ships) {
			Ship ship = Ship.NewfromPayload(s);
			opponent.addShip(ship);
		}
	}

	public void updateScore() {
		opponent.setScore(player.HitsUpdate());
		player.setScore(opponent.HitsUpdate());
	}

	public void setSessionID(String id) {
		ID = id;
	}

	public String getSessionID() {
		return ID;
	}

	public payloads.Session serialize() {
		var pp = player.serialize();
		var op = opponent.serialize();
		var ctx = new payloads.Session(pp, op);
		ctx.session = ID;
		ctx.type = 2;
		return ctx;
	}

	public boolean receiveMissile(Vector2 mouse, int button) {
		System.out.println(mouse.toString());
		if (inBounds(playerBounds, mouse)) {
			player.onTouchDown(mouse, button);
			return true;
		}
		return false;
	}

	public boolean handleMissileClick(Vector2 mouse, int button) {
		if (inBounds(opponentBounds, mouse) && getTurn()) {
			opponent.onTouchDown(mouse, button);
			zones.onTouchDown(mouse);
			return true;
		}
		return false;
	}

	public void setRenderer(ShapeRenderer renderer) {
		player.setRenderer(renderer);
		opponent.setRenderer(renderer);
		zones.setRenderer(renderer);
	}

	public void draw(Batch batch) {
		player.draw(batch);
		opponent.draw(batch);
	}

	public void render() {
		player.render();
		opponent.render();
		zones.render();
	}
}
