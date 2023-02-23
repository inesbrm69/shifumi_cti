package server;

import common.Message;
import common.Player;
import common.PlayerSingleton;

import java.util.ArrayList;
import java.util.List;

public class Server {
	private int port;
	private List<ConnectedClient> clients;
	private List<ConnectedClient> playingClients;
	private List<ConnectedClient> waitingClients;


	public List<ConnectedClient> getClients() {
		return clients;
	}

	public void setClients(List<ConnectedClient> clients) {
		this.clients = clients;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public List<ConnectedClient> getPlayingClients() {
		return playingClients;
	}

	public void setPlayingClients(List<ConnectedClient> clients) {
		this.playingClients = clients;
	}

	public List<ConnectedClient> getWaitingClients() {
		return waitingClients;
	}

	public void setWaitingClients(List<ConnectedClient> clients) {
		this.waitingClients = clients;
	}

	public Server(int port) {
		super();
		this.port = port;
		this.clients = new ArrayList<ConnectedClient>();
		this.playingClients = new ArrayList<ConnectedClient>();
		this.waitingClients = new ArrayList<ConnectedClient>();

		Thread threadConnection = new Thread(new Connection(this));
		threadConnection.start();
	}

	public int getPort() {
		return this.port;
	}

	public int getNumClients() {
		return getClients().size();
	}

	public void addClient(ConnectedClient newClient) {
		this.clients.add(newClient);
		//broadcastMessage(new Message(newClient.getPlayerUsername(), " is connected"), newClient.getId());
	}

	public void addPlayingClient(ConnectedClient newClient) {
		this.playingClients.add(newClient);
		//broadcastMessage(new Message(newClient.getPlayerUsername(), " is playing"), newClient.getId());
	}

	public void addWaitingClients(ConnectedClient newClient) {
		this.waitingClients.add(newClient);
		//broadcastMessage(new Message(newClient.getPlayerUsername(), " is waiting to join"), newClient.getId());
	}

	public void broadcastMessage(Message mess, int id) {
		for (ConnectedClient client : clients) {
			if (client.getId() != id) {
				//if(mess.getSenderString() == null){
					//mess.setSenderString(mess.getSender().getPlayerUsername());
				//}
				client.sendMessage(mess);
			}
		}

	}

	public void messageToPlayers(Message mess, int id, boolean sendToAll) {
		for (ConnectedClient client : playingClients) {
			if(!sendToAll) {
				if (client.getId() != id) {
					if(mess.getSenderString() == null){
						mess.setSenderString(client.getPlayerUsername());
					}
					client.sendMessage(mess);
				}
			}
			else {
				client.sendMessage(mess);
			}
		}
	}

	public void sendMessageToId(Message mess, int idUser) {
		ConnectedClient client = clients.get(idUser);
		if(mess.getSenderString() == null){
			mess.setSenderString(client.getPlayerUsername());
		}
		client.sendMessage(mess);
	}

	public void disconnectedClient(ConnectedClient connectedClient) {
		connectedClient.closeClient();
		clients.remove(connectedClient);
		broadcastMessage(new Message(connectedClient.getPlayerUsername(), " déconnecté"), connectedClient.getId());
//		addClient(this.waitingClients.get(0));
//		this.waitingClients.remove(0);
	}
}