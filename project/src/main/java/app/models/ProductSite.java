package app.models;

public class ProductSite {
    private int siteID;
    private int productID;
    private int quantity;

    public ProductSite(int siteID, int productID, int quantity) {
        this.siteID = siteID;
        this.productID = productID;
        this.quantity = quantity;
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
