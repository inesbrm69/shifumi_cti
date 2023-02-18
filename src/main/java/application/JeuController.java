package application;

import client.Client;
import client.MainClient;
import common.ClientSingleton;
import common.Message;
import common.Player;
import common.PlayerSingleton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import server.ConnectedClient;
import server.Connection;
import server.MainServer;
import server.Server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JeuController{

    @FXML
    private Button btnSend;
    @FXML
    private TextField champMessage;
    @FXML
    private VBox vbox_messages;
    @FXML
    private ScrollPane scrollPane;

    private Server server;
    private Client client;
    private Player player;

    private ConnectedClient connectedClient;

    public Button getBtnSend() {
        return btnSend;
    }

    public void setBtnSend(Button btnSend) {
        this.btnSend = btnSend;
    }

    public TextField getChampMessage() {
        return champMessage;
    }

    public void setChampMessage(TextField champMessage) {
        this.champMessage = champMessage;
    }

    public VBox getVbox_messages() {
        return vbox_messages;
    }

    public void setVbox_messages(VBox vbox_messages) {
        this.vbox_messages = vbox_messages;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setUser(ActionEvent e){
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Player player = (Player) stage.getUserData();
        this.player.setUsername(player.getUsername());
        this.player.setId(player.getId());
    }

    public void printNewMessage(Message message){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Label text = new Label("\n"+ message.toString());
                text.setPrefWidth(vbox_messages.getPrefWidth() - 20);
                text.setAlignment(Pos.CENTER_LEFT);
                vbox_messages.getChildren().add(text);
            }
        });
    }

    public Message getMessage(){
        Player player = PlayerSingleton.getInstance().getObject();
        Message message = new Message(player.getUsername(), getChampMessage().getText());
        return message;
    }

    @FXML
    public void onSendData() throws IOException {
        this.player = PlayerSingleton.getInstance().getObject();
        Message message = new Message(player.getUsername(), getChampMessage().getText());
        printNewMessage(message);
        champMessage.setText("");
        this.client = ClientSingleton.getInstance().getObject();
        //Client client = MainClient.getClient();
        this.client.sendMessage(message);
    }
}
