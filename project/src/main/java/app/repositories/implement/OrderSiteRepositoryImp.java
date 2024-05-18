package app.repositories.implement;

import app.annotation.Component;
import app.annotation.Inject;
import app.db.DatabaseConnection;
import app.models.OrderStatus;
import app.models.SiteOrder;
import app.models.SiteOrderDetail;
import app.repositories.OrderSiteRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderSiteRepositoryImp implements OrderSiteRepository {

    @Inject
    Connection connection = DatabaseConnection.getConnection();
    @Override
    public List<SiteOrder> getAlls() {
        List<SiteOrder> siteOrders = new ArrayList<>();
        String query =  "SELECT so.ID, so.sideOrderCode, s.sname, so.finalPrice, so.oStatus " +
                        "FROM SiteOrders so " +
                        "JOIN Sites s ON so.siteID = s.ID";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SiteOrder siteOrder = new SiteOrder();
                siteOrder.setID(resultSet.getInt("ID"));
                siteOrder.setSiteOrderCode(resultSet.getString("sideOrderCode"));
                siteOrder.setSiteName(resultSet.getString("sname"));
                siteOrder.setFinalPrice(resultSet.getBigDecimal("finalPrice"));

                int oStatusValue = resultSet.getInt("oStatus");
                OrderStatus oStatus = OrderStatus.valueOf(String.valueOf(oStatusValue));
                siteOrder.setOStatus(oStatus);

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
        String query = "SELECT quantity, p.pCode FROM SiteOrderDetails sod " +
                "JOIN Products p ON sod.productID = p.ID " +
                "WHERE sod.siteOrderID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, siteOrderID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SiteOrderDetail siteOrderDetail = new SiteOrderDetail();
                siteOrderDetail.setpCode(resultSet.getString("pCode"));
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
