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

    public void setStatus(int status) {
        if(status == 1){
            this.status = "Hết hàng";
        }else{
            this.status = "Còn hàng";
        }
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getPPrice() {
        return pPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPName() {
        return pName;
    }

    public void setPID(int pID) {
        this.pID = pID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public void setPPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }
}
