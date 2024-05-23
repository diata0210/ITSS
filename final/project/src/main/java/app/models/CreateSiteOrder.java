package app.models;

import java.math.BigDecimal;
import java.util.List;

public class CreateSiteOrder {
    int orderID;
    List<Integer> siteIDs;
    List<Integer> productIDs;
    List<Integer> quantities;

    public CreateSiteOrder(int orderID, List<Integer> siteIDs, List<Integer> productIDs, List<Integer> quantities) {
        this.orderID = orderID;
        this.siteIDs = siteIDs;
        this.productIDs = productIDs;
        this.quantities = quantities;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public List<Integer> getSiteIDs() {
        return siteIDs;
    }

    public void setSiteIDs(List<Integer> siteIDs) {
        this.siteIDs = siteIDs;
    }

    public List<Integer> getProductIDs() {
        return productIDs;
    }

    public void setProductIDs(List<Integer> productIDs) {
        this.productIDs = productIDs;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }
}
