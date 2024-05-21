package app.models;

public class VehicleSite {
    private int vehicleID;
    private int siteID;
    private int dateTrans;
    private String vName;

    public VehicleSite(int vehicleID,String vName, int siteID, int dateTrans) {
        this.vehicleID = vehicleID;
        this.siteID = siteID;
        this.dateTrans = dateTrans;
        this.vName = vName;
    }

    public String getVName() {
        return vName;
    }

    public void setVName(String vName) {
        this.vName = vName;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getSiteID() {
        return siteID;
    }

    public void setSiteID(int siteID) {
        this.siteID = siteID;
    }

    public int getDateTrans() {
        return dateTrans;
    }

    public void setDateTrans(int dateTrans) {
        this.dateTrans = dateTrans;
    }
}
