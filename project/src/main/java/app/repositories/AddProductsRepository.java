package app.repositories;

import app.models.ProductSite;
import javafx.collections.ObservableList;

public interface AddProductsRepository {
    void loadData();
    void addProducts(ObservableList<ProductSite> addProducts);
    ObservableList<ProductSite> getProducts();
}
