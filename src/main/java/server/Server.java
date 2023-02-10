package server;

import java.util.ArrayList;
import java.util.List;

import common.Message;
import common.Player;

public class Server {
	private int port;
	private List<Player> clients;

	public List<Player> getClients() {
		return clients;
	}

	public void setClients(List<Player> clients) {
		this.clients = clients;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Server(int port) {
		super();
		this.port = port;
		this.clients = new ArrayList<Player>();

		Thread threadConnection = new Thread(new Connection(this));
		threadConnection.start();
	}

	public int getPort() {
		// TODO Auto-generated method stub
		return this.port;
	}

	public int getNumClients() {
		return getClients().size();
	}

	public void addClient(Player newClient) {
		if(this.clients.size() < 8) {
			this.clients.add(newClient);
			broadcastMessage(new Message(Integer.toString(newClient.getId()), " connected", 0), newClient.getId());
		}
		else {
			//"File d'attente..."
		}
		
	}

	public void broadcastMessage(Message mess, int id) {
		for (Player client : clients) {
			if (client.getId() != id) {
				client.sendMessage(mess);
			}
		}

	}

	public void disconnectedClient(Player connectedClient) {
		connectedClient.closeClient();
		clients.remove(connectedClient);
		broadcastMessage(new Message(Integer.toString(connectedClient.getId()), " disconnected", 0), connectedClient.getId());

	}

}
