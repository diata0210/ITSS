package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import app.models.LoginModel;
public class LoginController {
    @FXML
    private TextField paswordField;

    @FXML
    private TextField usernameField;

    @FXML
    void clickedForgotPassword(MouseEvent event) {

    }
    @FXML
    void onLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = paswordField.getText();
        LoginModel login = new LoginModel();
        Stage loginStage = (Stage) usernameField.getScene().getWindow();
        if(login.login(username,password)){
            loginStage.close();
            login.start();
        }
    }
}