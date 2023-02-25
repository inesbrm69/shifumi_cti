package common;

import java.io.Serializable;

public class Player implements Serializable {
	private int id;
	private String username;
	private String password;
	private int score;
	private int perso;
	private int x;
	private int y;

	public Player(int id, String username, String password, int score, int perso) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.score = score;
		this.perso = perso;
		this.x = 9;
		this.y = 8;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPerso() {
		return perso;
	}
	public void setPerso(int perso) {
		this.perso = perso;
	}

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

	public void moveUp() {
		y--;
	}

	public void moveDown() {
		y++;
	}

	public void moveLeft() {
		x--;
	}

	public void moveRight() {
		x++;
	}

	public void initPlayer() {
		//
	}

}