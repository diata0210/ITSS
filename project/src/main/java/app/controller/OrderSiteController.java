package app.controller;

import app.container.DIContainer;
import app.models.OrderStatus;
import app.models.SiteOrder;
import app.services.OrderSiteServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderSiteController implements Initializable {

    OrderSiteServiceImp orderSiteServiceImp = DIContainer.getInstance(OrderSiteServiceImp.class);

    @FXML
    private TableColumn<SiteOrder, String> code;

    @FXML
    private ComboBox<String> listStatus;

    @FXML
    private TableColumn<SiteOrder, BigDecimal> finalPrice;

    @FXML
    private TextField inputText;

    @FXML
    private TableColumn<SiteOrder, String> site;

    @FXML
    private TableColumn<SiteOrder, String> status;

    @FXML
    private TableColumn<SiteOrder, Integer> stt;

    @FXML
    private TableView<SiteOrder> table;

    @FXML
    void addOrder(ActionEvent event) {

    }

    @FXML
    void filterByName(ActionEvent event) {

    }

    @FXML
    void filterByStatus(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }


    private ObservableList<SiteOrder> siteOrders;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listStatus.setItems(FXCollections.observableArrayList(
                "Đã nhận hàng",
                    "Đang giao hàng",
                    "Đã hủy" ,
                    "Chờ xác nhận",
                    "Đang lấy hàng"
        ));

        loadData();
    }

    private void loadData(){
//        stt.setCellValueFactory(new PropertyValueFactory<>("ID"));
//        code.setCellValueFactory(new PropertyValueFactory<>("siteOrderCode"));
//        site.setCellValueFactory(new PropertyValueFactory<>("siteName"));
//        finalPrice.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
//        status.setCellValueFactory(new PropertyValueFactory<>("oStatus"));
//        siteOrders = FXCollections.observableArrayList(orderSiteServiceImp.getAllSiteOrders());
//        table.setItems(siteOrders);
        System.out.println(orderSiteServiceImp);
    }

}
