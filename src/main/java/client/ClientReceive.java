package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common.Message;
import common.PlayerCoords;

public class ClientReceive implements Runnable {
	private Client client;
	private Socket socket;
	private ObjectInputStream in;

	//region Controller
	public ClientReceive(Client client, Socket socket) {
		super();
		this.client = client;
		this.socket = socket;
	}
	//endregion

	@Override
	public void run() {
		
		try {
			this.in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean isActive = true;
		while (isActive) {
			Message mess;
			PlayerCoords playerCoords;

			try {
				Object objet = in.readObject();
				if(objet instanceof Message) {
					mess = (Message) objet;
					if (mess != null) {
						if (mess.getSenderString() == null) {
							mess.setSenderString(this.client.getPlayerUsername());
						}
						this.client.messageReceived(mess);
					} else {
						isActive = false;
					}
				}
				else if(objet instanceof PlayerCoords) {
					playerCoords = (PlayerCoords) objet;
					if (playerCoords != null) {
						this.client.coordsReceived(playerCoords);
					} else {
						isActive = false;
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		client.disconnectedServer();

	}

}
