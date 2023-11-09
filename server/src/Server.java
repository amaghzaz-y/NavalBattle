import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
	private static final int PORT = 6969;
	public static HashMap<String, PrintWriter> messenger;

	public Server() throws Exception {
		ServerSocket serverSocket = new ServerSocket(PORT);
		messenger = new HashMap<>();
		try {
			while (true) {
				new ClientHandler(serverSocket.accept()).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			serverSocket.close();
		}
	}

	private static class ClientHandler extends Thread {
		private Socket socket;
		private BufferedReader reader;
		private PrintWriter writer;

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
				handleInput();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// handle socket read
		private String read() throws IOException {
			String line = reader.readLine();
			return line;
		}

		private void handleInput() throws IOException {
			try {
				// username is the first thing to say
				String username = "";
				while (username.isEmpty()) {
					var x = read();
					if (x.startsWith("username:")) {
						username = x.replace("username:", "");
					}
				}
				messenger.put(username, writer);
				while (socket.isConnected() && !socket.isInputShutdown() && socket.isBound()) {
					var line = read();
					String recipient = "";
					while (recipient.isEmpty()) {
						var x = read();
						if (x.startsWith("to:")) {
							recipient = x.replace("to:", "");
						}
					}
					System.out.println("Client " + username + ":" + line);
					var pipe = messenger.get(recipient);
					pipe.println("from: " + username + " - message: " + line);
					pipe.flush();
					messenger.put(recipient, pipe);
					// TODO! handler client - Game Logic
				}

			} catch (Exception e) {
				reader.close();
				writer.close();
				socket.close();
			}
		}
	}
}
