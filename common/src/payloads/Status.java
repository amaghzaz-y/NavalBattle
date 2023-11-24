package payloads;

public class Status {
	// - Those are requests/status we send/receive to/from the server
	// - requests without a session id are ignored by the server
	// - helps syncing state on frontend. PS: Kind of ¯\_(ツ)_/¯ !
	// Number - Description - Destination
	// 1 - Ok - client
	// 2 - No - client
	// 3 - Wait - client
	// 4 - Session - server
	// 5 - Missile - server
	// 6 - Turn - server
	// 7 - Score - server
	// 8 - ScoreBoard - server
	// 9 - Chat - server
	public int type; // request type
	public String sender;
	public String session;
	public int code = 1;

	public Status() {
		type = 1;
	}

	public Status(int status) {
		type = 1;
		code = status;
	}
}
