package server;

import common.Message;

import java.util.ArrayList;
import java.util.List;

public class Server {
	private int port;
	private List<ConnectedClient> clients;

	public List<ConnectedClient> getClients() {
		return clients;
	}

	public void setClients(List<ConnectedClient> clients) {
		this.clients = clients;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Server(int port) {
		super();
		this.port = port;
		this.clients = new ArrayList<ConnectedClient>();

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

	public void addClient(ConnectedClient newClient) {
		this.clients.add(newClient);
		broadcastMessage(new Message(Integer.toString(newClient.getId()), " connected"), newClient.getId());
	}

	public void broadcastMessage(Message mess, int id) {
		for (ConnectedClient client : clients) {
			if (client.getId() != id) {
				client.sendMessage(mess);
			}
		}

	}

	public void disconnectedClient(ConnectedClient connectedClient) {
		connectedClient.closeClient();
		clients.remove(connectedClient);
		broadcastMessage(new Message(Integer.toString(connectedClient.getId()), " disconnected"), connectedClient.getId());

	}

}
