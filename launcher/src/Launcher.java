import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class Launcher extends JFrame {
	public Launcher() {
		setSize(500, 600);
		setTitle("NavalBattle 2.0 Launcher");
		setResizable(false);
		GridLayout grid = new GridLayout(6, 1);
		JPanel topbar = new JPanel();
		topbar.setLayout(new FlowLayout());
		JLabel logo = new JLabel("NavalBattle 2.0");
		JPanel panel = new JPanel();
		JButton connect = new JButton("CONNECT");
		JButton chat = new JButton("CHAT");
		JButton scoreboard = new JButton("SCOREBOARD");
		JButton sessions = new JButton("SESSIONS");
		sessions.addActionListener((l) -> {
			panel.add(new JLabel("fuck off !"));
			panel.revalidate();
			panel.repaint();
			System.out.println("d");
		});
		add(topbar);
		topbar.add(connect);
		topbar.add(panel);
		topbar.add(chat);
		topbar.add(scoreboard);
		topbar.add(sessions);
		panel.add(logo);
		grid.setHgap(20);
		grid.setVgap(20);
		logo.setFont(new Font("Roboto", 2, 34));
		add(panel);
		setLayout(grid);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Launcher();
	}
}