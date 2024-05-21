package app.repositories.implement;

import app.db.DatabaseConnection;
import app.models.CreateSiteOrder;
import app.models.SiteOrder;
import app.models.SiteOrderDetail;
import app.repositories.OrderToSiteRepository;
import javafx.util.Pair;

import java.sql.*;
import java.util.*;

public class OrderToSiteRepositoryImp implements OrderToSiteRepository {

    Connection connection = DatabaseConnection.getConnection();
    @Override
    public List<SiteOrder> getAllOrderToSite() {
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
        String query = "SELECT p.ID, quantity, p.pname, sod.finalPrice, sod.soStatus FROM SiteOrderDetails sod " +
                "JOIN Products p ON sod.productID = p.ID " +
                "WHERE sod.siteOrderID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, siteOrderID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SiteOrderDetail siteOrderDetail = new SiteOrderDetail();
                siteOrderDetail.setPID(resultSet.getInt(("ID")));
                siteOrderDetail.setPName(resultSet.getString("pname"));
                siteOrderDetail.setQuantity(resultSet.getInt("quantity"));
                siteOrderDetail.setPPrice(resultSet.getBigDecimal("finalPrice"));
                siteOrderDetail.setStatus(resultSet.getInt("soStatus"));
                siteOrderDetails.add(siteOrderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return siteOrderDetails;
    }

    @Override
    public SiteOrder getById(int id) {
        SiteOrder siteOrder = new SiteOrder();
        String query = "SELECT so.ID, s.sname, so.finalPrice, so.oStatus " +
                "FROM SiteOrders so " +
                "JOIN Sites s ON so.siteID = s.ID " +
                "WHERE so.ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                siteOrder.setID(resultSet.getInt("ID"));
                siteOrder.setSiteName(resultSet.getString("sname"));
                siteOrder.setFinalPrice(resultSet.getBigDecimal("finalPrice"));
                siteOrder.setOStatus(resultSet.getInt("oStatus"));
                List<SiteOrderDetail> siteOrderDetails = getSiteOrderDetails(id);
                String deliveryDate = getDeliveryDate(id);
                String sendDate = getSendDate(id);
                siteOrder.setSendDate(sendDate);
                siteOrder.setDeliveryDate(deliveryDate);
                siteOrder.setSellOrderID(getOrderID(id));
                siteOrder.setSiteOrderDetails(siteOrderDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return siteOrder;
    };

    private String getDeliveryDate(int siteOrderId) {
        String date = null;
        String query = "SELECT o.deliveryDate totalDate " +
                "FROM Orders o " +
                "JOIN SiteOrders s ON s.orderID = o.ID " +
                "WHERE s.ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, siteOrderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                date = resultSet.getString("totalDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return date;
    }

    private String getSendDate(int siteOrderId) {
        String date = null;
        String query = "SELECT o.sendDate AS totalDate " +
                "FROM Orders o " +
                "JOIN SiteOrders s ON s.orderID = o.ID " +
                "WHERE s.ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, siteOrderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                date = resultSet.getString("totalDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return date;
    }

    public int getOrderID(int siteOrderId) {
        int orderID = 0;

        String query = "SELECT orderID FROM SiteOrders WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, siteOrderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                orderID = resultSet.getInt("orderID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderID;
    }

    @Override
    public void createSiteOrders(CreateSiteOrder createSiteOrder) {
        String siteOrderInsertQuery = "INSERT INTO SiteOrders (orderID, siteID, oStatus) VALUES (?, ?, 1)"; // Mặc định oStatus = 1
        String siteOrderDetailsInsertQuery = "INSERT INTO SiteOrderDetails (siteOrderID, productID, quantity, soStatus) VALUES (?, ?, ?, 2)"; // Mặc định soStatus = 2
        try {
            connection.setAutoCommit(false);
            PreparedStatement siteOrderStatement = connection.prepareStatement(siteOrderInsertQuery, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement siteOrderDetailsStatement = connection.prepareStatement(siteOrderDetailsInsertQuery);

            List<Integer> siteIDs = createSiteOrder.getSiteIDs();
            List<Integer> productIDs = createSiteOrder.getProductIDs();
            List<Integer> quantities = createSiteOrder.getQuantities();

            // Tạo một danh sách tạm thời để lưu các bộ giá trị đã được sắp xếp theo siteID
            List<Pair<Integer, Pair<Integer, Integer>>> sortedData = new ArrayList<>();
            for (int i = 0; i < siteIDs.size(); i++) {
                sortedData.add(new Pair<>(siteIDs.get(i), new Pair<>(productIDs.get(i), quantities.get(i))));
            }

            // Sắp xếp danh sách tạm thời theo siteID
            Collections.sort(sortedData, Comparator.comparing(Pair::getKey));

            int previousSiteID = -1; // Giá trị siteID trước đó
            int siteOrderID = 0; // Khóa chính của siteOrder hiện tại

            for (Pair<Integer, Pair<Integer, Integer>> data : sortedData) {
                int siteID = data.getKey();
                int productID = data.getValue().getKey();
                int quantity = data.getValue().getValue();

                // Nếu siteID hiện tại không trùng với siteID trước đó,
                // ta cần tạo một siteOrder mới và lấy khóa chính của nó
                if (siteID != previousSiteID) {
                    siteOrderStatement.setInt(1, createSiteOrder.getOrderID());
                    siteOrderStatement.setInt(2, siteID);
                    siteOrderStatement.executeUpdate();

                    ResultSet generatedKeys = siteOrderStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        siteOrderID = generatedKeys.getInt(1); // Lấy giá trị khóa chính của SiteOrders
                    } else {
                        throw new SQLException("Failed to get generated SiteOrder ID.");
                    }

                    // Cập nhật giá trị siteID trước đó
                    previousSiteID = siteID;
                }

                siteOrderDetailsStatement.setInt(1, siteOrderID);
                siteOrderDetailsStatement.setInt(2, productID);
                siteOrderDetailsStatement.setInt(3, quantity);
                siteOrderDetailsStatement.addBatch();
            }

            siteOrderDetailsStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
