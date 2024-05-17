package app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnectionExample {
    public static void main(String[] args) {
        // Thông tin kết nối cơ sở dữ liệu
        String jdbcUrl = "jdbc:mysql://localhost:3306/sales_db?useSSL=false"; //i disabled ssl cuz its complicate thing
        String username = "root";
        String password = null;

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            System.out.println("Connected to MySQL database!");

        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}