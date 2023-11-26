import java.util.UUID;

public class ServerLauncher {
	public static void main(String[] args) throws Exception {
		System.out.println("SERVER STARTED ON PORT 6700 : " + UUID.randomUUID());
		new Server();
		return;
	}
}
