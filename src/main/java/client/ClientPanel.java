package client;

import javafx.scene.Node;
import javafx.scene.Parent;

public class ClientPanel extends Parent{

    public ClientPanel(Node textToSend, Node scrollReceivedText, Node clearBtn, Node sendBtn) {

        this.getChildren().add(textToSend);
        this.getChildren().add(scrollReceivedText);
        this.getChildren().add(clearBtn);
        this.getChildren().add(sendBtn);
    }

    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

}