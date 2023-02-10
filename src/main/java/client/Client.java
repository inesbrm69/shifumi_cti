package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import common.Message;
import server.Connection;

public class Client {

	private String address;
	private int port;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Client(String address, int port) {
		super();
		this.address = address;
		this.port = port;
		
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
