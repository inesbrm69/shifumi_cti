package application.controller;

import application.Jeu;
import application.connectionDB;
import client.Client;
import common.ClientSingleton;
import common.Message;
import common.Player;
import common.PlayerSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import org.apache.commons.codec.binary.Hex;

public class AuthController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button logIn;

    @FXML
    private Button signIn;

    @FXML
    private Button imageZero;

    @FXML
    private Button imageOne;

    @FXML
    private Button imageTwo;

    @FXML
    private Button imageThree;

    @FXML
    private Label errorMsg;

    private int choicePerso = 0;

    private Client client;

    private Player player = new Player(0, null, null, 0, 0);

    public TextField getUsername() {
        return username;
    }

    public PasswordField getPassword() {
        return password;
    }

    public Button getLogIn() {
        return logIn;
    }

    public Button getSignIn() {
        return signIn;
    }

    public Button getImageZero() {
        return imageZero;
    }

    public Button getImageOne() {
        return imageOne;
    }

    public Button getImageTwo() {
        return imageTwo;
    }

    public Button getImageThree() {
        return imageThree;
    }

    public Label getErrorMsg() {
        return errorMsg;
    }

    public int getChoicePerso() {
        return choicePerso;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }

    public void setLogIn(Button logIn) {
        this.logIn = logIn;
    }

    public void setSignIn(Button signIn) {
        this.signIn = signIn;
    }

    public void setImageZero(Button imageZero) {
        this.imageZero = imageZero;
    }

    public void setImageOne(Button imageOne) {
        this.imageOne = imageOne;
    }

    public void setImageTwo(Button imageTwo) {
        this.imageTwo = imageTwo;
    }

    public void setImageThree(Button imageThree) {
        this.imageThree = imageThree;
    }

    public void setErrorMsg(Label errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setChoicePerso(int choicePerso) {
        this.choicePerso = choicePerso;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    /** M??thode qui v??rifie si l'utilisateur peut se connecter. Si c'est le cas, cr??e un client et lance la page de jeu.
     * @param e l'??v??nement de clic sur le bouton de connexion.
     * @throws IOException
     */
    @FXML
    public void userLogIn(ActionEvent e) throws IOException {
        boolean canLogIn = checkLogIn();

        if(canLogIn){
            this.player.setPerso(choicePerso);
            PlayerSingleton.getInstance().setObject(this.player);
            FXMLLoader loader = new FXMLLoader(Jeu.class.getResource("PageGame.fxml"));
            AnchorPane gamePage = loader.load();
            Scene sceneGame = new Scene(gamePage);
            Stage stageGame = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stageGame.setScene(sceneGame);
            stageGame.setResizable(false);
            String[] properties = getServerProperties();
            Client client = new Client(properties[0], Integer.parseInt(properties[1]), this.player.getUsername());
            client.setPlayerId(this.player.getId());
            client.setPlayerUsername(this.player.getUsername());
            client.setPlayerScore(this.player.getScore());
            this.setClient(client);
            client.setAuthView(this);
            client.setJeuView(loader.getController());
            ClientSingleton.getInstance().setObject(this.client);
            //this.client.sendMessage(new Message(player));
            stageGame.show();
        }
    }

    /** M??thode qui lit les propri??t??s d'un serveur ?? partir d'un fichier de configuration.
     * @return un tableau de deux cha??nes de caract??res, contenant l'adresse IP et le port du serveur.
     *
     */
    public String[] getServerProperties(){
        try {
            Properties prop = new Properties();
            FileInputStream input = null;
            input = new FileInputStream("src/main/resources/application/config.properties");
            prop.load(input);
            String address = prop.getProperty("address");
            String port = prop.getProperty("port");

            String[] properties = new String[2];

            properties[0] = address;
            properties[1] = port;

            return properties;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** M??thode qui v??rifie avec la base de donn??es que le joueur est bien cr???? et r??cup??re les informations dans la base de donn??e du user
     * @return true si les informations de connexion sont valides, false sinon.
     * @throws IOException
     */
    private boolean checkLogIn() throws IOException {
        try {
            Player player = connectionDB.getPlayerByUsername(username.getText().toString());

            String passwordDb = null;

            if (player != null) {
                passwordDb = player.getPassword();
            }
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] passwordHash = digest.digest(password.getText().getBytes(StandardCharsets.UTF_8));
            String passwordHashStr = new String(Hex.encodeHex(passwordHash));
            if (username.getText().toString().equals(player.getUsername()) && passwordHashStr.equals(passwordDb) && player.getId() != 0) {
                var persoChanged = connectionDB.changePerso(player, getChoicePerso());
                if(!persoChanged){
                    errorMsg.setText("There was an error with your character, try again.");
                    return false;
                }
                this.player.setId(player.getId());
                this.player.setPerso(player.getPerso());
                this.player.setScore(player.getScore());
                this.player.setPassword(player.getPassword());
                this.player.setUsername(player.getUsername());
                return true;
            } else if (username.getText().isEmpty() || password.getText().isEmpty()) {
                errorMsg.setText("Please enter your username and password");
                return false;
            } else {
                errorMsg.setText("Some of your info isn't correct. Please try again.");
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /** M??thode qui ??crit un message qui confirme la cr??ation de l'utilisateur et initialise les champs user et password a 'null'
     * @param e
     * @throws IOException
     */
    @FXML
    public void userSignIn(ActionEvent e) throws IOException {
        boolean signedIn = checkSignIn();
        if(signedIn){
            errorMsg.setText("Your account was created. Please log in.");
            username.setText("");
            password.setText("");
        }
    }

    /** M??thode qui v??rifie si l'utilisateur peut cr??er un nouveau compte.
     * @return boolean true si le compte a ??t?? cr???? avec succ??s, false sinon.
     * @throws IOException
     */
    private boolean checkSignIn() throws IOException{
        try{
            Player player = connectionDB.getPlayerByUsername(username.getText().toString());

            if (player.getId() == 0) {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] passwordHash = digest.digest(password.getText().getBytes(StandardCharsets.UTF_8));
                String passwordHashStr = new String(Hex.encodeHex(passwordHash));
                boolean playerAdded = connectionDB.addNewPlayer(new Player(0, username.getText().toString(), passwordHashStr, 0, choicePerso ));
                return playerAdded;
            }
            else if(player.getId() != 0){
                errorMsg.setText("This username is already taken.");
                return false;
            }
            else{
                errorMsg.setText("There was an error, please try again.");
                return false;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void choosePersoZero(){
        choosePerso(0);
    }

    public void choosePersoOne(){
        choosePerso(1);
    }

    public void choosePersoTwo(){
        choosePerso(2);
    }

    public void choosePersoThree(){
        choosePerso(3);
    }

    /** M??thode appel??e lorsqu'un utilisateur clique sur l'une des images de personnage disponibles pour choisir son personnage.
     * Elle met en ??vidence l'image du personnage s??lectionn?? en ajoutant une bordure verte autour de celle-ci,
     * tout en enlevant les bordures des autres images.
     * @param choice : le choix du perso
     */
    public void choosePerso(int choice){
        //ajouter choosePerso sur les boutons dans le fichier fxml en format choosePerso(0) choosePerso(1)...
        switch (choice){
            case 0:
                if(getChoicePerso() != 0){
                    imageZero.setStyle("-fx-background-color: none;" +
                            " -fx-border-style: solid inside;" +
                            "-fx-border-width: 2; " +
                            "-fx-border-radius: 5; " +
                            "-fx-border-color:  #21EF80;");
                    imageOne.setStyle("-fx-background-color: none;");
                    imageTwo.setStyle("-fx-background-color: none;");
                    imageThree.setStyle("-fx-background-color: none;");
                }
                setChoicePerso(choice);
                break;
            case 1:
                imageOne.setStyle("-fx-background-color: none;" +
                        " -fx-border-style: solid inside;" +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 5; " +
                        "-fx-border-color:  #21EF80;");
                imageZero.setStyle("-fx-background-color: none;");
                imageTwo.setStyle("-fx-background-color: none;");
                imageThree.setStyle("-fx-background-color: none;");
                setChoicePerso(choice);
                break;
            case 2:
                imageTwo.setStyle("-fx-background-color: none;" +
                        " -fx-border-style: solid inside;" +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 5; " +
                        "-fx-border-color:  #21EF80;");
                imageZero.setStyle("-fx-background-color: none;");
                imageOne.setStyle("-fx-background-color: none;");
                imageThree.setStyle("-fx-background-color: none;");
                setChoicePerso(choice);
                break;
            case 3:
                imageThree.setStyle("-fx-background-color: none;" +
                        " -fx-border-style: solid inside;" +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 5; " +
                        "-fx-border-color:  #21EF80;");
                imageZero.setStyle("-fx-background-color: none;");
                imageOne.setStyle("-fx-background-color: none;");
                imageTwo.setStyle("-fx-background-color: none;");
                setChoicePerso(choice);
                break;
            default:
                imageZero.setStyle("-fx-background-color: none;");
                imageOne.setStyle("-fx-background-color: none;");
                imageTwo.setStyle("-fx-background-color: none;");
                imageThree.setStyle("-fx-background-color: none;");
                break;
        }
    }
}
