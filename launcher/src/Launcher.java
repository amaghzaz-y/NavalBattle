import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import common.SocketClient;
import common.SocketClient.ClientHandler;
import payloads.Message;
import payloads.Session;

public class Launcher extends JFrame {
	private String username = new String("username");
	private String serverAddr = new String("127.0.0.1");
	private String serverPort = new String("6700");
	private String session = new String("1234");
	private ClientHandler client;
	private ArrayList<Message> messages = new ArrayList<>();
	private ArrayList<Session> sessions = new ArrayList<>();
	private Vector<String> players = new Vector<>();
	private boolean connected = false;
	private JPanel Scene = new JPanel();

	public Launcher() {
		setSize(500, 600);
		setTitle("NavalBattle 2.0 Launcher");
		setResizable(false);
		JPanel topbar = new JPanel();
		JLabel logo = new JLabel("NavalBattle 2.0");
		JButton connect = new JButton("CONNECT");
		JButton chat = new JButton("CHAT");
		JButton scoreboard = new JButton("SCOREBOARD");
		JButton sessionsBtn = new JButton("SESSIONS");
		JButton activePlayers = new JButton("PLAYERS");

		add(topbar);
		topbar.setLayout(new FlowLayout());
		Scene.setLayout(new BoxLayout(Scene, 1));

		topbar.add(connect);
		topbar.add(chat);
		topbar.add(activePlayers);
		topbar.add(scoreboard);
		topbar.add(sessionsBtn);

		connect.addActionListener((l) -> {
			Scene.removeAll();
			Scene.add(ConnectView());
			Scene.revalidate();
			Scene.repaint();
		});

		sessionsBtn.addActionListener((l) -> {
			if (!connected)
				return;
			handleSessionBtn();
			Scene.removeAll();
			Scene.add(SessionView());
			Scene.revalidate();
			Scene.repaint();
		});

		scoreboard.addActionListener((l) -> {
			if (!connected)
				return;
			Scene.removeAll();
			Scene.add(ScoreBoardView());
			Scene.revalidate();
			Scene.repaint();
		});

		chat.addActionListener((l) -> {
			if (!connected)
				return;
			updateChatView();
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

	private void handleSessionBtn() {
		try {
			var ss = client.requestSessions();
			sessions.clear();
			sessions.addAll(ss);
			Scene.revalidate();
			Scene.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateChatView() {
		try {
			var users = client.requestUsers();
			var msgs = client.requestMessages();
			messages.addAll(msgs);
			players.clear();
			for (var user : users.list) {
				if (user.matches(username))
					continue;
				players.add(user);
			}
			Scene.revalidate();
			Scene.repaint();
		} catch (Exception e) {
			System.out.println(e);
		}
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
				client.registerLauncher();
				connectButton.setEnabled(true);
				connected = true;
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

	public JPanel SessionComponent(payloads.Session session) {
		JPanel view = new JPanel(new GridLayout(1, 5));
		view.setPreferredSize(new Dimension(400, 40));
		JLabel oppoentLabel = new JLabel("Opponent:");
		JLabel sessionLabel = new JLabel("Session:");
		JLabel opponent = new JLabel(session.player.username);
		JLabel sessionID = new JLabel(session.session);
		JButton join = new JButton("Join");

		join.addActionListener((l) -> {
			new SessionThread(username, this.session, serverAddr, serverPort).start();
		});

		view.add(oppoentLabel);
		view.add(opponent);
		view.add(sessionLabel);
		view.add(sessionID);
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
			handleSendButton(messageInput, playersList);
		});
		view.add(label);
		view.add(messageInput);
		view.add(scrollPane);
		view.add(sendButton);
		return view;
	}

	private void handleSendButton(JTextField messageInput, JList<String> playersList) {
		var m = new Message();
		m.sender = username;
		m.receiver = playersList.getSelectedValue();
		m.type = 5;
		m.message = messageInput.getText();
		try {
			client.sendMessage(m);
		} catch (IOException e) {
			e.printStackTrace();
		}
		messages.add(m);
		updateChatView();
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
		for (Session s : sessions) {
			view.add(SessionComponent(s));
		}
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