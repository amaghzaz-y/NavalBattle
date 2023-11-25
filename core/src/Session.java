
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import common.*;

public class Session {
	private String ID;
	private Player player;
	private Player opponent;
	private boolean turn;
	private Rectangle playerBounds = new Rectangle(320, 0, 320, 440);
	private Rectangle opponentBounds = new Rectangle(0, 0, 320, 440);

	public Session(String player, String opponent) {
		this.player = new Player(player);
		this.opponent = new Player(opponent);
		this.player
				.addShip(new Ship(new Vector2(360, 50), ShipBase.Direction.Vertical, ShipBase.Type.VerySmall));
		this.player.addShip(new Ship(new Vector2(580, 120), ShipBase.Direction.Vertical, ShipBase.Type.Small));
		this.player.addShip(new Ship(new Vector2(420, 250), ShipBase.Direction.Horizontal, ShipBase.Type.Medium));
		this.player.addShip(new Ship(new Vector2(550, 340), ShipBase.Direction.Vertical, ShipBase.Type.Big));
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
			System.out.println("missile in bounds");
			return true;
		}
		System.out.println("NOT in bounds");
		return false;
	}

	public boolean handleMissileClick(Vector2 mouse, int button) {
		if (inBounds(opponentBounds, mouse) && getTurn()) {
			opponent.onTouchDown(mouse, button);
			return true;
		}
		return false;
	}

	public void setRenderer(ShapeRenderer renderer) {
		player.setRenderer(renderer);
		opponent.setRenderer(renderer);
	}

	public void draw(Batch batch) {
		player.draw(batch);
		opponent.draw(batch);
	}

	public void render() {
		player.render();
		opponent.render();
	}
}
