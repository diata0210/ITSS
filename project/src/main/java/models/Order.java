package models;
import java.sql.Date;
public class Order {
    private int orderID;
    private int finalPrice;
    private String descriptions;
    private Date arriveDate;
    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public int getFinalPrice() {
        return finalPrice;
    }
    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }
    public String getDescriptions() {
        return descriptions;
    }
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
    public Date getArriveDate() {
        return arriveDate;
    }
    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }
    
}
