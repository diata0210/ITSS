package app.models;
public class Site {
    private int id;
    private String name;
    private String address;
    private int deliveryByAir;
    private int deliveryByShip;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getDeliveryByAir() {
        return deliveryByAir;
    }
    public void setDeliveryByAir(int deliveryByAir) {
        this.deliveryByAir = deliveryByAir;
    }
    public int getDeliveryByShip() {
        return deliveryByShip;
    }
    public void setDeliveryByShip(int deliveryByShip) {
        this.deliveryByShip = deliveryByShip;
    }
}