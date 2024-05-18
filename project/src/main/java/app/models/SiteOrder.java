package app.models;

import java.math.BigDecimal;
import java.util.List;

public class SiteOrder {
    private int id;
    private int orderID;
    private int siteID;
    private int oStatus;
    public BigDecimal finalPrice;
    private List<SiteOrderDetail> siteOrderDetails;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {

        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public int getSiteID() {
        return siteID;
    }
    public void setSiteID(int siteID) {
        this.siteID = siteID;
    }

    public List<SiteOrderDetail> getSiteOrderDetails() {
        return siteOrderDetails;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void setSiteOrderDetails(List<SiteOrderDetail> siteOrderDetails) {
        this.siteOrderDetails = siteOrderDetails;
    }

    public void setOstatus(int ostatus) {
        this.oStatus = ostatus;
    }

    public int getOstatus() {
        return oStatus;
    }
}
