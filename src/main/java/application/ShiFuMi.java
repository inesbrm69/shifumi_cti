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

public class ShiFuMi extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        /*List<String> parameters = this.getParameters().getRaw();

        String address = parameters.get(0);
        int port =  Integer.parseInt(parameters.get(1));

        Client client = new Client(address,port);
        final FXMLLoader FXMloader = new FXMLLoader(Jeu.class.getResource("pageStart.fxml"));
        Scene scene = new Scene(FXMloader.load());
        stage.show();
        *//*JeuController view = FXMloader.getController();*//*
        *//*client.setView(view);
        view.setClient(client);*//*
        stage.setTitle("DevOps Chat");
        stage.setScene(scene);*/


        AnchorPane pageStart = FXMLLoader.load(ShiFuMi.class.getResource("pageStart.fxml"));
        Scene page_Start = new Scene(pageStart);
        stage.setScene(page_Start);
        stage.setTitle("ShiFuMi.CTI");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(ShiFuMi.class, args);
    }
}