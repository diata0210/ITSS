package app.model;

public class SiteOrder {
    private int id;
    private int orderID;
    private int siteID;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int osrderID) {
        this.orderID = orderID;
    }
    public int getSiteID() {
        return siteID;
    }
    public void setSiteID(int siteID) {
        this.siteID = siteID;
    }
}
