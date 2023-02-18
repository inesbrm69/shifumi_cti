package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common.Message;

public class ClientReceive implements Runnable {
	private Client client;
	private Socket socket;
	private ObjectInputStream in;

	public ClientReceive(Client client, Socket socket) {
		super();
		this.client = client;
		this.socket = socket;
	}

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
			try {
				mess = (Message) in.readObject();
				if (mess != null) {
					this.client.messageReceived(mess);
				} else {
					isActive = false;
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		client.disconnectedServer();

	}

}
