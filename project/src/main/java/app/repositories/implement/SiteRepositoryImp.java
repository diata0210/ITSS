package app.repositories.implement;

import app.db.DatabaseConnection;
import app.models.ProductSite;
import app.models.Site;
import app.repositories.SiteRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SiteRepositoryImp implements SiteRepository {

    Connection connection = DatabaseConnection.getConnection();
    @Override
    public List<Site> getAll() {

        return List.of();
    }

    @Override
    public Site getById(int id) {
        return null;
    }

    @Override
    public List<ProductSite> getAllProductSite(int id) {
        return List.of();
    }

    @Override
    public int getQuantityInStore(int siteId, int productId) {
        String query = "SELECT quantity FROM ProductSite WHERE siteID = ? AND productID = ?";
        int quantity = 0;

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                quantity = resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return quantity;
    }
}
