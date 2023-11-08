import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
	private static final int PORT = 6969;
	public HashMap<String, PrintWriter> messenger;

	public Server() throws Exception {
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

		private void send(String msg) {
			writer.println(msg);
			writer.flush();
		}

		// handle socket read
		private String read() throws IOException {
			String line = reader.readLine();
			return line;
		}

		private void handleInput() throws IOException {
			// username is the first thing to say
			String username = "";
			while (username.isEmpty()) {
				var x = read();
				if (x.startsWith("username:")) {
					username = x.replace("username:", "");
				}
			}
			while (socket.isConnected()) {
				var line = read();
				System.out.println("Client " + username + ":" + line);
				send(line);
				// TODO! handler client
			}
			socket.close();
		}
	}
}
