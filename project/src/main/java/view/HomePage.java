package view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class HomePage {
    public void start(Stage stage){
        try{
            stage.close();
            stage.setScene(null);
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/resources/HomePage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
