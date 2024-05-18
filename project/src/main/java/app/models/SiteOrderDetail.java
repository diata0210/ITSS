package app.models;

public class SiteOrderDetail {
    private String pName;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public String getpName() {
        return pName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}
