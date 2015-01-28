package fr.istic.m2gl.oci.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        MyParser parser = new MyParser();
        parser.parser();
        
        String fxmlFile = "/fxml/fxml.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode);
        stage.setTitle("Workflow");
        stage.setScene(scene);
        stage.show();
    }
}
