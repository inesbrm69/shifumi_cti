package common;

import client.Client;


public class ClientSingleton {
    private static ClientSingleton instance;
    private Client clientToSend;

    private ClientSingleton() {
        clientToSend = null;
    }

    public static ClientSingleton getInstance() {
        if (instance == null) {
            instance = new ClientSingleton();
        }
        return instance;
    }

    public Client getObject() {
        return clientToSend;
    }

    public void setObject(Client client) {
        this.clientToSend = client;
    }
}
