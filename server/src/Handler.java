import java.io.PrintWriter;
import java.util.HashMap;
import com.badlogic.gdx.utils.Json;

import payloads.Player;
import payloads.Session;

public class Handler {
	private Json json = new Json();
	private HashMap<String, Session> sessions;
	public static HashMap<String, PrintWriter> writer;

	public Handler() {

	}

	public void readSession(String payload) {
		var session = json.fromJson(Session.class, payload);
	}
}
