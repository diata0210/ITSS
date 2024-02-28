package javafx;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Calculator extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            String fxmlPath = "/javafx/CalculatorView.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            primaryStage.setTitle("Calculator");
            primaryStage.setScene(new Scene(root,548,357));
            primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();

    }
}
