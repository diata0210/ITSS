package app.db;

import app.models.SiteOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3309/sales_db?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = null;

    private static Connection connection = null;

    private DatabaseConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                System.out.println("Connected to MySQL database!");
            } catch (SQLException e) {
                System.err.println("Error connecting to the database: " + e.getMessage());
            }
        }
        return connection;
    }
}
