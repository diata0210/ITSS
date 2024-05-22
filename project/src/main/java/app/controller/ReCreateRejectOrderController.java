package app.controller;

import app.models.*;
import app.repositories.ProductRepository;
import app.repositories.OrderToSiteRepository;
import app.repositories.SiteRepository;
import app.repositories.implement.ProductRepositoryImp;
import app.repositories.implement.OrderToSiteRepositoryImp;
import app.repositories.implement.SiteRepositoryImp;
import app.services.ProductServiceImp;
import app.services.OrderToSiteServiceImp;
import app.services.SiteServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ReCreateRejectOrderController implements Initializable {

    SiteServiceImp siteServiceImp = new SiteServiceImp();
    SiteRepository siteRepository = new SiteRepositoryImp();
    ProductServiceImp productServiceImp = new ProductServiceImp();
    ProductRepository productRepository = new ProductRepositoryImp();
    OrderToSiteServiceImp siteOrderServiceImp = new OrderToSiteServiceImp();
    OrderToSiteRepository orderToSiteRepository = new OrderToSiteRepositoryImp();

    private SiteOrder siteOrder;
    private List<SiteOrderDetail> siteOrderDetails;
    private List<Site> sites;
    private List<ProductSite> productSite;
    private int productId = 0;
    private int siteId = 0;
    private List<VehicleSite> vehicleSites;
    private ObservableList<ReCreateOrder> templist;
    private ObservableList<ReCreateOrder> filterList;
    @FXML
    private TableColumn<ReCreateOrder, Integer> ID;

    @FXML
    private TableColumn<ReCreateOrder, String> arrDate;

    @FXML
    private DatePicker arrivedDate;

    @FXML
    private TableColumn<ReCreateOrder, String> delete;

    @FXML
    private DatePicker deliverDate;

    @FXML
    private ComboBox<String> listProduct;

    @FXML
    private ComboBox<String> listSite;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<ReCreateOrder, BigDecimal> price;

    @FXML
    private TextField productValue;

    @FXML
    private TableColumn<ReCreateOrder, Integer> quantity;

    @FXML
    private TextField quantityOrder;

    @FXML
    private TextField quantityRequired;

    @FXML
    private TextField quantityStore;

    @FXML
    private TextField rejectCode;

    @FXML
    private TableColumn<ReCreateOrder, String> site;

    @FXML
    private TableView<ReCreateOrder> table;

    @FXML
    private TableColumn<ReCreateOrder, String> vehicle;

    @FXML
    private ComboBox<String> vehicelValue;

    @FXML
    void addAProduct(ActionEvent event) {
        int quantityO = Integer.parseInt(quantityOrder.getText());
        int currentQuantity = Integer.parseInt(quantityStore.getText());
        if(currentQuantity < quantityO){
            System.out.println("Không thể chọn số lượng đơn hàng lớn hơn kho");
            return ;
        }
        int newQuantity = currentQuantity - quantityO;

        quantityStore.setText(String.valueOf(newQuantity));
        quantityOrder.clear();

        Optional<ReCreateOrder> filter = filterList.stream()
                .filter(item -> item.getSName().equals(listSite.getValue()) && item.getProductName().equals(listProduct.getValue()))
                .findFirst();

        if (filter.isPresent()) {
            ReCreateOrder existingItem = filter.get();
            int updatedQuantity = existingItem.getQuantity() + quantityO;
            existingItem.setQuantity(updatedQuantity);
        } else {
            ReCreateOrder table = new ReCreateOrder(
                    listProduct.getValue(),
                    siteId,
                    listSite.getValue(),
                    quantityO,
                    productServiceImp.getProductById(productId).getPrice(),
                    vehicelValue.getValue(),
                    arrivedDate.getValue().toString()
            );
            System.out.println(listProduct.getValue());
            templist.add(table);
        }
        filterList.clear();
        filterList.addAll(templist.stream()
                .filter(temp -> temp.getProductName().equals(productValue.getText()))
                .collect(Collectors.toList()));
    }

    @FXML
    void addAllOrder(ActionEvent event) throws IOException {
        if (siteId == 0 || productId == 0 || vehicelValue.getValue() == null || arrivedDate.getValue() == null) {
            System.out.println("Vui lòng chọn đủ thông tin cần thiết");
            return;
        }

        // Tạo một đối tượng CreateSiteOrder
        CreateSiteOrder createSiteOrder = new CreateSiteOrder(
                siteOrder.getSellOrderID(),     // Điền orderID vào đây
                new ArrayList<>(), // Tạo một danh sách trống để lưu siteIDs
                new ArrayList<>(), // Tạo một danh sách trống để lưu productIDs
                new ArrayList<>()  // Tạo một danh sách trống để lưu quantities
        );

        for (ReCreateOrder item : templist) {
            createSiteOrder.getSiteIDs().add(item.getID());
            for (SiteOrderDetail orderDetail : siteOrderDetails) {
                if (orderDetail.getPName().equals(item.getProductName())) {
                    createSiteOrder.getProductIDs().add(orderDetail.getPID());
                    siteServiceImp.updateQuantityInProductSite(item.getID(), orderDetail.getPID(), item.getQuantity());
                    break;
                }
            }
            createSiteOrder.getQuantities().add(item.getQuantity());
        }

        siteOrderServiceImp.createOrder(createSiteOrder);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/project/OrderToSitePage.fxml"));
        Parent parent = loader.load();
        pane.getChildren().clear();
        pane.getChildren().add(parent);
    }

    @FXML
    void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/project/OrderToSitePage.fxml"));
        Parent parent = loader.load();
        pane.getChildren().clear();
        pane.getChildren().add(parent);
    }


    @FXML
    void filterSite(ActionEvent event) {
        Site quantityInStore = sites.stream()
                .filter(site -> site.getSname().equals(listSite.getValue()))
                .findFirst()
                .orElse(null);
        this.siteId = quantityInStore.getId();
        if(siteId != 0 && productId != 0  ){
            quantityStore.setText(String.valueOf(siteServiceImp.getQuantityInSite(siteId,productId)));
        }
        this.vehicleSites = siteServiceImp.getAllVehicleSites(siteId);
        if(vehicleSites == null){
            vehicelValue.setValue("Không có phương tiện vận chuyển");
            arrDate.setText("");
        }else{
            List<String> vehicleNames = vehicleSites.stream()
                    .map(VehicleSite::getVName)
                    .collect(Collectors.toList());
            ObservableList<String> observableVehicleNames = FXCollections.observableArrayList(vehicleNames);
            vehicelValue.setItems(observableVehicleNames);
        }
    }

    @FXML
    void filterProduct(ActionEvent event) {
        productValue.setText(listProduct.getValue());
        SiteOrderDetail selectedDetail = siteOrderDetails.stream()
                .filter(siteOrderDetail -> siteOrderDetail.getPName().equals(productValue.getText()))
                .findFirst()
                .orElse(null);
        this.productId = selectedDetail.getPID();
        if (selectedDetail != null) {
            quantityRequired.setText(String.valueOf(selectedDetail.getQuantity()));
        }
        if(siteId != 0 && productId != 0  ){
            quantityStore.setText(String.valueOf(siteServiceImp.getQuantityInSite(siteId,productId)));
        }
        filterList.clear();
        filterList.addAll(templist.stream()
                .filter(temp -> temp.getProductName().equals(productValue.getText()))
                .collect(Collectors.toList()));
    }

    @FXML
    void getItem(MouseEvent event) {

    }

    @FXML
    void filterVehicle(ActionEvent event) {
        // Find the selected vehicle site
        VehicleSite selectedVehicleSite = null;
        if (vehicelValue.getValue() != null) {
            selectedVehicleSite = vehicleSites.stream()
                    .filter(vehicleSite -> vehicleSite.getVName().equals(vehicelValue.getValue()))
                    .findFirst()
                    .orElse(null);
        }

        if (selectedVehicleSite != null) {
            // Parse the sendDate from the siteOrder
            String arrDateString = siteOrder.getSendDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate sendDate = LocalDate.parse(arrDateString, formatter);

            // Calculate the arrival date based on the sendDate and dateTrans from the selected vehicle site
            int dateTrans = selectedVehicleSite.getDateTrans();
            LocalDate arriveDate = sendDate.plusDays(dateTrans);

            // Set the arrivedDate value
            arrivedDate.setValue(arriveDate); // Assuming arrivedDateField is a DatePicker
        } else {
            // Handle case when no vehicle site is selected
            arrivedDate.setValue(null); // Clear the arrivedDateField
        }
    }

    public void loadFilter(){
        List<String> siteNames = sites.stream()
                .map(Site::getSname)
                .collect(Collectors.toList());
        ObservableList<String> observableSiteNames = FXCollections.observableArrayList(siteNames);
        listSite.setItems(observableSiteNames);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        siteServiceImp.setSiteRepository(siteRepository);
        productServiceImp.setProductRepository(productRepository);
        siteOrderServiceImp.setOrderSiteRepository(orderToSiteRepository);
        filterList = FXCollections.observableArrayList();

        this.sites = siteServiceImp.getAllSites();
        loadFilter();
        loadTable();
    }

    public void loadPage(SiteOrder siteOrder){
        this.siteOrder = siteOrder;
        loadTable();
        loadInfomation(siteOrder);
    }

    public void loadTable(){
        ID.setCellValueFactory(
                new PropertyValueFactory<>("ID")
        );
        site.setCellValueFactory(
                new PropertyValueFactory<>("sName")
        );
        quantity.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );
        price.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );
        vehicle.setCellValueFactory(
                new PropertyValueFactory<>("vehicle")
        );
        arrDate.setCellValueFactory(
                new PropertyValueFactory<>("arrDate")
        );
        delete.setCellValueFactory(
                new PropertyValueFactory<>("delete")
        );

        templist = FXCollections.observableArrayList();



        table.setItems(filterList);
    }

    public void loadInfomation(SiteOrder siteOrder){
        rejectCode.setText(String.valueOf(siteOrder.getID()));
        ObservableList<String> productNames = FXCollections.observableArrayList();

        this.siteOrderDetails = siteOrder.getSiteOrderDetails();

        for (SiteOrderDetail orderDetail : siteOrderDetails) {
            int productID = orderDetail.getPID();
            String productName = orderDetail.getPName();
            if (productName != null && !productNames.contains(productName)) {
                productNames.add(productName);
            }
        }

        listProduct.setItems(productNames);
        String deliveryDateString = siteOrder.getDeliveryDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate deliveryDate = LocalDate.parse(deliveryDateString, formatter);
        deliverDate.setValue(deliveryDate);
    }
}
