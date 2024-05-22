package app.repositories.implement;

import app.db.DatabaseConnection;
import app.models.SiteOrder;
import app.models.WHCheckTable;
import app.models.WHCheckedTable;
import app.repositories.WarehouseRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WarehouseRepositoryImp implements WarehouseRepository {
    Connection connection = DatabaseConnection.getConnection();
    @Override
    public List<SiteOrder> getSiteOrders() {
        List<SiteOrder> siteOrders = new ArrayList<>();
        String query = "SELECT * FROM SiteOrders  WHERE oStatus =3";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SiteOrder siteOrder = new SiteOrder();
                siteOrder.setID(resultSet.getInt("ID"));
                siteOrder.setSiteID(resultSet.getInt("siteID"));

                siteOrder.setFinalPrice(resultSet.getBigDecimal("finalPrice"));
                siteOrder.setOStatus(resultSet.getInt("oStatus"));
                siteOrder.setVehicleID(resultSet.getInt("vehicleID"));

                int siteOrderID = resultSet.getInt("ID");
                List<WHCheckTable> siteOrderDetails = getSiteOrderDetail(siteOrderID);
                siteOrder.setWHChecks(siteOrderDetails);
                siteOrders.add(siteOrder);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return siteOrders;    }

    @Override
    public List<SiteOrder> getSiteOrdersChecked() {
        List<SiteOrder> siteOrders = new ArrayList<>();
        String query = "SELECT * FROM SiteOrders WHERE oStatus =4";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SiteOrder siteOrder = new SiteOrder();
                siteOrder.setID(resultSet.getInt("ID"));
                siteOrder.setSiteName(resultSet.getString("siteID"));
                siteOrder.setFinalPrice(resultSet.getBigDecimal("finalPrice"));

                Timestamp arrivalTimestamp = resultSet.getTimestamp("arrivalDate");

                // Chuyển đổi Timestamp thành LocalDateTime
                if (arrivalTimestamp != null) {
                    LocalDateTime arrivalDate = arrivalTimestamp.toLocalDateTime();
                    // Gán giá trị cho thuộc tính arrivalDate của siteOrder
                    siteOrder.setArrivalDate(arrivalDate);
                }
                siteOrder.setOStatus(resultSet.getInt("oStatus"));
                siteOrder.setActualValue(resultSet.getBigDecimal("actualValue"));
                System.out.println(siteOrder.getActualValue());
                siteOrder.setVehicleID(resultSet.getInt("vehicleID"));

                int siteOrderID = resultSet.getInt("ID");
                List<WHCheckTable> siteOrderDetails = getSiteOrderDetail(siteOrderID);
                siteOrder.setWHChecks(siteOrderDetails);
                siteOrders.add(siteOrder);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return siteOrders;    }

    @Override
    public void checkAuthUser() {

    }

    @Override
    public List <WHCheckTable> getSiteOrderDetail(int siteOrderID) {

        List<WHCheckTable> siteOrderDetails = new ArrayList<>();
        String query = "SELECT p.ID, p.pname , p.price as price, sod.quantity FROM SiteOrderDetails sod " +
                "JOIN Products p ON sod.productID = p.ID " +
                "WHERE sod.siteOrderID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, siteOrderID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                WHCheckTable siteOrderDetail = new WHCheckTable(resultSet.getInt(("ID")),resultSet.getString("pname"),resultSet.getBigDecimal(("price")),resultSet.getInt(("quantity")));

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
                List<WHCheckTable> WHChecks = getSiteOrderDetail(id);
                siteOrder.setWHChecks(WHChecks);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return siteOrder;


    };
    @Override
    public List <WHCheckedTable> getSiteOrderDetailChecked(int siteOrderID) {

        List<WHCheckedTable> siteOrderDetails = new ArrayList<>();
        String query = "SELECT p.ID, p.pname , p.price, sod.quantity, sod.actualQuantity  FROM SiteOrderDetails sod " +
                "JOIN Products p ON sod.productID = p.ID " +
                "WHERE sod.siteOrderID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, siteOrderID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                WHCheckedTable siteOrderDetail = new WHCheckedTable(resultSet.getInt(("ID")),resultSet.getString("pname"),resultSet.getBigDecimal(("price")),resultSet.getInt(("quantity")),resultSet.getInt(("actualQuantity")));

                siteOrderDetails.add(siteOrderDetail);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return siteOrderDetails;
    }
    @Override
    public SiteOrder getByIdChecked(int id) {
        SiteOrder siteOrder = new SiteOrder();
        String query = "SELECT so.ID, s.sname, so.finalPrice,so.actualValue, so.oStatus " +
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
                siteOrder.setActualValue(resultSet.getBigDecimal("actualValue"));
                List<WHCheckedTable> WHCheckeds = getSiteOrderDetailChecked(id);
                siteOrder.setWHCheckeds(WHCheckeds);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return siteOrder;


    };



//    public void updateActualQuantity(int productID, int checked, LocalDateTime arrivalDate) {
//        String sql = "UPDATE WHCheckTable SET checked = ?, arrivalDate  = ? WHERE product_id = ?\"";
//        try (PreparedStatement preparedStatement= connection.prepareStatement(sql)) {
//
//            preparedStatement.setInt(1, checked);
//            preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf(arrivalDate));
//            preparedStatement.setInt(3, productID);
//            preparedStatement.executeUpdate();
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
@Override
public void updateActualQuantity(int siteOrderID, int productID, int checked) {
    String sql = "UPDATE SiteOrderDetails SET actualQuantity  = ? WHERE siteOrderID = ? AND productID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, checked);
        preparedStatement.setInt(2, siteOrderID);
        preparedStatement.setInt(3, productID);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
            e.printStackTrace();
    }
}
@Override
    public void updateSiteOrder(int siteOrderID, LocalDateTime arrivalDate,BigDecimal actualValue, int orderStatus){
        String sql = "UPDATE SiteOrders SET arrivalDate = ?, actualValue = ?, oStatus  = ? WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(arrivalDate));
            preparedStatement.setBigDecimal(2,actualValue);
            preparedStatement.setInt(3, orderStatus);
            preparedStatement.setInt(4, siteOrderID);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setConnection(Connection mockConnection) {
        this.connection = connection;
    }
}
