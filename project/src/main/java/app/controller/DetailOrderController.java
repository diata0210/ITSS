package app.controller;

import app.models.SiteOrder;
import app.models.SiteOrderDetail;
import app.models.SiteOrderDetailTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DetailOrderController implements Initializable {


    private ObservableList<SiteOrderDetailTable> orderDetails;
    private FilteredList<SiteOrderDetailTable> filteredOrders;

    @FXML
    private TableView<SiteOrderDetailTable> table;

    @FXML
    private TableColumn<SiteOrderDetailTable, Integer> ID;

    @FXML
    private TableColumn<SiteOrderDetailTable, String> pName;

    @FXML
    private TableColumn<SiteOrderDetailTable, BigDecimal> pPrice;

    @FXML
    private TableColumn<SiteOrderDetailTable, Integer> quantity;

    @FXML
    private TableColumn<SiteOrderDetailTable, String> status;

    @FXML
    private Button ReCreateOrder;

    @FXML
    private TextField finalPrice;


    @FXML
    private TextField inputValue;

    @FXML
    private ComboBox<String> statuspValue;

    @FXML
    private TextField orderCodeValue;

    @FXML
    void filterByName(ActionEvent event) {

    }

    @FXML
    void filterByStatus(ActionEvent event) {

    }

    @FXML
    private TextField statusValue;

    @FXML
    void back(MouseEvent event) {

    }

    @FXML
    void reCreateOrder(ActionEvent event) {

    }

    @FXML
    private TextField siteValue;

    public void initializeTable(){
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        pName.setCellValueFactory(new PropertyValueFactory<>("pName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        pPrice.setCellValueFactory(new PropertyValueFactory<>("pPrice"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void loadData(SiteOrder siteOrder){
        orderDetails = FXCollections.observableArrayList();

        String productName, status;
        int id, pquantity;
        BigDecimal pPrice;
        initializeTable();
        List<SiteOrderDetail> siteOrderDetails = siteOrder.getSiteOrderDetails();
        for(SiteOrderDetail siteOrderDetail: siteOrderDetails){
            id = siteOrderDetail.getPID();
            productName = siteOrderDetail.getPName();
            pquantity = siteOrderDetail.getQuantity();
            pPrice = siteOrderDetail.getPPrice();
            status = siteOrderDetail.getStatus();
            SiteOrderDetailTable order = new SiteOrderDetailTable(id, productName, pquantity, pPrice, status);
            orderDetails.add(order);
        }
        table.setItems(orderDetails);

        // set title
        orderCodeValue.setText(String.valueOf(siteOrder.getID()));
        statusValue.setText(siteOrder.getOStatus());
        siteValue.setText(siteOrder.getSiteName());
        finalPrice.setText(String.valueOf(siteOrder.getFinalPrice()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
