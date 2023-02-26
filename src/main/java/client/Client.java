package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import application.controller.AuthController;
import application.controller.JeuController;
import common.PlayerCoords;
import interfaces.IClient;
import common.Message;

public class Client implements IClient {

	private String address;
	private int port;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private AuthController authView;

	private JeuController jeuView;

	private String playerUsername;

	private int playerId;

	private int playerScore;


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public AuthController getAuthView() {
		return authView;
	}

	//region Implementation interface
	public String getPlayerUsername() {
		return playerUsername;
	}

	@Override
	public int getPlayerId() {
		return playerId;
	}

	@Override
	public int getPlayerScore() {
		return playerScore;
	}

	@Override
	public void setPlayerScore(int score) {
		this.playerScore = score;
	}

	@Override
	public void setPlayerUsername(String username) {
		this.playerUsername = username;
	}

	@Override
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	//endregion



	public void setAuthView(AuthController authView) {
		this.authView = authView;
	}

	public JeuController getJeuView() {
		return jeuView;
	}

	public void setJeuView(JeuController jeuView) {
		this.jeuView = jeuView;
	}

	public Client(String address, int port, String username) {
		super();
		this.address = address;
		this.port = port;
		this.playerUsername = username;
		
		try {
			this.socket = new Socket(address, port);
			this.out = new ObjectOutputStream(socket.getOutputStream());

			Thread threadClientReceive = new Thread(new ClientReceive(this, socket));
			threadClientReceive.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnectedServer() {
		try {
			this.socket.close();
			this.in.close();
			this.out.close();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	/** Méthode qui affiche le message sur le chat au client
	 * @param mess
	 */
	public void messageReceived(Message mess) {
		jeuView.printNewMessage(mess);
	}

	public void coordsReceived(PlayerCoords playerCoords){
		jeuView.printOthers(playerCoords);
	}


	/** Méthode qui envoit le message
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(Message message) throws IOException {
		this.out.writeObject(message);
		this.out.flush();
	}

	public void sendCoords(PlayerCoords playerCoords) throws IOException {
		this.out.writeObject(playerCoords);
		this.out.flush();
	}
}
