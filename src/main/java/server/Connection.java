package server;

import common.Player;

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
			this.serverSocket = new ServerSocket(this.server.getPort());
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
				
				Player newClient = new Player(server, sockNewClient);
				newClient.setId(server.getNumClients()) ;
				
				server.addClient(newClient);
				
				Thread threadNewClient = new Thread(newClient);
				threadNewClient.start();
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
