module com.example.shifumi_cti {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires commons.codec;
    requires javafx.media;

    /*opens com.example.shifumi_cti to javafx.fxml;
    exports com.example.shifumi_cti;
*/
    opens application to javafx.fxml;
    exports application;
    opens application.controller to javafx.fxml;
    exports application.controller;
}