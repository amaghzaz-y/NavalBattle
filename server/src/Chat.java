import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class Chat {
	private Set<String> clients;
	private HashMap<String, Vector<String>> messages; // client username name as key

	public Chat() {
		clients = new HashSet<>();
		messages = new HashMap<>();
	}

	public void AddUser(String username) {
		if (clients.contains(username))
			return;
		clients.add(username);
	}

	public void addMessage(String username, String message) {
		var msg = messages.get(username);
		msg.add(message);
		messages.put(username, msg);
	}

	public Vector<String> getMessages(String username) {
		return messages.get(username);
	}
}
