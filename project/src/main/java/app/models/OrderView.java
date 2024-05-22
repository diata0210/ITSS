package app.models;

import java.math.BigDecimal;

import javafx.beans.property.*;

public class OrderView {
    private StringProperty productName = new SimpleStringProperty();
    private IntegerProperty ID = new SimpleIntegerProperty();
    private StringProperty sName = new SimpleStringProperty();
    private IntegerProperty quantity = new SimpleIntegerProperty();
    private ObjectProperty<BigDecimal> price = new SimpleObjectProperty<BigDecimal>();
    private StringProperty vehicle = new SimpleStringProperty();
    private StringProperty arrDate = new SimpleStringProperty();
    private StringProperty delete = new SimpleStringProperty();

    public StringProperty getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = new SimpleStringProperty(delete);
    }

    public OrderView(String productName, int ID, String sName, int quantity, BigDecimal price, String vehicle, String arrDate) {
        this.productName = new SimpleStringProperty(productName) ;
        this.ID = new SimpleIntegerProperty(ID);
        this.sName = new SimpleStringProperty(sName);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleObjectProperty<BigDecimal>(price);
        this.vehicle = new SimpleStringProperty(vehicle);
        this.arrDate = new SimpleStringProperty(arrDate);
        this.delete = new SimpleStringProperty("Xóa");
    }

    public String getProductName() {
        return productName.get();
    }

    public Integer getID() {
        return ID.get();
    }

    public void setID(int ID) {
        this.ID.setValue(ID);
    }

    public String getSName() {
        return sName.get();
    }
    public void setSName (String sName) {
        this.sName.setValue(sName);
    }

    public Integer getQuantity() {
        return quantity.get();
    }
    public void setQuantity(int quantity) {
        this.quantity = new SimpleIntegerProperty(quantity);
    }
    public String getVehicle () {
        return vehicle.get();
    }
    public void setVehicle (String vehicle) {
        this.vehicle = new SimpleStringProperty(vehicle);
    }
    public String getArrDate () {
        return arrDate.get();
    }
    public void setArrDate (String arrDate){
        this.arrDate = new SimpleStringProperty(arrDate);
    };
    public BigDecimal getPrice ( ){
        return price.get();
    }
    public void setPrice (BigDecimal price){
        this.price = new SimpleObjectProperty<BigDecimal>(price);
    }
}
