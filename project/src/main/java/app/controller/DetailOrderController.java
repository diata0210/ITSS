package app.controller;

import app.models.SiteOrder;
import app.models.SiteOrderDetail;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.math.BigDecimal;

public class DetailOrderController {


    private ObservableList<SiteOrderDetail> orderDetails;
    private FilteredList<SiteOrderDetail> filteredOrders;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<SiteOrderDetail> table;

    @FXML
    private TableColumn<SiteOrderDetail, Integer> ID;

    @FXML
    private TableColumn<SiteOrderDetail, String> pName;

    @FXML
    private TableColumn<SiteOrderDetail, BigDecimal> pPrice;

    @FXML
    private TableColumn<SiteOrderDetail, Integer> quantity;

    @FXML
    private TableColumn<SiteOrderDetail, String> status;

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
        String filterNameValue = inputValue.getText().toLowerCase();
        filteredOrders.setPredicate(siteOrderDetail -> {
            // If filter text is empty, display all orders
            if (filterNameValue == null || filterNameValue.isEmpty()) {
                return true;
            }
            // Compare site order name of every site order with filterNameValue
            return siteOrderDetail.getPName().toLowerCase().contains(filterNameValue);
        });
    }

    private SiteOrder orders;

    @FXML
    private TextField statusValue;

    @FXML
    void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/project/OrderToSitePage.fxml"));
        Parent parent = loader.load();
        pane.getChildren().clear();
        pane.getChildren().add(parent);
    }

    @FXML
    void reCreateOrder(ActionEvent event) throws  IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/project/ReCreateRejectOrder.fxml"));
        Parent parent = loader.load();
        pane.getChildren().clear();
        pane.getChildren().add(parent);
        ReCreateRejectOrderController controller = loader.getController();
        controller.loadPage(orders);
    }


    @FXML
    private TextField siteValue;

    public void initializeTable(){
        ID.setCellValueFactory(new PropertyValueFactory<>("pID"));
        pName.setCellValueFactory(new PropertyValueFactory<>("pName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        pPrice.setCellValueFactory(new PropertyValueFactory<>("pPrice"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void loadData(SiteOrder siteOrder){
        this.orders = siteOrder;
        orderDetails = FXCollections.observableArrayList(siteOrder.getSiteOrderDetails());
        filteredOrders = new FilteredList<>(orderDetails, p -> true);
        initializeTable();
        table.setItems(filteredOrders);
        orderCodeValue.setText(String.valueOf(siteOrder.getID()));
        statusValue.setText(siteOrder.getOStatus());
        siteValue.setText(siteOrder.getSiteName());
        finalPrice.setText(String.valueOf(siteOrder.getFinalPrice()));
    }
}
