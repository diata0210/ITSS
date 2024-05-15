package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.HomePage;
public class LoginController {

    @FXML
    private TextField paswordField;

    @FXML
    private TextField usernameField;

    @FXML
    void clickedForgotPassword(MouseEvent event) {

    }

    @FXML
    void clickedLogin(ActionEvent event) {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        HomePage homePage = new HomePage();
        homePage.start(stage);
    }

}
