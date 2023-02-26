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

	/** Méthode qui ajoute un client dans la liste "clients"
	 * @param newClient
	 */
	public void addClient(ConnectedClient newClient) {
		this.clients.add(newClient);
		//broadcastMessage(new Message(newClient.getPlayerUsername(), " is connected"), newClient.getId());
	}

	/** Méthode qui ajoute le nouveau client dans la liste des joueurs qui y joue
	 * @param newClient
	 */
	public void addPlayingClient(ConnectedClient newClient) {
		this.playingClients.add(newClient);
		//broadcastMessage(new Message(newClient.getPlayerUsername(), " is playing"), newClient.getId());
	}

	/** Méthode qui met les joueurs dans une liste d'attente (max de joueurs 8)
	 * @param newClient
	 */
	public void addWaitingClients(ConnectedClient newClient) {
		this.waitingClients.add(newClient);
		//broadcastMessage(new Message(newClient.getPlayerUsername(), " is waiting to join"), newClient.getId());
	}

	public void broadcastMessage(Message mess, int id) {
		for (ConnectedClient client : clients) {
			if (client.getId() != id) {
				client.sendMessage(mess);
			}
		}

	}

	/** Envoie un message aux joueurs dans le jeu.
	 * @param mess le message à envoyer
	 * @param id l'ID du joueur qui a envoyé le message
	 * @param sendToAll indique s'il faut envoyer le message à tous les joueurs ou seulement aux joueurs qui ne sont pas l'expéditeur
	 */
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

	/** Envoie un message à un utilisateur spécifié par son identifiant.
	 * @param mess le message à envoyer
	 * @param idUser l'identifiant de l'utilisateur destinataire
	 */
	public void sendMessageToId(Message mess, int idUser) {
		ConnectedClient client = clients.get(idUser);
		if(mess.getSenderString() == null){
			mess.setSenderString(client.getPlayerUsername());
		}
		client.sendMessage(mess);
	}

	/**Déconnecte un client connecté.
	 * @param connectedClient le client connecté à déconnecter
	 */
	public void disconnectedClient(ConnectedClient connectedClient) {
		connectedClient.closeClient();
		clients.remove(connectedClient);
		broadcastMessage(new Message(connectedClient.getPlayerUsername(), " déconnecté"), connectedClient.getId());
	}
}