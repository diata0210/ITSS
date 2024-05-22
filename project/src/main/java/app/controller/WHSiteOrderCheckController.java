package app.controller;

import app.models.SiteOrder;
import app.repositories.WarehouseRepository;
import app.repositories.implement.WarehouseRepositoryImp;
import app.services.WarehouseServiceImp;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class WHSiteOrderCheckController implements Initializable {
    WarehouseRepository warehouseRepository ;
    WarehouseServiceImp warehouseServiceImp ;
    private ObservableList<SiteOrder> siteOrders;
    private FilteredList<SiteOrder> filteredOrders;
    @FXML
    private TableColumn<SiteOrder, Number> numberColumn;
    @FXML
    private TableColumn<SiteOrder, Integer> stt;

    @FXML
    private TableColumn<SiteOrder, String> orderID;

    @FXML
    private TableColumn<SiteOrder, String> vehicleID;
    @FXML
    private TableColumn<SiteOrder, LocalDateTime> arrivalDate;
    @FXML
    private TableColumn<SiteOrder,Integer> siteid;

    @FXML
    private TableColumn<SiteOrder, BigDecimal> finalPrice;

    @FXML
    private TableColumn<SiteOrder, String> status;

    @FXML
    private TableView<SiteOrder> table;
    @FXML
    void search(ActionEvent event) {
        // Implement search functionality if needed
    }

    @FXML
    private AnchorPane pane;
    @FXML
    void getItem(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/project/NKPage.fxml"));
        Parent parent = loader.load();
        pane.getChildren().clear();
        pane.getChildren().add(parent);

        NKController controller = loader.getController();
        SiteOrder siteorder = warehouseServiceImp.getByIdChecked(table.getSelectionModel().getSelectedItem().getID());
        controller.loadData(siteorder);
//        } else {
//            // Handle the case where no item is selected
//            System.out.println("No item selected.");
//        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Hello");
        warehouseRepository = new WarehouseRepositoryImp();
        warehouseServiceImp = new WarehouseServiceImp();
        warehouseServiceImp.setWarehouseRespository(warehouseRepository);
        loadData();
    }

    private void loadData() {
        try {

            List<SiteOrder> orders = warehouseServiceImp.getSiteOrdersChecked();

            if (orders.isEmpty()) {
                System.out.println("Không có dữ liệu từ cơ sở dữ liệu.");
            } else {
                System.out.println("Dữ liệu từ cơ sở dữ liệu đã được tải thành công.");
            }


            orderID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            vehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));

            arrivalDate.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
            status.setCellValueFactory(new PropertyValueFactory<>("oStatus"));
            stt.setCellValueFactory(column ->
                    new ReadOnlyObjectWrapper<>(table.getItems().indexOf(column.getValue()) + 1)
            );
            stt.setSortable(false);
            siteOrders = FXCollections.observableArrayList(orders);
            filteredOrders = new FilteredList<>(siteOrders, p -> true);
            table.setItems(filteredOrders);
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi tải dữ liệu từ cơ sở dữ liệu: " + e.getMessage());
        }
    }

}

