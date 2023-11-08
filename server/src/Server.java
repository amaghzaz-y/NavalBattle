import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import com.badlogic.gdx.utils.Array;

public class Server {
	private static final int PORT = 6969;
	private HashMap<String, ObjectOutputStream> messenger;
	private Array<Client> clients = new Array<>();

	public static void main(String[] args) throws Exception {
		ServerSocket listener = new ServerSocket(PORT);
		try {
			while (true) {
				new ClientHandler(listener.accept()).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			listener.close();
		}
	}

	private static class ClientHandler extends Thread {
		private Socket socket;
		private ObjectInputStream input;
		private ObjectOutputStream output;

		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			var istream = socket.getInputStream();
			var ossteam = socket.getOutputStream();
			this.input = new ObjectInputStream(istream);
			this.output = new ObjectOutputStream(ossteam);
		}

		@Override
		public void run() {
			// try {
			// } catch (IOException e) {
			// e.printStackTrace();
			// }

		}

		private void send(String msg) throws IOException {
			this.output.writeUTF(msg);
			this.output.flush();
		}

		private void receive(String msg) throws IOException {
			while (true) {
				var incoming = this.input.readUTF();
				if (incoming.isEmpty() || incoming.isBlank())
					continue;
				// TODO! Add Message Handler
			}
		}
	}
}
