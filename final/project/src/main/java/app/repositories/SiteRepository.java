package app.repositories;

import app.models.ProductSite;
import app.models.Site;
import app.models.VehicleSite;

import java.util.List;

public interface SiteRepository {
    List<Site> getAllSites();
    Site getById(int id);
    List<ProductSite> getAllProductInSite(int siteID);
    int getQuantityInSite(int siteId0, int productId);
    List<VehicleSite> getAllVehicleInSite(int siteId);
    void updateQuantityProductSite(int siteId, int productId, int newQuantity);
}
