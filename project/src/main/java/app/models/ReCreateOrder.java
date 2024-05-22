package app.models;

import java.math.BigDecimal;

public class ReCreateOrder {
    private String productName;
    private int ID;
    private String sName;
    private int quantity;
    private BigDecimal price;
    private String vehicle;
    private String arrDate;
    private String delete;

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public ReCreateOrder(String productName, int ID, String sName, int quantity, BigDecimal price, String vehicle, String arrDate) {
        this.productName = productName;
        this.ID = ID;
        this.sName = sName;
        this.quantity = quantity;
        this.price = price;
        this.vehicle = vehicle;
        this.arrDate = arrDate;
        this.delete = "Xóa";
    }

    public String getProductName() {
        return productName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getArrDate() {
        return arrDate;
    }

    public String getSName() {
        return sName;
    }

    public String getVehicle() {
        return vehicle;
    }
}
