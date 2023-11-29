package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.badlogic.gdx.utils.Json;

import payloads.Message;
import payloads.Messages;
import payloads.Missile;
import payloads.Session;
import payloads.Status;
import payloads.Users;

public class SocketClient {
	private static String ADDR = "localhost";
	private static String PORT = "6700";
	public ClientHandler client;
	private static Json json;
	private static String username;
	private static String session;

	public SocketClient() throws Exception {
		json = new Json();
		json.setIgnoreUnknownFields(true);
	}

	public void setServerAddr(String address, String port) {
		ADDR = address;
		PORT = port;
	}

	public void start() {
		try {
			Socket socket = new Socket(ADDR, Integer.parseInt(PORT));
			client = new ClientHandler(socket);
			client.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUsername(String name) {
		username = name;
	}

	public void setSession(String id) {
		session = id;
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

		// client readiness state
		public boolean isReady() {
			return ready;
		}

		public void waitTillReady() {
			while (!isReady())
				;
		}

		// handle socket messaging
		public void send(String message) throws IOException {
			writer.println(message);
			writer.flush();
		}

		public String read() throws IOException {
			try {
				while (socket.isConnected() && !socket.isInputShutdown() && socket.isBound()) {
					String msg = reader.readLine();
					if (!msg.isEmpty()) {
						System.out.println(msg);
						return msg;
					}
				}
			} catch (Exception e) {
				reader.close();
				writer.close();
				socket.close();
			}
			return new String();
		}

		public Messages requestMessages() throws IOException {
			Status status = new Status();
			status.type = 1;
			status.code = 8;
			status.sender = username;
			String request = json.toJson(status);
			send(request);
			String response = read();
			var messages = json.fromJson(payloads.Messages.class, response);
			return messages;
		}

		public boolean registerLauncher() throws IOException {
			Status status = new Status();
			status.sender = username;
			status.code = 10;
			status.type = 1;
			String request = json.toJson(status);
			send(request);
			String response = read();
			status = json.fromJson(payloads.Status.class, response);
			if (status.code == 1)
				return true;
			return false;
		}

		public boolean requestMissile() throws IOException {
			Status status = new Status();
			status.sender = username;
			status.session = session;
			status.code = 5;
			status.type = 1;
			String request = json.toJson(status);
			send(request);
			String response = read();
			status = json.fromJson(payloads.Status.class, response);
			if (status.code == 1)
				return true;
			return false;
		}

		public boolean requestSession() throws IOException {
			Status status = new Status();
			status.code = 4;
			status.sender = username;
			status.session = session;
			status.type = 1;
			String request = json.toJson(status);
			send(request);
			String response = read();
			status = json.fromJson(payloads.Status.class, response);
			if (status.code == 3)
				return false;
			return true;
		}

		public boolean requestTurn() throws IOException {
			Status status = new Status();
			status.type = 1;
			status.code = 6;
			status.sender = username;
			status.session = session;
			String request = json.toJson(status);
			send(request);
			String response = read();
			status = json.fromJson(payloads.Status.class, response);
			if (status.code == 1)
				return true;
			return false;
		}

		public Users requestUsers() throws IOException {
			Status status = new Status();
			status.type = 1;
			status.code = 9;
			status.sender = username;
			String request = json.toJson(status);
			send(request);
			String response = read();
			var users = json.fromJson(payloads.Users.class, response);
			return users;
		}

		public boolean sendMessage(Message message) throws IOException {
			String request = json.toJson(message);
			send(request);
			String response = read();
			Status status = json.fromJson(payloads.Status.class, response);
			if (status.code == 1)
				return true;
			return false;
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

		public boolean sendSession(Session s) throws IOException {
			// handle request
			String request = json.toJson(s);
			send(request);
			// handle response
			String response = read();
			Status status = json.fromJson(payloads.Status.class, response);
			if (status.code == 1)
				return true;
			return false;
		}

		public Missile readMissile() throws IOException {
			// handle response
			String response = read();
			Missile missile = json.fromJson(payloads.Missile.class, response);
			return missile;
		}

		public Session readSession() throws IOException {
			// handle response
			String response = read();
			Session session = json.fromJson(payloads.Session.class, response);
			return session;
		}
	}
}
