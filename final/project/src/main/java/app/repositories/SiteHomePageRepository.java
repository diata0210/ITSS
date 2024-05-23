package app.repositories;

import app.models.ProductSite;
import javafx.collections.ObservableList;

public interface SiteHomePageRepository {
    void loadData(int userId);
    String getName();
    int getCode();
    String getAddress();
    int getAirDelivery();
    int getShipDelivery();
    ObservableList<ProductSite> getSiteProducts();
}
