package common;

import java.io.Serializable;

public class Message implements Serializable{
	private String sender;
	private String content;
	private int shifumi;
	
	public Message(String sender, String content, int shifumi) {
		super();
		this.sender = sender;
		this.content = content;
		this.shifumi = shifumi;
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

	public int getShifumi() {
		return shifumi;
	}

	public void setShifumi(int shifumi) {
		this.shifumi = shifumi;
	}
	
	
	
}
