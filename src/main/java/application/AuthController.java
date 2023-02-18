package application;

import client.Client;
import common.ClientSingleton;
import common.Message;
import common.Player;
import common.PlayerSingleton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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



    @FXML
    public void userLogIn(ActionEvent e) throws IOException {
        boolean canLogIn = checkLogIn();

        if(canLogIn){
            AnchorPane gamePage = FXMLLoader.load(Jeu.class.getResource("PageGame.fxml"));
            Scene sceneGame = new Scene(gamePage);
            Stage stageGame = (Stage) ((Node) e.getSource()).getScene().getWindow();
            //stageGame.setUserData(this.player);
            PlayerSingleton.getInstance().setObject(this.player);
            stageGame.setScene(sceneGame);
            String[] properties = getServerProperties();
            Client client = new Client(properties[0], Integer.parseInt(properties[1]));
            this.setClient(client);
            client.setView(this);
            ClientSingleton.getInstance().setObject(this.client);
            stageGame.show();
        }
    }

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

    private boolean checkLogIn() throws IOException {
        try {
            Player player = connectionDB.getPlayerByUsername(username.getText().toString());

            String passwordDb = null;

            if (player != null) {
                passwordDb = player.getPassword();
            }

            if (username.getText().toString().equals(player.getUsername()) && password.getText().toString().equals(passwordDb)) {
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

    @FXML
    public void userSignIn(ActionEvent e) throws IOException {
        boolean signedIn = checkSignIn();
        if(signedIn){
            errorMsg.setText("Your account was created. Please log in.");
            username.setText("");
            password.setText("");
            choosePerso(999);
        }
        else{
            errorMsg.setText("There was an error, please try again.");
        }
    }

    private boolean checkSignIn() throws IOException{
        try{
            Player player = connectionDB.getPlayerByUsername(username.getText().toString());

            if (player.getId() == 0) {
                boolean playerAdded = connectionDB.addNewPlayer(new Player(0, username.getText().toString(), password.getText().toString(), 0, choicePerso ));
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

    public void choosePerso(int choice){

        String styleWithBorder = "-fx-background-color: none;" +
                " -fx-border-style: solid inside;" +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 5; " +
                "-fx-border-color:  #21EF80;";

        String styleWithoutBorder = "-fx-background-color: none;";

        switch (choice){
            case 0:
                if(getChoicePerso() != 0){
                    imageZero.setStyle(styleWithBorder);

                    imageOne.setStyle(styleWithoutBorder);
                    imageTwo.setStyle(styleWithoutBorder);
                    imageThree.setStyle(styleWithoutBorder);
                }
                setChoicePerso(choice);
                break;
            case 1:
                imageOne.setStyle(styleWithBorder);

                imageZero.setStyle(styleWithoutBorder);
                imageTwo.setStyle(styleWithoutBorder);
                imageThree.setStyle(styleWithoutBorder);
                setChoicePerso(choice);
                break;
            case 2:
                imageTwo.setStyle(styleWithBorder);

                imageZero.setStyle(styleWithoutBorder);
                imageOne.setStyle(styleWithoutBorder);
                imageThree.setStyle(styleWithoutBorder);
                setChoicePerso(choice);
                break;
            case 3:
                imageThree.setStyle(styleWithBorder);

                imageZero.setStyle(styleWithoutBorder);
                imageOne.setStyle(styleWithoutBorder);
                imageTwo.setStyle(styleWithoutBorder);
                setChoicePerso(choice);
                break;
            default:
                imageZero.setStyle(styleWithBorder);

                imageOne.setStyle(styleWithoutBorder);
                imageTwo.setStyle(styleWithoutBorder);
                imageThree.setStyle(styleWithoutBorder);
                break;
        }
    }
}
