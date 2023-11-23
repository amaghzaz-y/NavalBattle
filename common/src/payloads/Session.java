package payloads;

public class Session {
	public String ID;
	public Player player;
	public Player opponent;

	public Session() {
	}

	public Session(Player player, Player opp) {
		this.player = player;
		this.opponent = opp;
	}
}