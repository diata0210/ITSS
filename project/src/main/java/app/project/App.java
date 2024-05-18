package app.project;

import app.db.DatabaseConnection;
import app.models.SiteOrder;
import app.repositories.OrderSiteRepository;
import app.repositories.implement.OrderSiteRepositoryImp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Parent root = fxmlLoader.load();
        Stage homeStage = new Stage();
        homeStage.setScene(new Scene(root));
        homeStage.show();

        Connection connection = DatabaseConnection.getConnection();

    }

    public static void main(String[] args) {
        launch();
    }
}