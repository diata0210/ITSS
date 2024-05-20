package app.repositories.implement;

import app.db.DatabaseConnection;
import app.models.ProductSite;
import app.models.Site;
import app.models.VehicleSite;
import app.repositories.SiteRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SiteRepositoryImp implements SiteRepository {

    Connection connection = DatabaseConnection.getConnection();
    @Override
    public List<Site> getAllSites() {
        List<Site> sites = new ArrayList<>();
        String query = "SELECT * FROM Sites";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("sname");
                String address = resultSet.getString("saddress");
                Site site = new Site(id, name, address);
                sites.add(site);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sites;

    }

    @Override
    public Site getById(int ID) {
        String query = "SELECT * FROM Sites WHERE ID = ?";
        Site site = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("sname");
                String address = resultSet.getString("saddress");
                site = new Site(ID, name, address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return site;
    }

    @Override
    public int getQuantityInSite(int siteID, int productID) {
        String query = "SELECT quantity FROM ProductSites WHERE siteID = ? AND productID = ?";
        int quantity = 0;

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, siteID);
            preparedStatement.setInt(2, productID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                quantity = resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return quantity;
    }

    @Override
    public List<VehicleSite> getAllVehicleInSite(int siteId) {
        List<VehicleSite> vehicleSites = new ArrayList<>();
        String query = "SELECT vs.vehicleID, v.vname, vs.siteID, vs.dateTrans " +
                "FROM VehicleSites vs " +
                "JOIN Vehicles v ON vs.vehicleID = v.ID " +
                "WHERE vs.siteID = ?";
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, siteId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int vehicleId = resultSet.getInt("vehicleID");
                String vehicleName = resultSet.getString("vname");
                int siteID = resultSet.getInt("siteID");
                int dateTrans = resultSet.getInt("dateTrans");
                VehicleSite vehicleSite = new VehicleSite(vehicleId, vehicleName, siteID, dateTrans);
                vehicleSites.add(vehicleSite);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicleSites;
    }

    @Override
    public List<ProductSite> getAllProductInSite(int siteID){
        List<ProductSite> productSites = new ArrayList<>();
        String query = "SELECT * FROM ProductSites WHERE siteID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, siteID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                int quantity = resultSet.getInt("quantity");
                ProductSite productSite = new ProductSite(productID, siteID, quantity);
                productSites.add(productSite);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productSites;
    };
    @Override
    public void updateQuantityProductSite(int siteId, int productId, int quantity) {
        String query = "UPDATE ProductSites SET quantity = quantity - ? WHERE siteID = ? AND productID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, siteId);
            preparedStatement.setInt(3, productId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
