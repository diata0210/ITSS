package app.controller;

import java.io.IOException;

import app.repositories.LoginRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class LoginController {
    LoginRepository login = new LoginRepository();
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
        Stage loginStage = (Stage) usernameField.getScene().getWindow();
        if(login.login(username,password)){
            loginStage.close();
            start();
        }
    }
    public void start(){
        if (login.getRole() == 2){
            loginWithOrderDepartment();
        }
        else if (login.getRole() == 3){
            loginWithSite();
        }
    }
    private void loginWithOrderDepartment(){
        try {
            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/app/project/Layout.fxml"));
            Parent root = loader.load();
            Stage homeStage = new Stage();
            homeStage.setScene(new Scene(root));
            homeStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loginWithSite(){
        try {
            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/app/project/SiteHomepage.fxml"));
            Parent root = loader.load();
            SiteHomePageController controller = loader.getController();
            controller.setUserId(3);
            controller.initialize(null, null);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
