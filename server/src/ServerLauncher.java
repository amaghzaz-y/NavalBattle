import java.util.UUID;

public class ServerLauncher {
	public static void main(String[] args) throws Exception {
		System.out.println(UUID.randomUUID());
		new Server();
		return;
	}
}
