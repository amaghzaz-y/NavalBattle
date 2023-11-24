package payloads;

// a wrapper to help the json parser identify the object class
public class Generic {
	// 0 - Error, 1 - Status, 2 - Session, 3 - Missile, 4 - Score
	public int type; // request type

	public Generic() {
	}
}
