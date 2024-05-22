package app.repositories.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.db.DatabaseConnection;
import app.models.SellOrderDetail;
import app.models.SellOrder;
import app.models.SiteOrder;
import app.models.SiteOrderDetail;
import app.repositories.SellOrderRepository;

public class SellOrderRepositoryImp implements SellOrderRepository{
    
Connection connection = DatabaseConnection.getConnection();

    @Override
    public List<SellOrder> getAlls() {
        List<SellOrder> sellOrders = new ArrayList<>();
        String query = "SELECT s.ID, s.arriveDate  FROM Orders s";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SellOrder sellOrder = new SellOrder();
                sellOrder.setOrderID(resultSet.getInt("ID"));
                sellOrder.setArriveDate(resultSet.getDate("arriveDate"));                

                int sellOrderID = resultSet.getInt("ID");
                List<SellOrderDetail> sellOrderDetails = getSellOrderDetails(sellOrderID);
                sellOrder.setSellOrderDetails(sellOrderDetails);
                sellOrders.add(sellOrder);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sellOrders;
    }

     public List<SellOrderDetail> getSellOrderDetails(int sellOrderID) {
        List<SellOrderDetail> sellOrderDetails = new ArrayList<>();
        String query = "SELECT p.ID, p.pname, sod.orderID, sod.quantity FROM OrderDetails sod " +
                "JOIN Products p ON sod.productID = p.ID " +
                "WHERE sod.orderID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sellOrderID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SellOrderDetail sellOrderDetail = new SellOrderDetail();
                sellOrderDetail.setOrderID(resultSet.getInt("orderID"));
                sellOrderDetail.setPName(resultSet.getString("pname"));
                sellOrderDetail.setProductID(resultSet.getInt("ID"));
                sellOrderDetail.setQuantity(resultSet.getInt("quantity"));
                System.out.println(sellOrderDetail.getPName()); 
                sellOrderDetails.add(sellOrderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sellOrderDetails;
    }

    @Override
    public SellOrder getById(int id){
        SellOrder sellOrder = new SellOrder();
        String query = "SELECT s.ID, s.arriveDate,s.sendDate,s.deliveryDate  FROM Orders s WHERE s.ID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                sellOrder.setOrderID(resultSet.getInt("ID"));
                sellOrder.setArriveDate(resultSet.getDate("arriveDate"));
                sellOrder.setSendDate(resultSet.getDate("sendDate"));  
                sellOrder.setDeliveryDate(resultSet.getDate("deliveryDate"));             
                System.out.println(sellOrder.getOrderID());
                System.out.println(sellOrder.getFinalPrice());
                List<SellOrderDetail> sellOrderDetails = getSellOrderDetails(id);
                System.out.println(getSellOrderDetails(id));
                sellOrder.setSellOrderDetails(sellOrderDetails);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sellOrder;
    }
}

    
