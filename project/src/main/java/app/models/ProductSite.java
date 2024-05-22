package app.models;
import java.math.BigDecimal;

public class ProductSite {
    private int siteID;
    private int productID;
    private int quantity;
    private String name;
    private BigDecimal price;
    private String unit;
    public ProductSite(){

    }
    public ProductSite(int siteID, int productID, int quantity) {
        this.siteID = siteID;
        this.productID = productID;
        this.quantity = quantity;
    }
    
    public void setSiteID(int siteID) {
        this.siteID = siteID;
    }
    public int getSiteID() {
        return siteID;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public int getProductId() {
        return productID;
    }
    public void setProductId(int productId) {
        this.productID = productId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
