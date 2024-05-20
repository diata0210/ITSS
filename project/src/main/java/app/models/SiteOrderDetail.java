package app.models;

import java.math.BigDecimal;

public class SiteOrderDetail {
    private int pID;
    private String pName;
    private int quantity;
    private BigDecimal pPrice;
    private String status;

    public int getPID() {
        return pID;
    }

    public void setPID(int pID) {
        this.pID = pID;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPPrice() {
        return pPrice;
    }

    public void setPPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(int status) {
        if(status == 1){
            this.status = "Hết hàng";
        }else{
            this.status = "Còn hàng";
        }
    }
}
