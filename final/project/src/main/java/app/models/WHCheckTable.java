package app.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.math.BigDecimal;

public class WHCheckTable {
    private int productID;
    private String productName;
    private final IntegerProperty quantity;
    private final ObjectProperty<BigDecimal> price;
    private final IntegerProperty checked;
    private final IntegerProperty deviated;
    private ObjectProperty<BigDecimal> deviationValue;

    public WHCheckTable(int productID, String productName, BigDecimal price, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleObjectProperty<>(price);
        this.checked = new SimpleIntegerProperty(quantity); // Initialize checked with the value of quantity
        this.deviated = new SimpleIntegerProperty(0); // Initial deviated
        this.deviationValue = new SimpleObjectProperty<>(BigDecimal.ZERO);
    }
    public WHCheckTable(int productID, String productName, BigDecimal price, int quantity, int checked, IntegerProperty deviated) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleObjectProperty<>(price);
        this.checked = new SimpleIntegerProperty(quantity); // Initialize checked with the value of quantity
//        this.deviated = new SimpleIntegerProperty(0); // Initial deviated
//        this.deviationValue = new SimpleObjectProperty<>(BigDecimal.ZERO);
        this.deviated = deviated;
    }


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
        updateDependentValues();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price.get();
    }

    public void setPrice(BigDecimal price) {
        this.price.set(price);
        updateDependentValues();
    }

    public ObjectProperty<BigDecimal> priceProperty() {
        return price;
    }

    public int getChecked() {
        return checked.get();
    }

    public void setChecked(int checked) {
        this.checked.set(checked);
        updateDependentValues();
    }

    public IntegerProperty checkedProperty() {
        return checked;
    }

    public int getDeviated() {
        return deviated.get();
    }

    public void setDeviated(int deviated) {
        this.deviated.set(deviated);
    }

    public IntegerProperty deviatedProperty() {
        return deviated;
    }

    public BigDecimal getDeviationValue() {
        return deviationValue.get();
    }

    public void setDeviationValue(BigDecimal deviationValue) {
        this.deviationValue.set(deviationValue);
    }

    public ObjectProperty<BigDecimal> deviationValueProperty() {
        return deviationValue;
    }

    private void updateDependentValues() {
        int quantity = getQuantity();
        int checked = getChecked();
        int deviated = quantity - checked;
        setDeviated(deviated);
        setDeviationValue(BigDecimal.valueOf(deviated).multiply(getPrice()));
    }
}
