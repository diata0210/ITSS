package app.models;

public class ProductSite {
    private int id;
    private int siteID;
    private int productID;
    private int quantity;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSiteID() {
        return siteID;
    }
    public void setSiteID(int siteID) {
        this.siteID = siteID;
    }
    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
