package server;

import application.JeuController;
import common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectedClient implements Runnable {
    private static int idCounter;
    private int id;
    private Server server;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private JeuController jeuController = new JeuController();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public JeuController getJeuController() {
        return jeuController;
    }

    public void setJeuController(JeuController jeuController) {
        this.jeuController = jeuController;
    }

    public ConnectedClient(Server server, Socket socket) {
        super();
        this.id = idCounter++;
        this.server = server;
        this.socket = socket;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Nouvelle connexion, id = " + id);
    }

    public void sendMessage(Message mess) {
        try {
            this.out.writeObject(mess);
            this.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeClient() {
        try {
            this.in.close();
            this.out.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());

            boolean isActive = true;

            while (isActive) {
                Message mess;
                try {
                    mess = (Message) in.readObject();

                    if (mess != null) {
                        mess.setSender(String.valueOf(id));
                        server.messageToPlayers(mess, id, false);
                    } else {
                        server.disconnectedClient(this);
                        isActive = false;
                    }
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}