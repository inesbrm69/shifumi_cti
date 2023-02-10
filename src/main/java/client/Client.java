package client;

import common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

	private static String pseudo = null;
	private String address;
	private int port;

	public static String getPseudo() {
		return pseudo;
	}

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Client(String address, int port, String pseudo) {
		super();
		this.address = address;
		this.port = port;
		this.pseudo = pseudo;
		
		try {
			this.socket = new Socket(address, port);
			this.out = new ObjectOutputStream(socket.getOutputStream());
			
			
			Thread threadClientSend = new Thread(new ClientSend(socket, this.out));;
			threadClientSend.start();
			Thread threadClientReceive = new Thread(new ClientReceive(this, socket));
			threadClientReceive.start();
			
			try {
				threadClientSend.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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

	public void messageReceived(Message mess) {
		System.out.println(mess);
	}
	
	
}
