import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import common.SocketClient;
import common.SocketClient.ClientHandler;
import payloads.Message;

public class Launcher extends JFrame {
	private String username = new String("username");
	private String serverAddr = new String("127.0.0.1");
	private String serverPort = new String("6700");
	private String session = new String("1234");
	private ClientHandler client;
	private ArrayList<Message> messages = new ArrayList<>();
	private Vector<String> players = new Vector<>();

	public Launcher() {
		setSize(500, 600);
		setTitle("NavalBattle 2.0 Launcher");
		setResizable(false);
		JPanel Scene = new JPanel();
		JPanel topbar = new JPanel();
		JLabel logo = new JLabel("NavalBattle 2.0");
		JButton connect = new JButton("CONNECT");
		JButton chat = new JButton("CHAT");
		JButton scoreboard = new JButton("SCOREBOARD");
		JButton sessions = new JButton("SESSIONS");
		JButton activePlayers = new JButton("PLAYERS");

		add(topbar);
		topbar.setLayout(new FlowLayout());
		Scene.setLayout(new BoxLayout(Scene, 1));

		topbar.add(connect);
		topbar.add(chat);
		topbar.add(activePlayers);
		topbar.add(scoreboard);
		topbar.add(sessions);

		sessions.addActionListener((l) -> {
			Scene.removeAll();
			Scene.add(SessionView());
			Scene.revalidate();
			Scene.repaint();
		});

		connect.addActionListener((l) -> {
			Scene.removeAll();
			Scene.add(ConnectView());
			Scene.revalidate();
			Scene.repaint();
		});

		scoreboard.addActionListener((l) -> {
			Scene.removeAll();
			Scene.add(ScoreBoardView());
			Scene.revalidate();
			Scene.repaint();
		});
		chat.addActionListener((l) -> {
			Scene.removeAll();
			Scene.add(ChatView());
			Scene.revalidate();
			Scene.repaint();
		});

		logo.setFont(new Font("Roboto", 2, 34));
		Scene.setBackground(Color.BLACK);

		add(Scene);
		setVisible(true);
		setLayout(new FlowLayout(1, 10, 10));
	}

	public static void main(String[] args) {
		new Launcher();
	}

	public JPanel ConnectView() {
		JPanel view = new JPanel(new GridLayout(3, 2));
		view.setPreferredSize(new Dimension(400, 200));

		JButton connectButton = new JButton("launch session");
		JButton checkButton = new JButton("check connection");
		JTextField usernameInput = new JTextField(this.username);
		JTextField serverInput = new JTextField(this.serverAddr);
		JTextField portInput = new JTextField(this.serverPort);
		JTextField sessionInput = new JTextField(this.session);

		connectButton.setEnabled(false);

		connectButton.addActionListener((l) -> {
			this.username = usernameInput.getText();
			this.serverAddr = serverInput.getText();
			this.serverPort = portInput.getText();
			this.session = sessionInput.getText();
			new SessionThread(username, session, serverAddr, serverPort).start();
		});

		checkButton.addActionListener((l) -> {
			try {
				this.username = usernameInput.getText();
				this.serverAddr = serverInput.getText();
				this.serverPort = portInput.getText();
				this.session = sessionInput.getText();
				SocketClient socket = new SocketClient();
				socket.setServerAddr(serverAddr, serverPort);
				socket.setUsername(username);
				socket.start();
				client = socket.getClient();
				client.waitTillReady();
				connectButton.setEnabled(true);
				view.add(new Label("connected to server !"));
				view.revalidate();
				view.repaint();
				checkButton.setEnabled(false);
			} catch (Exception e) {
				System.err.println(e);
			}
		});

		view.add(usernameInput);
		view.add(serverInput);
		view.add(portInput);
		view.add(sessionInput);
		view.add(checkButton);
		view.add(connectButton);
		return view;
	}

	public JPanel SessionComponent() {
		JPanel view = new JPanel(new GridLayout(1, 5));
		view.setPreferredSize(new Dimension(400, 40));
		JLabel oppoentLabel = new JLabel("Opponent:");
		JLabel sessionLabel = new JLabel("Session:");
		JLabel opponent = new JLabel("opponent");
		JLabel session = new JLabel("4234o86s");
		JButton join = new JButton("Join");
		view.add(oppoentLabel);
		view.add(opponent);
		view.add(sessionLabel);
		view.add(session);
		view.add(join);
		return view;
	}

	public JPanel ScoreComponent() {
		JPanel view = new JPanel(new GridLayout(1, 5));
		view.setPreferredSize(new Dimension(400, 40));
		JLabel oppoentLabel = new JLabel("Player:");
		JLabel sessionLabel = new JLabel("Score:");
		JLabel opponent = new JLabel("opponent");
		JLabel session = new JLabel("432");
		view.add(oppoentLabel);
		view.add(opponent);
		view.add(sessionLabel);
		view.add(session);
		return view;
	}

	public JPanel ChatInputComponent(JPanel parent) {
		JPanel view = new JPanel(new GridLayout(2, 2));
		JLabel label = new JLabel("Message: ");
		JTextField messageInput = new JTextField("Message here...");
		JButton sendButton = new JButton("Send");
		JList<String> playersList = new JList<>(players);
		JScrollPane scrollPane = new JScrollPane(playersList);

		// Style
		view.setPreferredSize(new Dimension(400, 100));
		messageInput.setPreferredSize(new Dimension(400, 40));
		sendButton.setPreferredSize(new Dimension(100, 40));
		messageInput.setToolTipText("Write your message here!");

		// Behavior
		sendButton.addActionListener((l) -> {
			var m = new Message();
			m.sender = username;
			m.session = session;
			m.type = 5;
			m.message = messageInput.getText();
			messages.add(m);
			System.out.println(playersList.getSelectedValue());
			parent.revalidate();
			parent.repaint();
		});
		view.add(label);
		view.add(messageInput);
		view.add(scrollPane);
		view.add(sendButton);
		return view;
	}

	public JPanel ChatComponent(String user, String message) {
		JPanel view = new JPanel(new GridLayout(1, 1));
		view.setPreferredSize(new Dimension(400, 40));
		JLabel userLabel = new JLabel(String.format("[%s] %s", user, message));
		userLabel.setFont(new Font("Roboto", 1, 14));
		view.add(userLabel);
		return view;
	}

	public JPanel ChatView() {
		JPanel view = new JPanel();
		view.setLayout(new BoxLayout(view, 1));
		Vector<String> x = new Vector<>();
		for (Message msg : messages) {
			x.add(String.format("[%s] %s", msg.sender, msg.message));
		}
		JList<String> playersList = new JList<>(x);
		JScrollPane scrollPane = new JScrollPane(playersList);
		scrollPane.setPreferredSize(new Dimension(400, 400));
		view.add(ChatInputComponent(view));
		view.add(scrollPane);
		return view;
	}

	public JPanel SessionView() {
		JPanel view = new JPanel(new GridLayout(10, 0));
		view.add(SessionComponent());
		view.add(SessionComponent());
		view.add(SessionComponent());
		return view;
	}

	public JPanel ScoreBoardView() {
		JPanel view = new JPanel(new GridLayout(10, 0));
		view.add(ScoreComponent());
		view.add(ScoreComponent());
		view.add(ScoreComponent());
		return view;
	}
}