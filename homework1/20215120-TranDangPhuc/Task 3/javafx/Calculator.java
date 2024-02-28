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
      URL url = new File("/Users/tdphuc0110/Desktop/Tài liệu chuyên ngành/ITSS/Group/2023.2-147730-08/homework1/tran-dang-phuc-215120/Task 3/javafx/CalculateView.fxml").toURI().toURL();
      Parent root = FXMLLoader.load(url);
      primaryStage.setTitle("Calculator");
      primaryStage.setScene(new Scene(root, 400, 500));
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch();
  }
}
