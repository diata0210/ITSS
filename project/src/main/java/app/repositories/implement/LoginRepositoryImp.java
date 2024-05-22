package app.repositories.implement;

import java.sql.Connection;
import app.db.DatabaseConnection;
import app.models.User;
import app.repositories.LoginRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepositoryImp implements LoginRepository{
    private Connection connection = DatabaseConnection.getConnection();
    private User user = new User();
    @Override
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
    @Override
    public int getRole(){
        return user.getRole();
    }
}
