package application;

import client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Auth extends Application {

    @Override
    public void start(Stage stage) throws Exception {

    }

    public Client launchClient(){
        /*App app = new App();
        Parameters parameters = this.getParameters();
        List<String> para = parameters.getRaw();

        String address = para.get(0);
        int port =  Integer.parseInt(para.get(1));*/
        Client client = new Client("127.0.0.1",50000);

        return client;
    }

}
