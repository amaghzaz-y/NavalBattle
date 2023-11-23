import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.badlogic.gdx.utils.Json;

import payloads.Missile;
import payloads.Session;
import payloads.Status;

public class SocketClient {
	private static final int PORT = 6700;
	private static String received;
	public ClientHandler client;
	private static Json json;

	public SocketClient() throws Exception {
		json = new Json();
		try {
			Socket socket = new Socket("localhost", PORT);
			client = new ClientHandler(socket);
			client.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ClientHandler getClient() {
		return client;
	}

	public static class ClientHandler extends Thread {
		private Socket socket;
		private BufferedReader reader;
		private PrintWriter writer;
		private boolean ready = false;

		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				var input = socket.getInputStream();
				reader = new BufferedReader(new InputStreamReader(input));
				var outputStream = socket.getOutputStream();
				writer = new PrintWriter(outputStream);
				ready = true;
				// handleInput();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public boolean isReady() {
			return ready;
		}

		// handle socket read
		public void send(String message) throws IOException {
			writer.println(message);
			writer.flush();
		}

		public boolean requestUpdate() throws IOException {
			Status status = new Status();
			status.code = 4;
			String request = json.toJson(status);
			send(request);
			String response = read();
			status = json.fromJson(payloads.Status.class, response);
			if (status.code == 1)
				return true;
			return false;
		}

		// Waiting for update after requesting an update
		public String WildRead() throws IOException {
			return read();
		}

		public boolean sendMissile(Missile missile) throws IOException {
			String request = json.toJson(missile);
			send(request);
			String response = read();
			Status status = json.fromJson(payloads.Status.class, response);
			if (status.code == 1)
				return true;
			return false;
		}

		public boolean requestTurnPermission() throws IOException {
			Status status = new Status();
			status.code = 5;
			String request = json.toJson(status);
			send(request);
			String response = read();
			status = json.fromJson(payloads.Status.class, response);
			if (status.code == 1)
				return true;
			return false;
		}

		public boolean sendSession(Session s) throws IOException {
			String request = json.toJson(s);
			send(request);
			String response = read();
			Status status = json.fromJson(payloads.Status.class, response);
			if (status.code == 1)
				return true;
			return false;
		}

		public String read() throws IOException {
			try {
				while (socket.isConnected() && !socket.isInputShutdown() && socket.isBound()) {
					String msg = reader.readLine();
					if (!msg.isEmpty()) {
						received = msg;
						return received;
					}
				}
			} catch (Exception e) {
				reader.close();
				writer.close();
				socket.close();
			}
			return new String();
		}
	}
}
