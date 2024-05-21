package app.models;

import java.io.IOException;
import java.sql.Connection;
import app.controller.LoginController;
import app.db.DatabaseConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.controller.SiteHomePageController;

public class LoginModel {
    private Connection connection = DatabaseConnection.getConnection();
    private User user = new User();
    public boolean login(String username, String password){
        String query = "SELECT * FROM Users WHERE username = ? AND upassword = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(username);
                    user.setRole(resultSet.getInt("roles"));
                }
                else return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public void start(){
        if (user.getRole() == 2){
            loginWithOrderDepartment();
        }
        else if (user.getRole() == 3){
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
