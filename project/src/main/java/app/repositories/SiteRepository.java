package app.repositories;

import app.models.ProductSite;
import app.models.Site;

import java.util.List;

public interface SiteRepository {
    List<Site> getAll();
    Site getById(int id);
    List<ProductSite> getAllProductSite(int id);
    int getQuantityInStore(int siteId, int productId);
}
