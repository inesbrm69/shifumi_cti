package common;

import java.io.Serializable;

public class Player implements Serializable {
	private int id;
	private String username;
	private String email;
	private int score;
	private int highest;

	public Player(int id, String username, String email, int score, int highest) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.score = score;
		this.highest = highest;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getHighest() {
		return highest;
	}

	public void setHighest(int highest) {
		this.highest = highest;
	}
	
	public void initPlayer() {
		//
	}

}
