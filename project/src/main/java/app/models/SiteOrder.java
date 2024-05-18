package app.models;

import java.math.BigDecimal;
import java.util.List;

public class SiteOrder {
    private int ID;
    private String siteName;
    public BigDecimal finalPrice;
    private String orderCode;
    private String oStatus;
    private List<SiteOrderDetail> siteOrderDetails;


    public String getSiteName() {
        return siteName;
    }

    public List<SiteOrderDetail> getSiteOrderDetails() {
        return siteOrderDetails;
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
        } else if (oStatus == 2){
            this.oStatus = "Đang lấy hàng";
        } else if (oStatus == 3) {
            this.oStatus = "Đang giao hàng";
        } else if (oStatus == 4) {
            this.oStatus = "Đã nhận hàng";
        }else if (oStatus == 5){
            this.oStatus = "Đã hủy";
        }
    }

    public String getOrderCode() {
        return orderCode;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setSiteOrderDetails(List<SiteOrderDetail> siteOrderDetails) {
        this.siteOrderDetails = siteOrderDetails;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }


    public void setID(int ID) {
        this.ID = ID;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getoStatus() {
        return oStatus;
    }
}
