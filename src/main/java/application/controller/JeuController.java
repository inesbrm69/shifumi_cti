package application.controller;

import client.Client;
/*import client.MainClient;*/
import common.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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



    /**
     * Méthode qui fait le style de message (cad que quand c'est le user qui envoie le message, il est en blanc et quand c'est les autres c'est en rouge
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

    /**
     * Méthode  lorsqu'un utilisateur souhaite envoyer un message dans la conversation.
     * Elle vérifie si le champ de saisie de message n'est pas vide, crée un nouvel objet Message avec le contenu du message et l'identifiant
     * de l'utilisateur, envoie le message au serveur via le client, puis efface le champ de saisie de message.
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

    /**
     * Attache un événement pour déclencher l'envoi d'un message lorsque l'utilisateur appuie sur la touche "Entrée" dans le champ de message.
     * Le message sera envoyé en appelant la méthode onSendData().
     */
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

    /**Méthode qui initialise l'interface utilisateur du joueur et le panneau de jeu.
     * Elle appelle également la méthode initMap() pour initialiser la carte du jeu.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        map.initMap();
        setPlayerInterface();
        setPanelGame();
    }


    /**
     * Méthode gère les déplacements du personnage en réponse à un événement de touche de clavier.
     * Les touches autorisées sont Z, S, Q et D qui correspondent respectivement aux déplacements vers le haut, le bas, la gauche et la droite.
     * Si le personnage peut se déplacer dans la direction donnée par la touche pressée, alors la méthode met à jour la position du personnage sur la carte et l'affiche à la nouvelle position.
     * Cette méthode affiche également la carte mise à jour dans la console à chaque déplacement.
     * @param e un objet KeyEvent qui représente l'événement de touche de clavier à gérer.
     */
    @FXML
    private void handleMovePerso(KeyEvent e) {
        this.client = ClientSingleton.getInstance().getObject();
        KeyCode code = e.getCode();

        int x = player.getX();
        int y = player.getY();

        switch (code) {
            case Z:
                if(y > 0 && (map.getMapArray()[y-1][x].equals("f") || map.getMapArray()[y-1][x].equals("c"))){
                    map.setMapTile(x, y, pastTile);
                    String tileToSend = pastTile;

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

                    pastTile = map.getMapTile(x, y-1);
                    map.setMapTile(x, y-1, "p");

                    player.moveUp();

                    try {
                        PlayerCoords playerCoords = new PlayerCoords(player, x, y, tileToSend);
                        this.client.sendCoords(playerCoords);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case S:
                if(y < map.getMapArray().length - 1 && (map.getMapArray()[y+1][x].equals("f") || map.getMapArray()[y+1][x].equals("c"))) {
                    map.setMapTile(x, y, pastTile);
                    String tileToSend = pastTile;

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

                    pastTile = map.getMapTile(x, y+1);
                    map.setMapTile(x, y+1, "p");

                    player.moveDown();

                    try {
                        PlayerCoords playerCoords = new PlayerCoords(player, x, y, tileToSend);
                        this.client.sendCoords(playerCoords);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case Q:
                if(x > 0 && (map.getMapArray()[y][x-1].equals("f") || map.getMapArray()[y][x-1].equals("c"))) {
                    map.setMapTile(x, y, pastTile);
                    String tileToSend = pastTile;

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

                    pastTile = map.getMapTile(x-1, y);
                    map.setMapTile(x-1, y, "p");

                    player.moveLeft();

                    try {
                        PlayerCoords playerCoords = new PlayerCoords(player, x, y, tileToSend);
                        this.client.sendCoords(playerCoords);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case D:
                if(x < map.getMapArray()[0].length - 1 && (map.getMapArray()[y][x+1].equals("f") || map.getMapArray()[y][x+1].equals("c"))) {
                    map.setMapTile(x, y, pastTile);
                    String tileToSend = pastTile;

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

                    pastTile = map.getMapTile(x+1, y);
                    map.setMapTile(x+1, y, "p");

                    player.moveRight();
                    try {
                        PlayerCoords playerCoords = new PlayerCoords(player, x, y, tileToSend);
                        this.client.sendCoords(playerCoords);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
        }

        // Obtient les nouvelles coordonnées du joueur
        int newX = player.getX();
        int newY = player.getY();

        // Supprime l'image précédente du joueur
        gridMap.getChildren().remove(playerImage);

        // Ajoute la nouvelle image du joueur aux nouvelles coordonnées
        gridMap.add(playerImage, newX, newY);

        // Supprime le label précédent s'il existe
        gridMap.getChildren().remove(usernameLabel);

        // Ajoute le nouveau label avec le nom d'utilisateur aux nouvelles coordonnées,
        // mais avec un décalage vertical pour le placer au-dessus du joueur
        Label username = new Label(player.getUsername());
        username.setStyle("-fx-font-size: 14px;" +
                "-fx-font-weight: bold;");
        GridPane.setHalignment(username, HPos.LEFT); // centre le texte horizontalement
        GridPane.setValignment(username, VPos.BOTTOM); // place le texte en haut de la cellule
        gridMap.add(username, newX, newY-1);
        usernameLabel = username;

    }

    /**Méthode qui affiche les autres joueurs sur la carte.
     * Cette méthode utilise une tâche de fond pour afficher les joueurs sur la carte dans l'interface utilisateur.
     * La méthode utilise une image et un label pour représenter chaque joueur. Le label affiche le nom d'utilisateur du joueur.
     * @param playerCoords les coordonnées du joueur à afficher sur la carte
     */
    public void printOthers(PlayerCoords playerCoords){
        //Platform.runLater() parce que sinon l'action n'est pas faite sur le thread de JavaFX
        Platform.runLater(() -> {
            ImageView otherPlayer = new ImageView();
            Label username = new Label(playerCoords.getPlayer().getUsername());
            switch (playerCoords.getPlayer().getPerso()) {
                case 0:
                    otherPlayer.setImage(new Image("0down1.png"));
                    break;
                case 1:
                    otherPlayer.setImage(new Image("1down1.png"));
                    break;
                case 2:
                    otherPlayer.setImage(new Image("2down1.png"));
                    break;
                case 3:
                    otherPlayer.setImage(new Image("3down1.png"));
                    break;
                default:
                    break;

            }

            /*Node node = null;
            for (Node child : gridMap.getChildren()) {
                if (GridPane.getColumnIndex(child) == playerCoords.getOldX() && GridPane.getRowIndex(child) == playerCoords.getOldY()) {
                    node = child;
                    break;
                }
            }
            if (node != null) {
                gridMap.getChildren().remove(node);
            }*/

            map.setMapTile(playerCoords.getOldX(), playerCoords.getOldY(), playerCoords.getPastTile());
            map.setMapTile(playerCoords.getPlayer().getX(), playerCoords.getPlayer().getY(), "o");
            gridMap.add(otherPlayer, playerCoords.getPlayer().getX(), playerCoords.getPlayer().getY());

            // Obtient les nouvelles coordonnées du joueur
            int newX = playerCoords.getPlayer().getX();
            int newY = playerCoords.getPlayer().getY();

            // Supprime l'image précédente du joueur
            gridMap.getChildren().remove(otherPlayer);

            // Ajoute la nouvelle image du joueur aux nouvelles coordonnées
            gridMap.add(otherPlayer, newX, newY);
            // Supprime le label précédent s'il existe
            Label usernameOthers = new Label();
            gridMap.getChildren().remove(usernameOthers);

            // Ajoute le nouveau label avec le nom d'utilisateur aux nouvelles coordonnées,
            // mais avec un décalage vertical pour le placer au-dessus du joueur
            username.setStyle("-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;");
            GridPane.setHalignment(username, HPos.LEFT); // centre le texte horizontalement
            GridPane.setValignment(username, VPos.BOTTOM); // place le texte en haut de la cellule
            gridMap.add(username, newX, newY-1);
            usernameOthers = username;


        });
    }


    /**
     * Méthode qui initialise l'image du joueur et l'ajoute à la grille de la carte de jeu.
     * L'image utilisée pour représenter le joueur est chargée à partir du fichier "sprite0.png".
     * L'image est placée à la position initiale du joueur (8,7) dans la grille de la carte de jeu.
     */
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
        // Obtient les nouvelles coordonnées du joueur
        int newX = player.getX();
        int newY = player.getY();

        // Supprime l'image précédente du joueur
        gridMap.getChildren().remove(playerImage);

        // Ajoute la nouvelle image du joueur aux nouvelles coordonnées
        gridMap.add(playerImage, 8, 7);
        // Supprime le label précédent s'il existe
        gridMap.getChildren().remove(usernameLabel);

        // Ajoute le nouveau label avec le nom d'utilisateur aux nouvelles coordonnées,
        // mais avec un décalage vertical pour le placer au-dessus du joueur
        Label username = new Label(player.getUsername());
        username.setStyle("-fx-font-size: 14px;" +
                "-fx-font-weight: bold;");
        GridPane.setHalignment(username, HPos.LEFT); // centre le texte horizontalement
        GridPane.setValignment(username, VPos.BOTTOM); // place le texte en haut de la cellule
        gridMap.add(username, newX, newY-1);
        usernameLabel = username;
    }


    /**
     * Met à jour l'interface graphique du joueur avec son image de personnage, son nom d'utilisateur
     * et son score.
     */
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
