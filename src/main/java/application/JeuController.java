package application;

import client.Client;
import client.MainClient;
import common.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import server.ConnectedClient;
import server.Connection;
import server.MainServer;
import server.Server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JeuController {

    @FXML
    private Button btnSend;
    @FXML
    private TextField champMessage;
    @FXML
    private VBox vbox_messages;
    @FXML
    private ScrollPane scrollPane;

    private Server server;
    //private Client client;

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

    //public Client getClient() {
        //return client;
    //}

    //public void setClient(Client client) {
        //this.client = client;
    //}

    //public JeuController(Client client){
      //  this.client = client;
    //}


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

    @FXML
    public void onSendData() throws IOException {
        Message message = new Message("Moi",getChampMessage().getText());
        printNewMessage(message);
        champMessage.setText("");
        //Client client = MainClient.getClient();
        //client.sendMessage(message);
    }
    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String messageToSend = champMessage.getText();
                if(!messageToSend.isEmpty()){
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5,5,5,10));

                    Text text = new Text(messageToSend);
                    TextFlow textFlow = new TextFlow(text);

                    textFlow.setStyle("-fx-color: rgb(239,242,255)"+
                        "-fx-background-color: rgb(15,125,242)"+
                        "-fx-background-raduis: 20px");

                    textFlow.setPadding(new Insets(5,10,5,10));
                    text.setFill(Color.color(0.934,0.945,0.996));

                    hBox.getChildren().add(textFlow);
                    vbox_messages.getChildren().add(hBox);



                    client.messageReceived(new Message(messageToSend));
                    *//*server.messageToPlayers(new Message(messageToSend),);*//*

                }
            }
        });
    }*/
}
