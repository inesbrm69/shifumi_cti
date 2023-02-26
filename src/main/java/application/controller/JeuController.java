package application.controller;

import client.Client;
/*import client.MainClient;*/
import common.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import server.ConnectedClient;
import server.Server;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
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
    @FXML
    private Pane panelMap;
    @FXML
    private GridPane gridMap;
    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView chairImage;
    @FXML
    private ImageView tableImage;

    private Server server;
    private Client client;
    private Player player;

    private Map map = new Map();

    private String pastTile = "f";

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



    /** Méthode qui fait le style de message (cad que quand c'est le user qui envoie le message, il est en blanc et quand c'est les autres c'est en rouge
     ** @param message : contient les informations du player du genre le contenu du message, son username...
     */
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
                    //Champ des autres utilisateurs
                    Text username = new Text(message.getSenderString());
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

    /**Méthode qui envoie le message
     * @throws IOException
     */
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

    @FXML
    public void enterMessage(){
        champMessage.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    onSendData();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Stage stage = (Stage) anchorPane.getScene().getWindow();
        //stage.setOnCloseRequest(event -> this.client.disconnectedServer());
        map.initMap();
        setPlayerInterface();
        setPanelGame();
    }

    @FXML
    private void handleMovePerso(KeyEvent e) {
        KeyCode code = e.getCode();

        int x = player.getX();
        int y = player.getY();


        switch (code) {
            case Z:
                if(y > 0 && (map.getMapArray()[y-1][x].equals("f") || map.getMapArray()[y-1][x].equals("c"))){
                    map.setMapTile(x, y, pastTile);
                    pastTile = map.getMapTile(x, y-1);

                    switch (this.player.getPerso()){
                        case 0:
                            if(map.getMapArray()[y-1][x].equals("c")){
                                if(map.getMapArray()[y-1][x-1].equals("t")) {
                                    playerImage.setImage(new Image("0left1.png"));
                                }
                                else{
                                    playerImage.setImage(new Image("0right1.png"));
                                }
                            }
                            else{
                                playerImage.setImage(new Image("0up1.png"));
                            }
                            break;
                        case 1:
                            if(map.getMapArray()[y-1][x].equals("c")){
                                if(map.getMapArray()[y-1][x-1].equals("t")) {
                                    playerImage.setImage(new Image("1left1.png"));
                                }
                                else{
                                    playerImage.setImage(new Image("1right1.png"));
                                }
                            }
                            else{
                                playerImage.setImage(new Image("1up1.png"));
                            }
                            break;
                        case 2:
                            if(map.getMapArray()[y-1][x].equals("c")){
                                if(map.getMapArray()[y-1][x-1].equals("t")) {
                                    playerImage.setImage(new Image("2left1.png"));
                                }
                                else{
                                    playerImage.setImage(new Image("2right1.png"));
                                }
                            }
                            else{
                                playerImage.setImage(new Image("2up1.png"));
                            }
                            break;
                        case 3:
                            if(map.getMapArray()[y-1][x].equals("c")){
                                if(map.getMapArray()[y-1][x-1].equals("t")) {
                                    playerImage.setImage(new Image("3left1.png"));
                                }
                                else{
                                    playerImage.setImage(new Image("3right1.png"));
                                }
                            }
                            else{
                                playerImage.setImage(new Image("3up1.png"));
                            }
                            break;
                        default:
                            break;
                    }
                    map.setMapTile(x, y-1, "p");

                    player.moveUp();
                }
                break;
            case S:
                if(y < map.getMapArray().length - 1 && (map.getMapArray()[y+1][x].equals("f") || map.getMapArray()[y+1][x].equals("c"))) {
                    map.setMapTile(x, y, pastTile);
                    pastTile = map.getMapTile(x, y+1);

                    switch (this.player.getPerso()){
                        case 0:
                            if(map.getMapArray()[y+1][x].equals("c")){
                                if(map.getMapArray()[y+1][x-1].equals("t")) {
                                    playerImage.setImage(new Image("0left1.png"));
                                }
                                else{
                                    playerImage.setImage(new Image("0right1.png"));
                                }
                            }
                            else{
                                playerImage.setImage(new Image("0down1.png"));
                            }
                            break;
                        case 1:
                            var ok = map.getMapArray()[y+1][x];
                            if(map.getMapArray()[y+1][x].equals("c")){
                                if(map.getMapArray()[y+1][x-1].equals("t")) {
                                    playerImage.setImage(new Image("1left1.png"));
                                }
                                else{
                                    playerImage.setImage(new Image("1right1.png"));
                                }
                            }
                            else{
                                playerImage.setImage(new Image("1down1.png"));
                            }
                            break;
                        case 2:
                            if(map.getMapArray()[y+1][x].equals("c")){
                                if(map.getMapArray()[y+1][x-1].equals("t")) {
                                    playerImage.setImage(new Image("2left1.png"));
                                }
                                else{
                                    playerImage.setImage(new Image("2right1.png"));
                                }
                            }
                            else{
                                playerImage.setImage(new Image("2down1.png"));
                            }
                            break;
                        case 3:
                            if(map.getMapArray()[y+1][x].equals("c")){
                                if(map.getMapArray()[y+1][x-1].equals("t")) {
                                    playerImage.setImage(new Image("3left1.png"));
                                }
                                else{
                                    playerImage.setImage(new Image("3right1.png"));
                                }
                            }
                            else{
                                playerImage.setImage(new Image("3down1.png"));
                            }
                            break;
                        default:
                            break;
                    }
                    map.setMapTile(x, y+1, "p");

                    player.moveDown();
                }
                break;
            case Q:
                if(x > 0 && (map.getMapArray()[y][x-1].equals("f") || map.getMapArray()[y][x-1].equals("c"))) {
                    map.setMapTile(x, y, pastTile);
                    pastTile = map.getMapTile(x-1, y);

                    switch (this.player.getPerso()){
                        case 0:
                            playerImage.setImage(new Image("0left1.png"));
                            break;
                        case 1:
                            playerImage.setImage(new Image("1left1.png"));
                            break;
                        case 2:
                            playerImage.setImage(new Image("2left1.png"));
                            break;
                        case 3:
                            playerImage.setImage(new Image("3left1.png"));
                            break;
                        default:
                            break;
                    }

                    map.setMapTile(x-1, y, "p");

                    player.moveLeft();
                }
                break;
            case D:
                if(x < map.getMapArray()[0].length - 1 && (map.getMapArray()[y][x+1].equals("f") || map.getMapArray()[y][x+1].equals("c"))) {
                    map.setMapTile(x, y, pastTile);
                    pastTile = map.getMapTile(x+1, y);

                    switch (this.player.getPerso()){
                        case 0:
                            playerImage.setImage(new Image("0right1.png"));
                            break;
                        case 1:
                            playerImage.setImage(new Image("1right1.png"));
                            break;
                        case 2:
                            playerImage.setImage(new Image("2right1.png"));
                            break;
                        case 3:
                            playerImage.setImage(new Image("3right1.png"));
                            break;
                        default:
                            break;
                    }
                    map.setMapTile(x+1, y, "p");

                    player.moveRight();
                }
                break;
        }
        gridMap.getChildren().remove(playerImage);
        gridMap.add(playerImage, player.getX(), player.getY());
    }

    private void setPanelGame() {
        playerImage = new ImageView();

        switch (this.player.getPerso()){
            case 0:
                playerImage.setImage(new Image("0down1.png"));
                break;
            case 1:
                playerImage.setImage(new Image("1down1.png"));
                break;
            case 2:
                playerImage.setImage(new Image("2down1.png"));
                break;
            case 3:
                playerImage.setImage(new Image("3down1.png"));
                break;
            default:
                break;
        }
        gridMap.add(playerImage, player.getX(), player.getY());



    // Loop through the map and display the sprite at each "c" position
        for (int i = 0; i <  map.getMapArray().length; i++) {
            for (int j = 0; j <  map.getMapArray()[i].length; j++) {
                if (map.getMapArray()[i][j].equals("t")) {
                    // Add the sprite view to the game pane
                    tableImage = new ImageView();
                    tableImage.setImage(new Image("table.png"));
                    gridMap.add(tableImage,j,i);
                }
                if (map.getMapArray()[i][j].equals("c")) {
                    // Add the sprite view to the game pane
                    chairImage = new ImageView();
                    chairImage.setImage(new Image("chair.png"));
                    gridMap.add(chairImage,j,i);
                }
            }
        }
    }




    private void setPlayerInterface(){
                Player playerSingleton = PlayerSingleton.getInstance().getObject();
                this.player = playerSingleton;

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
