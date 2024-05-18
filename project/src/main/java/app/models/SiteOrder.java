package app.models;

import java.math.BigDecimal;
import java.util.List;

public class SiteOrder {
    private int ID;
    public String siteOrderCode;
    private String siteName;
    public BigDecimal finalPrice;
    private String orderCode;
    private OrderStatus oStatus;
    private List<SiteOrderDetail> siteOrderDetails;

    public String getSiteOrderCode() {
        return siteOrderCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public List<SiteOrderDetail> getSiteOrderDetails() {
        return siteOrderDetails;
    }

    public int getID() {
        return ID;
    }

    public OrderStatus getOStatus() {
        return oStatus;
    }

    public void setOStatus(OrderStatus oStatus) {
        this.oStatus = oStatus;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setSiteOrderCode(String siteOrderCode) {
        this.siteOrderCode = siteOrderCode;
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


}
