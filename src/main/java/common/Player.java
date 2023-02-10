package common;

import server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Player implements Runnable {
	private static int idCounter;
	private int id;
	private String username;
	private String email;
	private int score;
	private int highest;
	private Server server;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;


	public Player(int id, String username, String email, int score, int highest) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.score = score;
		this.highest = highest;
	}

	public Player(Server server, Socket socket) {
		super();
		this.id = idCounter++;
		this.server = server;
		this.socket = socket;

		try {
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Nouvelle connexion, id = " + id);
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
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

	public void sendMessage(Message mess) {
		try {
			this.out.writeObject(mess);
			this.out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void closeClient() {
		try {
			this.in.close();
			this.out.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			in = new ObjectInputStream(socket.getInputStream());

			boolean isActive = true;

			while (isActive) {
				Message mess;
				try {
					mess = (Message) in.readObject();

					if (mess != null) {
						mess.setSender(String.valueOf(id));
						server.broadcastMessage(mess, id);
						System.out.println(mess);
					} else {
						server.disconnectedClient(this);
						isActive = false;
					}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
