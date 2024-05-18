package app.models;

public class SiteOrderDetail {
    private String pCode;
    private int quantity;

    public String getpCode() {
        return pCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
