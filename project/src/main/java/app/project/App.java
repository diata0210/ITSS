package app.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Parent root = fxmlLoader.load();
        Stage homeStage = new Stage();
        homeStage.setScene(new Scene(root));
        homeStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}