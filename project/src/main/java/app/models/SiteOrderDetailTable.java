package app.models;

import java.math.BigDecimal;

public class SiteOrderDetailTable {
    private int ID;
    private String pName;
    private int quantity;
    private BigDecimal pPrice;
    private String status;

    public SiteOrderDetailTable(int ID, String pName, int quantity, BigDecimal pPrice, String status) {
        this.ID = ID;
        this.pName = pName;
        this.quantity = quantity;
        this.pPrice = pPrice;
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public String getPName() {
        return pName;
    }

    public String getStatus() {
        return status;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPPrice() {
        return pPrice;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }
}
