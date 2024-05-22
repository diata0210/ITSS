package app.models;

public class SellOrderDetail {
    private int productID;
    private String pName;
    private int orderID;
    private int quantity;
    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
     public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }
}
