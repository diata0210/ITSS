package app.model;

import java.util.List;

public class SiteOrder {
    private int id;
    private int orderID;
    private int siteID;
    private int status;
    private int final_price;
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
}
