package controller;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class LoginController {

    @FXML
    private TextField paswordField;

    @FXML
    private TextField usernameField;

    @FXML
    void clickedForgotPassword(MouseEvent event) {

    }
    private AnchorPane anchorPane;
    @FXML
    void onLogin(ActionEvent event) {
        Stage loginStage = (Stage) usernameField.getScene().getWindow();
        loginStage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomePage.fxml"));
            Parent root = loader.load();
            Stage homeStage = new Stage();
            homeStage.setScene(new Scene(root));
            homeStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
