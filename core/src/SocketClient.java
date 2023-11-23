import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
	private static final int PORT = 6700;
	private static String received;
	public ClientHandler client;

	public SocketClient() throws Exception {
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
		public void sendMessage(String message) throws IOException {
			writer.println(message);
			writer.flush();
		}

		public String readMessage() throws IOException {
			return reader.readLine();
		}

		public String handleInput() throws IOException {
			try {
				while (socket.isConnected() && !socket.isInputShutdown() && socket.isBound()) {
					String msg = readMessage();
					if (!msg.isEmpty()) {
						received = msg;
						// System.out.println(received);
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
