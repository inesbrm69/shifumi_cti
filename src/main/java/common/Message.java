package common;

import java.io.Serializable;

public class Message implements Serializable{
	private String sender;
	private String content;
	
	public Message(String sender, String content) {
		super();
		this.sender = sender;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Sender:"+ sender + ", says:" + content + "]";
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
