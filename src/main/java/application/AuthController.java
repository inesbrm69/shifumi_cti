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

    @FXML
    public void userLogIn(ActionEvent e) throws IOException {
        AnchorPane authPage = FXMLLoader.load(Jeu.class.getResource("pageAuthentification.fxml"));
        Scene scene = new Scene(authPage);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

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

        Player player = connectionDB.getPlayerByUsername(username.getText().toString());

        String passwordDb = null;

        if(player != null){
            passwordDb = player.getPassword();
        }

        if(username.getText().toString().equals(player.getUsername()) && password.getText().toString().equals(passwordDb)){
            return true;
        }
        else if(username.getText().isEmpty() || password.getText().isEmpty()){
            errorMsg.setText("Please enter your username and password");
            return false;
        }
        else{
            errorMsg.setText("Some of your info isn't correct. Please try again.");
            return false;
        }
    }

    @FXML
    public void userSignIn(ActionEvent e) throws IOException {
        AnchorPane authPage = FXMLLoader.load(Jeu.class.getResource("pageAuthentification.fxml"));
        Scene scene = new Scene(authPage);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
