package application;

import client.Client;
/*import client.MainClient;*/
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.apache.commons.codec.binary.Hex;
import server.ConnectedClient;
import server.Connection;
import server.MainServer;
import server.Server;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ResourceBundle;

public class JeuController implements Initializable{

    @FXML
    private Button btnSend;
    @FXML
    private TextField champMessage;
    @FXML
    private VBox vbox_messages;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView imagePerso;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label scoreLabel;

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

    public ImageView getImagePerso() {
        return imagePerso;
    }

    public void setImagePerso(ImageView imagePerso) {
        this.imagePerso = imagePerso;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(Label usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(Label scoreLabel) {
        this.scoreLabel = scoreLabel;
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

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public void setUser(ActionEvent e){
        //mettre dans le initialize parce que pas besoin Action event du coup
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

                if(message.getSenderString().equals(player.getUsername().toString())){
                    //Champ "You"
                    Text username = new Text("You");
                    //La box qui contiendra le username
                    HBox hBoxUsername = new HBox();
                    hBoxUsername.setAlignment(Pos.CENTER_RIGHT);
                    hBoxUsername.setPadding(new Insets(0,0,0,0));

                    TextFlow textFlowUsername = new TextFlow(username);
                    textFlowUsername.setStyle ("-fx-background-color: transparent;");
                    textFlowUsername.setPadding(new Insets(0,5,5,0));

                    username.setFill(Color.color(0,0,0));

                    //Message
                    String messageToSend = message.getContent();
                    //La box qui contiendra le message
                    HBox hBoxMessage = new HBox();
                    hBoxMessage.setAlignment(Pos.CENTER_RIGHT);
                    hBoxMessage.setPadding(new Insets(0,0,0,0));

                    Text text = new Text(messageToSend);

                    text.setStyle("-fx-font-weight: bold;");
                    TextFlow textFlowMessage = new TextFlow(text);
                    textFlowMessage.setStyle ("-fx-background-color: rgb(233,233,235);" +
                            "-fx-border-radius: 10 10 10 10;" +
                            "-fx-background-radius: 10 10 10 10;" +
                            "-fx-font-size: 16px;");

                    textFlowMessage.setPadding(new Insets(5,10,5,10));
                    text.setFill(Color.color(0,0,0));

                    //Ajout du champ username dans la box
                    hBoxUsername.getChildren().add(textFlowUsername);
                    //Ajout du message dans la box
                    hBoxMessage.getChildren().add(textFlowMessage);
                    //Ajout des champs username et message dans la ligne de la VBOX
                    vbox_messages.getChildren().add(hBoxUsername);
                    vbox_messages.getChildren().add(hBoxMessage);
                }else{
                    //Champ "You"
                    Text username = new Text(player.getUsername());
                    //La box qui contiendra le username
                    HBox hBoxUsername = new HBox();
                    hBoxUsername.setAlignment(Pos.CENTER_LEFT);
                    hBoxUsername.setPadding(new Insets(0,0,0,0));

                    TextFlow textFlowUsername = new TextFlow(username);
                    textFlowUsername.setStyle ("-fx-background-color: transparent;");
                    textFlowUsername.setPadding(new Insets(0,5,5,0));

                    username.setFill(Color.color(0,0,0));

                    //Message
                    String messageToSend = message.getContent();
                    //La box qui contiendra le message
                    HBox hBoxMessage = new HBox();
                    hBoxMessage.setAlignment(Pos.CENTER_LEFT);
                    hBoxMessage.setPadding(new Insets(0,0,0,0));

                    Text text = new Text(messageToSend);

                    text.setStyle("-fx-font-weight: bold;");
                    TextFlow textFlowMessage = new TextFlow(text);
                    textFlowMessage.setStyle ("-fx-color: rgb(239,242,255);" +
                            "-fx-background-color: rgb(255,0,0);" +
                            "-fx-border-radius: 10 10 10 10;" +
                            "-fx-background-radius: 10 10 10 10;" +
                            "-fx-font-size: 16px;");

                    textFlowMessage.setPadding(new Insets(5,8,5,8));
                    text.setFill(Color.color(0.934,0.945,0.996));

                    //Ajout du champ username dans la box
                    hBoxUsername.getChildren().add(textFlowUsername);
                    //Ajout du message dans la box
                    hBoxMessage.getChildren().add(textFlowMessage);
                    //Ajout des champs username et message dans la ligne de la VBOX
                    vbox_messages.getChildren().add(hBoxUsername);
                    vbox_messages.getChildren().add(hBoxMessage);
                }
            }
        });
    }

    @FXML
    public void onSendData() throws IOException {
        this.player = PlayerSingleton.getInstance().getObject();
        if(!getChampMessage().getText().isEmpty()){
            Message message = new Message(player.getUsername(), getChampMessage().getText());


            champMessage.setText("");
            this.client = ClientSingleton.getInstance().getObject();
            this.client.sendMessage(message);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Stage stage = (Stage) anchorPane.getScene().getWindow();
        //stage.setOnCloseRequest(event -> this.client.disconnectedServer());
        setPlayerInterface();
    }

        private void setPlayerInterface(){
                Player playerSingleton = PlayerSingleton.getInstance().getObject();

                switch (playerSingleton.getPerso()){
                    case 0:
                        imagePerso.setImage(new Image("0.png"));
                        break;
                    case 1:
                        imagePerso.setImage(new Image("1.png"));
                        break;
                    case 2:
                        imagePerso.setImage(new Image("2.png"));
                        break;
                    case 3:
                        imagePerso.setImage(new Image("3.png"));
                        break;
                    default:
                        break;
                }
                usernameLabel.setText(playerSingleton.getUsername());
                scoreLabel.setText(playerSingleton.getScore() + " PTS");
        }
}
