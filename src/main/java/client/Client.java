package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import application.AuthController;
import application.JeuController;
import application.ShiFuMiController;
import common.Message;
import server.Connection;

public class Client {

	private String address;
	private int port;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private AuthController view;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public AuthController getView() {
		return view;
	}

	public void setView(AuthController view) {
		this.view = view;
	}

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
		/*view.printNewMessage(mess);*/
	}

	public void sendMessage(Message message) throws IOException {
		this.out.writeObject(message);
		this.out.flush();
	}
	
	
}
