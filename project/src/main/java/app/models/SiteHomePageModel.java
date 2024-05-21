package app.models;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import app.db.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SiteHomePageModel{
    private Connection connection = DatabaseConnection.getConnection();
    private String name;
    private int code;
    private String address;
    private int airDelivery = -1;
    private int shipDelivery = -1;
    private ObservableList<ProductSite> siteProducts = FXCollections.observableArrayList();
    public void loadData(int userId){
        getSiteFromDB(userId);
        getVehicleSiteFromDB();
        getSiteProductsFromDB();
    }
    private void getSiteFromDB(int userId){
        String query = "SELECT * FROM Sites WHERE userID = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    name = resultSet.getString("sname");
                    code = resultSet.getInt("ID");
                    address = resultSet.getString("saddress");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void getVehicleSiteFromDB(){
        String query = "SELECT * FROM VehicleSites WHERE siteID = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, code);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    if (resultSet.getInt("vehicleID") == 1) {
                        shipDelivery = resultSet.getInt("dateTrans");
                    }
                    else {
                        airDelivery = resultSet.getInt("dateTrans");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void getSiteProductsFromDB(){
        String query = "SELECT ps.productID, ps.quantity, p.pname, p.price\r\n" + //
                        "FROM ProductSites ps\r\n" + //
                        "JOIN Products p ON ps.productID = p.ID\r\n" + //
                        "WHERE ps.siteID = ?;\r\n" + //
                        "";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, code);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    ProductSite pSite = new ProductSite();
                    pSite.setProductID(resultSet.getInt("productID"));
                    pSite.setName(resultSet.getString("pname"));
                    pSite.setPrice(resultSet.getInt("price"));
                    pSite.setQuantity(resultSet.getInt("quantity"));
                    siteProducts.add(pSite);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getName() {
        return name;
    }
    public int getCode() {
        return code;
    }
    public String getAddress() {
        return address;
    }
    public int getAirDelivery() {
        return airDelivery;
    }
    public int getShipDelivery() {
        return shipDelivery;
    }
    public ObservableList<ProductSite> getSiteProducts() {
        return siteProducts;
    }
}
