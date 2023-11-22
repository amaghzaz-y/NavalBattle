import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.badlogic.gdx.utils.Json;

public class Server {
	private static final int PORT = 6700;
	// public static HashMap<String, PrintWriter> messenger;
	public static Handler handler;
	public static Json json;

	public Server() throws Exception {
		ServerSocket serverSocket = new ServerSocket(PORT);
		// messenger = new HashMap<>();
		handler = new Handler();
		json = new Json();
		try {
			while (true) {
				new Client(serverSocket.accept()).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			serverSocket.close();
		}
	}

	private static class Client extends Thread {
		private Socket socket;
		private BufferedReader reader;
		private PrintWriter writer;

		public Client(Socket socket) throws IOException {
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
				while (socket.isConnected() && !socket.isInputShutdown() && socket.isBound()) {
					var payload = read();
					if (payload.isEmpty())
						continue;
					handler.HandleRequest(payload, writer);
				}
			} catch (Exception e) {
				reader.close();
				writer.close();
				socket.close();
			}
		}
	}
}
