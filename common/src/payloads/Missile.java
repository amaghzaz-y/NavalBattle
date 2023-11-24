package payloads;

public class Missile {
	public String session;
	public String player;
	public String opponent;
	public int X;
	public int Y;

	public Missile(String player, String opponent, int X, int Y) {
		this.X = X;
		this.Y = Y;
		this.opponent = opponent;
		this.player = player;
	};
}
