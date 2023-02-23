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
                Label text = new Label("\n"+ message.toString(true));
                text.setPrefWidth(vbox_messages.getPrefWidth() - 20);
                text.setAlignment(Pos.CENTER_LEFT);
                vbox_messages.getChildren().add(text);
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
                        imagePerso.setImage(new Image("C:\\Users\\cesso\\IdeaProjects\\shifumi_cti\\src\\main\\resources\\application\\images\\0.png"));
                        break;
                    case 1:
                        imagePerso.setImage(new Image("C:\\Users\\cesso\\IdeaProjects\\shifumi_cti\\src\\main\\resources\\application\\images\\1.png"));
                        break;
                    case 2:
                        imagePerso.setImage(new Image("C:\\Users\\cesso\\IdeaProjects\\shifumi_cti\\src\\main\\resources\\application\\images\\2.png"));
                        break;
                    case 3:
                        imagePerso.setImage(new Image("C:\\Users\\cesso\\IdeaProjects\\shifumi_cti\\src\\main\\resources\\application\\images\\3.png"));
                        break;
                    default:
                        break;
                }
                usernameLabel.setText(playerSingleton.getUsername());
                scoreLabel.setText(playerSingleton.getScore() + " PTS");
        }
}
