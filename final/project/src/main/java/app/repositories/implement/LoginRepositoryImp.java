package app.repositories.implement;

import app.db.DatabaseConnection;
import app.repositories.LoginRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepositoryImp implements LoginRepository {

    private Connection connection = DatabaseConnection.getConnection();

    @Override
    public int getRole(String username, String password) {
        String query = "SELECT roles FROM Users WHERE username = ? AND upassword = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("roles");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
