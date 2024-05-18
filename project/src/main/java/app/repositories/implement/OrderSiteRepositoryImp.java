package app.repositories.implement;

import app.db.DatabaseConnection;
import app.models.SiteOrder;
import app.repositories.OrderSiteRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderSiteRepositoryImp implements OrderSiteRepository {

    Connection connection = DatabaseConnection.getConnection();
    @Override
    public List<SiteOrder> getAlls() {
        List<SiteOrder> siteOrders = new ArrayList<>();
        String query = "SELECT * FROM SiteOrders";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SiteOrder siteOrder = new SiteOrder();
                siteOrder.setId(resultSet.getInt("id"));
                siteOrder.setOrderID(resultSet.getInt("orderID"));
                siteOrder.setSiteID(resultSet.getInt("siteID"));
                siteOrder.setFinalPrice(resultSet.getBigDecimal("finalPrice"));
                siteOrder.setOstatus(resultSet.getInt("oStatus"));
                siteOrders.add(siteOrder);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return siteOrders;
    }

    @Override
    public SiteOrder getById() {
        return null;
    }

    @Override
    public SiteOrder getByStatus(int id) {
        return null;
    }
}
