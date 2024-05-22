package app.controller;

import app.repositories.LoginRepository;
import app.repositories.implement.LoginRepositoryImp;
import app.services.LoginServiceImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private LoginServiceImp loginServiceImp = new LoginServiceImp();
    private LoginRepository loginRepository = new LoginRepositoryImp();

    @FXML
    private TextField paswordField;

    @FXML
    private TextField usernameField;

    @FXML
    void clickedForgotPassword(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginServiceImp.setLoginRepository(loginRepository);
    }

    @FXML
    void onLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = paswordField.getText();
        int role = loginServiceImp.getRole(username, password);
        if(role == 4){

        }else if(role == 2){
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
    }
}