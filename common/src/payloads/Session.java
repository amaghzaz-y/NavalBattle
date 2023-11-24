package payloads;

public class Session {
	public int type; // request type
	public String session;
	public Player player;
	public Player opponent;

	public Session() {
		type = 2;
	}

	public Session(Player player, Player opp) {
		type = 2;
		this.player = player;
		this.opponent = opp;
	}
}