package application;

import client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Jeu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        /*List<String> parameters = this.getParameters().getRaw();

        String address = parameters.get(0);
        int port =  Integer.parseInt(parameters.get(1));

        Client client = new Client(address,port);
        final FXMLLoader FXMloader = new FXMLLoader(Jeu.class.getResource("PageGame.fxml"));
        Scene scene = new Scene(FXMloader.load());
        JeuController view = FXMloader.getController();
        client.setView(view);
        view.setClient(client);
        stage.setTitle("DevOps Chat");
        stage.setScene(scene);
        stage.show();*/
    }

    public static void main(String[] args){
        launch(Jeu.class,args);
    }
}
