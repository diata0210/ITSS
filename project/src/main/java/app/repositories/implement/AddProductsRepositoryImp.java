package app.repositories.implement;

import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import app.db.DatabaseConnection;
import app.models.ProductSite;
import app.repositories.AddProductsRepository;

public class AddProductsRepositoryImp implements AddProductsRepository{
    private Connection connection = DatabaseConnection.getConnection();
    private ObservableList<ProductSite> products = FXCollections.observableArrayList();
    @Override
    public void loadData(){
        String query = "SELECT * FROM Products;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    ProductSite product = new ProductSite();
                    product.setProductId(resultSet.getInt("ID"));
                    product.setName(resultSet.getString("pname"));
                    product.setPrice(resultSet.getBigDecimal("price"));
                    product.setUnit(resultSet.getString("punit"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addProducts(ObservableList<ProductSite> addProducts){
        String query = "INSERT INTO ProductSites (siteID, productID, quantity) VALUES ";
        for (ProductSite product : addProducts) {
                query = query + "(" + product.getSiteId() + "," + product.getProductId() +"," + product.getQuantity() +"),";
        }
        query = query.substring(0, query.length() - 1);
        query += " ON DUPLICATE KEY UPDATE quantity = quantity + VALUES(quantity);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ObservableList<ProductSite> getProducts() {
        return products;
    }
}