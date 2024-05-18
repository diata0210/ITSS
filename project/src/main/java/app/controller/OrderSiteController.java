package app.controller;

import app.models.SiteOrder;
import app.models.SiteOrderDetail;
import app.models.SiteOrderTable;
import app.repositories.OrderSiteRepository;
import app.repositories.implement.OrderSiteRepositoryImp;
import app.services.OrderSiteServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class OrderSiteController implements Initializable {

    OrderSiteRepository orderSiteRepository = new OrderSiteRepositoryImp();
    OrderSiteServiceImp orderSiteServiceImp = new OrderSiteServiceImp();

    private ObservableList<SiteOrderTable> siteOrders;
    private FilteredList<SiteOrderTable> filteredOrders;

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
    private TableView<SiteOrderTable> table;

    @FXML
    void addOrder(ActionEvent event) {

    }

    @FXML
    void filterByName(ActionEvent event) {
        String filterNameValue = inputText.getText();
        filteredOrders.setPredicate(siteOrderTable -> {
            // If filter text is empty, display all orders
            if (filterNameValue == null || filterNameValue.isEmpty()) {
                return true;
            }
            // Compare site order code of every site order with filterNameValue
            return siteOrderTable.getSiteName().toLowerCase().contains(filterNameValue.toLowerCase());
        });
    }

    @FXML
    void filterByStatus(ActionEvent event) {
        String filterStatusValue = listStatus.getValue();
        filteredOrders.setPredicate(siteOrderTable -> {
            // If filter text is empty, display all orders
            if (filterStatusValue == null || filterStatusValue.isEmpty()) {
                return true;
            }
            // Compare status of every site order with filterStatusValue
            return siteOrderTable.getOStatus().equals(filterStatusValue);
        });
    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    private AnchorPane pane;

    @FXML
    void getItem(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/project/DetailOrderPage.fxml"));
        Parent parent = loader.load();
        pane.getChildren().clear();
        pane.getChildren().add(parent);

        DetailOrderController controller = loader.getController();
        SiteOrder siteorder = orderSiteServiceImp.getSiteOrderById(table.getSelectionModel().getSelectedItem().getID());
        controller.loadData(siteorder);
//        } else {
//            // Handle the case where no item is selected
//            System.out.println("No item selected.");
//        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderSiteServiceImp.setOrderSiteRepository(orderSiteRepository);
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
         List<SiteOrder> orders = orderSiteServiceImp.getAllSiteOrders();
         stt.setCellValueFactory(new PropertyValueFactory<>("ID"));
         site.setCellValueFactory(new PropertyValueFactory<>("siteName"));
         finalPrice.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
         status.setCellValueFactory(new PropertyValueFactory<>("oStatus"));
         siteOrders = FXCollections.observableArrayList();
         int stt;
         String site, status, code;
         BigDecimal finalPrice;
         for(SiteOrder order : orders){
             stt = order.getID();
             site = order.getSiteName();
             status = order.getOStatus();
             finalPrice = order.getFinalPrice();
             System.out.println(order.getID());
             SiteOrderTable siteorder = new SiteOrderTable(site,finalPrice, status, stt);
             siteOrders.add(siteorder);
         }
        filteredOrders = new FilteredList<>(siteOrders, p -> true);
        table.setItems(filteredOrders);
    }

}
