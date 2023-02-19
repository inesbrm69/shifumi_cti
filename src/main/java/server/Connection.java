package server;

import common.ClientSingleton;
import common.Message;
import client.Client;
import common.Player;
import common.PlayerSingleton;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection implements Runnable{
	private Server server;
	private ServerSocket serverSocket;

	public Connection(Server server) {
		super();
		this.server = server;
		try {
			this.serverSocket = new ServerSocket(this.server.getPort(), 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while(true) {
				Socket sockNewClient = serverSocket.accept();

				ConnectedClient newClient = new ConnectedClient(server, sockNewClient);
				newClient.setId(server.getNumClients());

				if(server.getPlayingClients().size() < 8) {
					server.addClient(newClient);
					server.addPlayingClient(newClient);
				}
				else {
					server.addClient(newClient);
					server.addWaitingClients(newClient);
					server.sendMessageToId(new Message("Trop de joueurs connectés..."), newClient.getId());
				}

				Thread threadNewClient = new Thread(newClient);
				threadNewClient.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}