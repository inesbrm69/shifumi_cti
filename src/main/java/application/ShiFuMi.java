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
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import server.ConnectedClient;
import server.Server;
import javafx.scene.media.Media;

import java.io.File;
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

        // Crée un objet Media pour représenter votre fichier de musique
        Media sound = new Media(new File("src/main/resources/application/musique/musique.mp3").toURI().toString());

        // Crée un objet MediaPlayer en utilisant votre objet Media
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        mediaPlayer.setCycleCount(5);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });

        // Joue votre musique
        mediaPlayer.play();

        // Affiche la fenêtre de l'application
        stage.show();
    }


}