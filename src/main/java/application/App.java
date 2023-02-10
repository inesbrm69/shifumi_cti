package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application{
    public static ShiFuMi scene;
    public static Stage stage;
    public static void main( String[] args )
    {
        Application.launch(App.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pageStart = FXMLLoader.load(App.class.getResource("pageStart.fxml"));
        Scene page_Start = new Scene(pageStart);
        stage.setScene(page_Start);
        stage.setTitle("ShiFuMi.CTI");
        stage.show();
    }
}
