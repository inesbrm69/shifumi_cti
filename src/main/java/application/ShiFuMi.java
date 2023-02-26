package application;

import client.Client;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.ConnectedClient;
import server.Server;

import java.io.IOException;
import java.util.List;
import java.net.URL;
import java.util.Optional;

public class ShiFuMi extends Application{

    /**Méthode qui lance la page de l'application
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pageStart = FXMLLoader.load(ShiFuMi.class.getResource("pageStart.fxml"));
        Scene page_Start = new Scene(pageStart);
        stage.setScene(page_Start);
        stage.setTitle("ShiFuMi.CTI");
        stage.setResizable(false); // Rend la fenêtre non redimensionnable
        stage.show();
    }
}