package app.repositories.implement;

import app.db.DatabaseConnection;
import app.models.SiteOrder;
import app.models.SiteOrderDetail;
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
        String query =  "SELECT so.ID,  s.sname, so.finalPrice, so.oStatus " +
                        "FROM SiteOrders so " +
                        "JOIN Sites s ON so.siteID = s.ID";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SiteOrder siteOrder = new SiteOrder();
                siteOrder.setID(resultSet.getInt("ID"));
                siteOrder.setSiteName(resultSet.getString("sname"));
                siteOrder.setFinalPrice(resultSet.getBigDecimal("finalPrice"));
                siteOrder.setOStatus(resultSet.getInt("oStatus"));

                int siteOrderID = resultSet.getInt("ID");
                List<SiteOrderDetail> siteOrderDetails = getSiteOrderDetails(siteOrderID);
                siteOrder.setSiteOrderDetails(siteOrderDetails);
                siteOrders.add(siteOrder);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return siteOrders;
    }

    private List<SiteOrderDetail> getSiteOrderDetails(int siteOrderID) {
        List<SiteOrderDetail> siteOrderDetails = new ArrayList<>();
        String query = "SELECT quantity, p.pname FROM SiteOrderDetails sod " +
                "JOIN Products p ON sod.productID = p.ID " +
                "WHERE sod.siteOrderID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, siteOrderID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SiteOrderDetail siteOrderDetail = new SiteOrderDetail();
                siteOrderDetail.setpName(resultSet.getString("pname"));
                siteOrderDetail.setQuantity(resultSet.getInt("quantity"));

                siteOrderDetails.add(siteOrderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return siteOrderDetails;
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
