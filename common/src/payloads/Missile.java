package payloads;

public class Missile {
	public int type; // request type
	public String session;
	public String player;
	public String opponent;
	public int X;
	public int Y;

	public Missile() {
		type = 3;
	}

	public Missile(String player, String opponent, int X, int Y) {
		type = 3;
		this.X = X;
		this.Y = Y;
		this.opponent = opponent;
		this.player = player;
	};
}
