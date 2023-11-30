package payloads;

public class Message {
	public int type; // request type
	public String sender;
	public String receiver;
	public String message;
	public int code = 5;
    public String session;

    public Message() {

	}
}
