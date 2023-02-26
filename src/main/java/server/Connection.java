package server;

import common.ClientSingleton;
import common.Message;
import client.Client;
import common.Player;
import common.PlayerSingleton;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Connection implements Runnable{
	private Server server;
	private ServerSocket serverSocket;
	private String[] connectedUsers = {}; // Tableau de String vide

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

				String user = newClient.getPlayerUsername();
				if (connectedUsers.length == 0 || !Arrays.asList(connectedUsers).contains(user)) {
					if(server.getPlayingClients().size() < 8) {
						server.addClient(newClient);
						server.addPlayingClient(newClient);
						connectedUsers = Arrays.copyOf(connectedUsers, connectedUsers.length + 1);
						connectedUsers[connectedUsers.length - 1] = user;
						System.out.println("Joueur ajouté dans le tableau");
					}
					else {
						server.addClient(newClient);
						server.addWaitingClients(newClient);
						server.sendMessageToId(new Message("Trop de joueurs connectés..."), newClient.getId());
					}
				} else {
					System.out.println("Joueur déjà dans le tableau");
				}

				Thread threadNewClient = new Thread(newClient);
				threadNewClient.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}