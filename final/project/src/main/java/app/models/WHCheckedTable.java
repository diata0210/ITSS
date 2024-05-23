package app.models;



import java.math.BigDecimal;

public class WHCheckedTable {
    private int productID;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private int checked;
//    private int deviated;
//    private  deviationValue;

    public WHCheckedTable(int productID, String productName, BigDecimal price, int quantity, int checked) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.checked = checked; // Initialize checked with the value of quantity
//        this.deviated = new SimpleIntegerProperty(0); // Initial deviated
//        this.deviationValue = new SimpleObjectProperty<>(BigDecimal.ZERO);
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
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }


    //    public IntegerProperty checkedProperty() {
//        return checked;
//    }
//
//    public int getDeviated() {
//        return deviated.get();
//    }
//
//    public void setDeviated(int deviated) {
//        this.deviated.set(deviated);
//    }
//
//    public IntegerProperty deviatedProperty() {
//        return deviated;
//    }
//
//    public BigDecimal getDeviationValue() {
//        return deviationValue.get();
//    }
//
//    public void setDeviationValue(BigDecimal deviationValue) {
//        this.deviationValue.set(deviationValue);
//    }
//
//    public ObjectProperty<BigDecimal> deviationValueProperty() {
//        return deviationValue;
//    }
//
//    private void updateDependentValues() {
//        int quantity = getQuantity();
//        int checked = getChecked();
//        int deviated = quantity - checked;
//        setDeviated(deviated);
//        setDeviationValue(BigDecimal.valueOf(deviated).multiply(getPrice()));
//    }
}
