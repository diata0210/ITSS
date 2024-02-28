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
  public void start(Stage primaryStage) {
    try {
      URL url = new File("/java/homework1/dang-thai-tuan-210907/src/javafx/CalculateView.fxml").toURI().toURL();
      Parent root = FXMLLoader.load(url);
      primaryStage.setTitle("Calculator");
      primaryStage.setScene(new Scene(root));
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch();
  }
}
