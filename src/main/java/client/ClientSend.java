package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import common.Message;

public class ClientSend implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;

    public ClientSend(Socket socket, ObjectOutputStream out) {
        super();
        this.socket = socket;
        this.out = out;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            //System.out.print("Votre message >> ");
            //String m = sc.nextLine();
            String m = "";
            Message mess = new Message("client", m);
            try {
                out.writeObject(mess);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
