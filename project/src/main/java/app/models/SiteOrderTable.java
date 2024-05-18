package app.models;

import java.math.BigDecimal;
import java.util.List;

public class SiteOrderTable {
    private int ID;
    private String siteName;
    private BigDecimal finalPrice;
    private String oStatus;

    public SiteOrderTable(String siteName, BigDecimal finalPrice, String oStatus, int ID) {
        this.siteName = siteName;
        this.finalPrice = finalPrice;
        this.oStatus = oStatus;
        this.ID = ID;
    }

    public String getSiteName() {
        return siteName;
    }

    public int getID() {
        return ID;
    }

    public String getOStatus() {
        return oStatus;
    }


    public void setOStatus(int oStatus) {
        if(oStatus == 1){
            this.oStatus = "Chờ xác nhận";
        }
        else if (oStatus == 2){
            this.oStatus = "Đang lấy hàng";
        } else if (oStatus == 3) {
            this.oStatus = "Đang giao hàng";
        } else if (oStatus == 4) {
            this.oStatus = "Đã nhận hàng";
        }else if (oStatus == 5){
            this.oStatus = "Đã hủy";
        }
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
