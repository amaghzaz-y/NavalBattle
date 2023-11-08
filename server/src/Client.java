public class Client {
	private String ipaddress;
	private String gameRoom;
	private String username;

	public Client() {
	};

	public String getUserName() {
		return username;
	}

	public String getGameRoom() {
		return gameRoom;
	}

	public String getIpAddress() {
		return ipaddress;
	}

	public void setIpAddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public void setGameRoom(String gameRoom) {
		this.gameRoom = gameRoom;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
