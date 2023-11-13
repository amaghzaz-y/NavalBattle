import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Session {
	private Player player;
	private Player opponent;
	private int playerScore = 0;
	private int opponentScore = 0;

	public Session(String player, String opponent) {
		this.player = new Player(player);
		this.opponent = new Player(opponent);
		this.player.addShip(new Ship(new Vector2(50, 200), ShipBase.Direction.Horizontal, ShipBase.Type.VerySmall));
		this.player.addShip(new Ship(new Vector2(120, 400), ShipBase.Direction.Horizontal, ShipBase.Type.Small));
		this.player.addShip(new Ship(new Vector2(200, 80), ShipBase.Direction.Vertical, ShipBase.Type.Medium));
		this.player.addShip(new Ship(new Vector2(250, 300), ShipBase.Direction.Horizontal, ShipBase.Type.Big));
		this.opponent.addShip(new Ship(new Vector2(360, 50), ShipBase.Direction.Vertical, ShipBase.Type.VerySmall));
		this.opponent.addShip(new Ship(new Vector2(580, 120), ShipBase.Direction.Vertical, ShipBase.Type.Small));
		this.opponent.addShip(new Ship(new Vector2(420, 250), ShipBase.Direction.Horizontal, ShipBase.Type.Medium));
		this.opponent.addShip(new Ship(new Vector2(550, 340), ShipBase.Direction.Vertical, ShipBase.Type.Big));
	}

	public Player getPlayer() {
		return player;
	}

	public Player getOpponent() {
		return opponent;
	}

	public void onTouchDown(Vector2 mouse, int button) {
		player.onTouchDown(mouse, button);
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
