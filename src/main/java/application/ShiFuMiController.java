package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ShiFuMiController {

    @FXML

    public void nextPage(ActionEvent e) throws IOException {
        AnchorPane authPage = FXMLLoader.load(Jeu.class.getResource("pageAuthentification.fxml"));
        Scene scene = new Scene(authPage);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }
}
