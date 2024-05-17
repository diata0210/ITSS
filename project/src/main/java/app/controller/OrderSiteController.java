package app.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderSiteController implements Initializable {

    @FXML
    private TextField filterByName;

    @FXML
    private ComboBox<String> filterByStatus;

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
        filterByStatus.setItems(FXCollections.observableArrayList(
                "Đã nhận hàng",
                    "Đang giao hàng",
                    "Đã hủy" ,
                    "Chờ xác nhận",
                    "Đang lấy hàng"
        ));
    }
}
