package app.repositories.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import app.db.DatabaseConnection;
import app.models.ProductSite;
import app.repositories.SiteHomePageRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
public class SiteHomePageRepositoryImp implements SiteHomePageRepository{
    private Connection connection = DatabaseConnection.getConnection();
    private String name;
    private int code;
    private String address;
    private int airDelivery = -1;
    private int shipDelivery = -1;
    private ObservableList<ProductSite> siteProducts = FXCollections.observableArrayList();
    @Override
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
        siteProducts.clear();
        String query = "SELECT ps.productID, ps.quantity, p.pname, p.price, p.punit \r\n" + //
                        "FROM ProductSites ps\r\n" + //
                        "JOIN Products p ON ps.productID = p.ID\r\n" + //
                        "WHERE ps.siteID = ?;\r\n" + //
                        "";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, code);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    ProductSite pSite = new ProductSite();
                    pSite.setProductId(resultSet.getInt("productID"));
                    pSite.setName(resultSet.getString("pname"));
                    pSite.setPrice(resultSet.getBigDecimal("price"));
                    pSite.setQuantity(resultSet.getInt("quantity"));
                    pSite.setUnit(resultSet.getString("punit"));
                    siteProducts.add(pSite);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getCode() {
        return code;
    }
    @Override
    public String getAddress() {
        return address;
    }
    @Override
    public int getAirDelivery() {
        return airDelivery;
    }
    @Override
    public int getShipDelivery() {
        return shipDelivery;
    }
    @Override
    public ObservableList<ProductSite> getSiteProducts() {
        return siteProducts;
    }
}
