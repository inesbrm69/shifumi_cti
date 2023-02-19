package common;

import interfaces.IClient;

import java.io.Serializable;

public class Message implements Serializable {
	private IClient sender;
	private String content;

	private String senderString;

	// shifumi = 0 : pierre
	// shifumi = 1 : feuille
	// shifumi = 2 : ciseau
	private int shifumi;

	public Message(String content) {
		super();
		this.content = content;
	}
	public Message(IClient sender, String content) {
		super();
		this.sender = sender;
		this.content = content;
	}
	public Message(IClient sender, String content, int shifumi) {
		super();
		this.sender = sender;
		this.content = content;
		this.shifumi = shifumi;
	}

	public Message(String sender, String content){
		super();
		this.senderString = sender;
		this.content = content;
	}

	/*@Override
	public String toString() {
		return "Message{" +
				"sender=" + sender +
				", content='" + content + '\'' +
				", senderString='" + senderString + '\'' +
				", shifumi=" + shifumi +
				'}';
	}*/

	public String toString(boolean isSenderString) {
		if(isSenderString){
			return this.getSenderString() + " : " + this.getContent();
		}
		return this.getSender() + " : " + this.getContent();
	}

	public IClient getSender() {
		return sender;
	}

	public void setSender(IClient sender) {
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

	public String getSenderString() {
		return senderString;
	}

	public void setSenderString(String senderString) {
		this.senderString = senderString;
	}
}