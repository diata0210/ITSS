package app.controller;

import app.models.SiteOrder;
import app.repositories.OrderToSiteRepository;
import app.repositories.implement.OrderToSiteRepositoryImp;
import app.services.OrderToSiteServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderToSiteController implements Initializable {

    OrderToSiteRepository siteOrderRepository = new OrderToSiteRepositoryImp();
    OrderToSiteServiceImp siteOrderServiceImp = new OrderToSiteServiceImp();

    private ObservableList<SiteOrder> siteOrders;
    private FilteredList<SiteOrder> filteredOrders;

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

    private String statusValue;
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
        this.statusValue = listStatus.getValue();
        filteredOrders.setPredicate(siteOrderTable -> {
            // If filter text is empty, display all orders
            if (statusValue == null || statusValue.isEmpty()) {
                return true;
            }
            // Compare status of every site order with filterStatusValue
            return siteOrderTable.getOStatus().equals(statusValue);
        });
    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    private AnchorPane pane;

    @FXML
    void getItem(MouseEvent event) throws IOException {
        if(table.getSelectionModel().getSelectedItem().getOStatus().equals("Đã hủy") ){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/project/DetailOrderPage.fxml"));
            Parent parent = loader.load();
            pane.getChildren().clear();
            pane.getChildren().add(parent);

            DetailOrderController controller = loader.getController();
            SiteOrder siteorder = siteOrderServiceImp.getSiteOrderById(table.getSelectionModel().getSelectedItem().getID());
            controller.loadData(siteorder);
        }

//        } else {
//            // Handle the case where no item is selected
//            System.out.println("No item selected.");
//        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        siteOrderServiceImp.setOrderSiteRepository(siteOrderRepository);
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
         List<SiteOrder> orders = siteOrderServiceImp.getAllOrderToSite();
         stt.setCellValueFactory(new PropertyValueFactory<>("ID"));
         site.setCellValueFactory(new PropertyValueFactory<>("siteName"));
         finalPrice.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
         status.setCellValueFactory(new PropertyValueFactory<>("oStatus"));
         siteOrders = FXCollections.observableArrayList(orders);
        filteredOrders = new FilteredList<>(siteOrders, p -> true);
        table.setItems(filteredOrders);
    }

}
