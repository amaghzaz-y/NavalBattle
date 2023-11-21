
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Session {
	private Player player;
	private Player opponent;
	private Rectangle playerBounds = new Rectangle(0, 0, 320, 440);
	private Rectangle opponentBounds = new Rectangle(320, 0, 320, 440);

	public Session(String player, String opponent) {
		this.player = new Player(player);
		this.opponent = new Player(opponent);
		// default ships
		this.player.addShip(new Ship(new Vector2(50, 200), ShipBase.Direction.Horizontal, ShipBase.Type.VerySmall));
		this.player.addShip(new Ship(new Vector2(120, 400), ShipBase.Direction.Horizontal, ShipBase.Type.Small));
		this.player.addShip(new Ship(new Vector2(200, 80), ShipBase.Direction.Vertical, ShipBase.Type.Medium));
		this.player.addShip(new Ship(new Vector2(250, 300), ShipBase.Direction.Horizontal, ShipBase.Type.Big));
		this.opponent.addShip(new Ship(new Vector2(360, 50), ShipBase.Direction.Vertical, ShipBase.Type.VerySmall));
		this.opponent.addShip(new Ship(new Vector2(580, 120), ShipBase.Direction.Vertical, ShipBase.Type.Small));
		this.opponent.addShip(new Ship(new Vector2(420, 250), ShipBase.Direction.Horizontal, ShipBase.Type.Medium));
		this.opponent.addShip(new Ship(new Vector2(550, 340), ShipBase.Direction.Vertical, ShipBase.Type.Big));
		// setting boundaries

	}

	public void updateScore() {
		opponent.setScore(player.HitsUpdate());
		player.setScore(opponent.HitsUpdate());
	}

	public boolean inBounds(Rectangle bounds, Vector2 mouse) {
		return bounds.contains(mouse);
	}

	public Player getPlayer() {
		return player;
	}

	public Player getOpponent() {
		return opponent;
	}

	public void onTouchDown(Vector2 mouse, int button) {
		if (inBounds(playerBounds, mouse))
			player.onTouchDown(mouse, button);
		if (inBounds(opponentBounds, mouse))
			opponent.onTouchDown(mouse, button);
	}

	public void setRenderer(ShapeRenderer renderer) {
		player.setRenderer(renderer);
		opponent.setRenderer(renderer);
	}

	public void draw(Batch batch) {
		player.drawShips(batch);
		opponent.drawShips(batch);
	}

	public void render() {
		player.renderShips();
		opponent.renderShips();
	}
}
