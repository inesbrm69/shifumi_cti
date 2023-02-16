package application;

import common.Player;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.stage.Stage;

import java.io.IOException;

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

    @FXML
    public void userLogIn(ActionEvent e) throws IOException {
        //AnchorPane authPage = FXMLLoader.load(Jeu.class.getResource("pageAuthentification.fxml"));
        //Scene scene = new Scene(authPage);
        //Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //stage.setScene(scene);
        //stage.show();

        boolean canLogIn = checkLogIn();

        if(canLogIn){
            AnchorPane gamePage = FXMLLoader.load(Jeu.class.getResource("PageGame.fxml"));
            Scene sceneGame = new Scene(gamePage);
            Stage stageGame = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stageGame.setScene(sceneGame);
            stageGame.show();
        }
    }

    private boolean checkLogIn() throws IOException {
        //garder le try/catch ?
        try {
            Player player = connectionDB.getPlayerByUsername(username.getText().toString());

            String passwordDb = null;

            if (player != null) {
                passwordDb = player.getPassword();
            }

            if (username.getText().toString().equals(player.getUsername()) && password.getText().toString().equals(passwordDb)) {
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
        }
        else{
            errorMsg.setText("There was an error, please try again.");
        }
    }

    private boolean checkSignIn() throws IOException{
        //garder le try/catch ?
        try{
            Player player = connectionDB.getPlayerByUsername(username.getText().toString());

            if (player == null) {
                boolean playerAdded = connectionDB.addNewPlayer(new Player(0, username.getText().toString(), password.getText().toString(), 0, choicePerso ));
                return playerAdded;
            }
            else if(player != null){
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
                //-fx-background-color: none;
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
                break;
        }
        setChoicePerso(choice);
    }

}
