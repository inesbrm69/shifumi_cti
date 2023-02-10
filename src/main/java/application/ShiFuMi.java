package application;

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

import java.io.IOException;

public class ShiFuMi extends Application{


    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pageStart = FXMLLoader.load(ShiFuMi.class.getResource("pageStart.fxml"));
        Scene page_Start = new Scene(pageStart);
        stage.setScene(page_Start);
        stage.setTitle("ShiFuMi.CTI");
        stage.show();
    }
}