package payloads;

public class Session {
	public int type; // request type
	public String session;
	public Player player;
	public Player opponent;

	public Session() {
	}

	public Session(Player player, Player opp) {
		this.player = player;
		this.opponent = opp;
	}
}