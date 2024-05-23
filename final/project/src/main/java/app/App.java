package app;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.fxml.*;
import java.util.Objects;

public final class App extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/project/WHLayout.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String []args){
        launch(args);
    }
}
