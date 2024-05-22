package app.controller;

import app.models.SiteOrder;
import app.models.WHCheckTable;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class WHController implements Initializable {
    WarehouseRepository warehouseRepository ;
    WarehouseServiceImp warehouseServiceImp ;
    private ObservableList<WHCheckTable> orderDetails;
    private FilteredList<WHCheckTable> filteredOrders;
    private SiteOrder siteOrder;

    @FXML
    private TableView<WHCheckTable> table;

    @FXML
    private TableColumn<WHCheckTable, Integer> stt;

    @FXML
    private TableColumn<WHCheckTable, Integer> productID;

    @FXML
    private TableColumn<WHCheckTable, String> productName;

    @FXML
    private TableColumn<WHCheckTable, BigDecimal> price;

    @FXML
    private TableColumn<WHCheckTable, Integer> quantity;

    @FXML
    private TableColumn<WHCheckTable, Integer> checked;

    @FXML
    private TableColumn<WHCheckTable, Integer> deviated;

    @FXML
    private TableColumn<WHCheckTable, BigDecimal> deviationValue;

    @FXML
    private AnchorPane pane;
    private void loadScreen(String fxml) {
        try {
            if (pane != null) {
                pane.getChildren().clear();
                pane.getChildren().add(FXMLLoader.load(getClass().getResource(fxml)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void confirm(ActionEvent event) {
        LocalDateTime now = LocalDateTime.now();
        int orderStatus = 4; // Trạng thái đơn hàng sau khi xác nhận

        int siteOrderID = this.siteOrder.getID(); // ID của đơn hàng
        BigDecimal actualValue = BigDecimal.ZERO;
        for (WHCheckTable item : orderDetails) {

            BigDecimal itemTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getChecked()));
            actualValue = actualValue.add(itemTotal);

            warehouseServiceImp.updateActualQuantity(siteOrderID, item.getProductID(), item.getChecked());
        }

        // Cập nhật trạng thái đơn hàng sau khi tất cả các sản phẩm đã được cập nhật
        try {
            System.out.println(siteOrder.getActualValue());
            warehouseServiceImp.updateSiteOrder(siteOrderID, now,actualValue,orderStatus);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        loadScreen("/app/project/DSDHPage.fxml");
    }
    @FXML
    private TextField finalPrice;
    @FXML
    private Text code;


    @FXML
    private TextField inputValue;

    @FXML
    private ComboBox<String> statuspValue;

    @FXML
    private TextField orderCodeValue;

    @FXML
    private TextField statusValue;

    @FXML
    private TextField siteValue;

    @FXML
    void back(MouseEvent event) {
        // Your code here
    }

    @FXML
    void reCreateOrder(ActionEvent event) {
        // Your code here
    }

    public void initializeTable() {
        stt.setCellValueFactory(column ->
                new ReadOnlyObjectWrapper<>(table.getItems().indexOf(column.getValue()) + 1)
        );
        productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        checked.setCellValueFactory(new PropertyValueFactory<>("checked"));
        checked.setCellFactory(column -> new TextFieldTableCell());
        deviated.setCellValueFactory(new PropertyValueFactory<>("deviated"));
        deviationValue.setCellValueFactory(new PropertyValueFactory<>("deviationValue"));
    }

    public void loadData(SiteOrder siteOrder) {
        this.siteOrder = siteOrder;
        List<WHCheckTable> whChecks = siteOrder.getWHChecks();
        System.out.println(whChecks.get(1).getPrice());
        orderDetails = FXCollections.observableArrayList(whChecks);
        table.setItems(orderDetails);
        code.setText(String.valueOf(siteOrder.getID()));
//        orderCodeValue.setText(String.valueOf(siteOrder.getID()));
//        statusValue.setText(siteOrder.getOStatus());
//        siteValue.setText(siteOrder.getSiteName());
//        finalPrice.setText(String.valueOf(siteOrder.getFinalPrice()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warehouseRepository = new WarehouseRepositoryImp();
        warehouseServiceImp = new WarehouseServiceImp();
        warehouseServiceImp.setWarehouseRespository(warehouseRepository);
        initializeTable();
    }
}
