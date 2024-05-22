package app.models;

import java.util.Date;
import app.models.SellOrderDetail;
import java.util.List;
public class SellOrder {
    private int orderID;
    private int finalPrice;
    private String descriptions;
    private java.util.Date sendDate;
    private java.util.Date arriveDate;
    private java.util.Date deliveryDate;
    private List<SellOrderDetail> sellOrderDetails;
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
    public java.util.Date getSendDate() {
        return sendDate;
    }
    public void setSendDate(Date sendDate ){
        this.sendDate = sendDate;
    }
    public java.util.Date getArriveDate() {
        return arriveDate;
    }
    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }
    public java.util.Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public List<SellOrderDetail> getSellOrderDetails(){
        return sellOrderDetails;
    }
    public void setSellOrderDetails(List<SellOrderDetail> sellOrderDetails){
        this.sellOrderDetails = sellOrderDetails;
    }
}
