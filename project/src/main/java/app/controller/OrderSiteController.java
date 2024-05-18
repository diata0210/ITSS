package app.controller;

import app.models.SiteOrder;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderSiteController implements Initializable {

    @FXML
    private ComboBox<String> listStatus;

    @FXML
    private TableColumn<SiteOrder, > code;

    @FXML
    private TableColumn<?, ?> finalPrice;

    @FXML
    private TableColumn<?, ?> site;

    @FXML
    private TableColumn<?, ?> status;

    @FXML
    private TableColumn<?, ?> stt;


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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listStatus.setItems(FXCollections.observableArrayList(
                "Đã nhận hàng",
                    "Đang giao hàng",
                    "Đã hủy" ,
                    "Chờ xác nhận",
                    "Đang lấy hàng"
        ));
    }
}
