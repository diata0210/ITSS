package app.repositories.implement;

import app.models.Product;
import app.repositories.ProductRepository;
import app.db.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImp implements ProductRepository {
    private Connection connection;

    public ProductRepositoryImp() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("pname");
                String description = resultSet.getString("punit");
                BigDecimal price = resultSet.getBigDecimal("price");

                Product product = new Product(id, name, description, price);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product getProductById(int ID) {
        Product product = null;
        String query = "SELECT * FROM Products WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("pname");
                String description = resultSet.getString("punit");
                BigDecimal price = resultSet.getBigDecimal("price");
                product = new Product(id, name, description, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}
