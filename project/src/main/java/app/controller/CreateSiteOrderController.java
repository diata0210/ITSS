package app.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import app.models.CreateSiteOrder;
import app.models.OrderView;
import app.models.SellOrderDetail;
import app.models.SellOrder;
import app.models.Site;
import app.models.SiteOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import app.models.SiteOrderDetail;
import app.models.VehicleSite;
import app.models.OrderView;
import app.repositories.ProductRepository;
import app.repositories.SellOrderRepository;
import app.repositories.OrderToSiteRepository;
import app.repositories.SiteRepository;
import app.repositories.implement.ProductRepositoryImp;
import app.repositories.implement.OrderToSiteRepositoryImp;
import app.repositories.implement.SiteRepositoryImp;
import app.repositories.implement.SellOrderRepositoryImp;
import app.services.ProductServiceImp;
import app.services.SellOrderServiceImp;
import app.services.OrderToSiteServiceImp;
import app.services.SiteServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.beans.property.*;
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
import javafx.scene.layout.AnchorPane;

public class CreateSiteOrderController implements Initializable {


    SiteServiceImp siteServiceImp = new SiteServiceImp();
    SiteRepository siteRepository = new SiteRepositoryImp();
    ProductServiceImp productServiceImp = new ProductServiceImp();
    ProductRepository productRepository = new ProductRepositoryImp();
    OrderToSiteServiceImp OrderToSiteServiceImp = new OrderToSiteServiceImp();
    OrderToSiteRepository OrderToSiteRepository = new OrderToSiteRepositoryImp();
    SellOrderRepository sellOrderRepository = new SellOrderRepositoryImp();
    SellOrderServiceImp sellOrderServiceImp = new SellOrderServiceImp();

    private SiteOrder siteOrder;        
    private SellOrder mainSellOrder; //might not use
    private List<SellOrder> sellOrders;
    private List<OrderView> siteOrderView;
    private List<SellOrderDetail> sellOrderDetails;
    private List<SiteOrderDetail> siteOrderDetails;
    private List<Site> sites;
    private int productID = 0; //global (in this controller) to control selected product
    private int siteId = 0; //similar to productID but site
    private List<VehicleSite> vehicleSites; //not using yet/not know
    private ObservableList<SiteOrder> templist; //we take order from this list
    private ObservableList<SiteOrder> filterList; //filtered list, using on filtering and check product in the order table
    private ObservableList<OrderView> orderView;
    private ObservableList<OrderView> filterView;

    @FXML
    private TableColumn<OrderView, Integer> ID; //in order

    @FXML
    private TableColumn<OrderView, String> arrDate; //in order

    @FXML
    private TableColumn<OrderView, String> delete;

    @FXML
    private TableColumn<OrderView, Integer> quantity; //in oderDetail

    @FXML
    private TableColumn<OrderView, BigDecimal> price; //in order

    @FXML
    private TableColumn<OrderView, String> site; //in order

    @FXML
    private TableView<OrderView> table;

    @FXML
    private TableColumn<OrderView, String> vehicle;  //in order


    @FXML
    private DatePicker arrivedDate;

    @FXML
    private DatePicker deliverDate;

    @FXML
    private TextField finalPrice;

    @FXML
    private ComboBox<String> listProduct;

    @FXML
    private ComboBox<String> listSite;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField productValue;

    
    @FXML
    private TextField quantityOrder;

    @FXML
    private TextField quantityRequired;

    @FXML
    private TextField quantityStore;

    @FXML
    private ComboBox<Integer> orderCode;

    @FXML
    private ComboBox<String> vehicelValue;


    //Choose 
    @FXML
    void onChooseOrder(ActionEvent event){
        
        SellOrder sellOrder = new SellOrder();      //allocate new sell order instance may be redundance due to the mainSellOrder
        int orderID = Integer.parseInt(orderCode.getValue().toString());  //get the orderId value from interface
        sellOrder = sellOrderServiceImp.getById(orderID);       //get the order 
        System.out.println("This is " + sellOrder.getOrderID());
        sellOrderDetails = sellOrder.getSellOrderDetails(); //get the order detail
        mainSellOrder = sellOrder;
        ObservableList<String> pNameObservableList = FXCollections.observableArrayList();
        for (SellOrderDetail sellOrderDetail : sellOrderDetails) {
            pNameObservableList.add(sellOrderDetail.getPName());
            //quantityRequired.setText(Integer.toString(sellOrderDetail.getQuantity()));  //using wrong way
            System.out.println("=============================================================================");
            System.out.println(sellOrderDetail.getPName());
        }
        listProduct.setItems(pNameObservableList);
         String deliDateString = mainSellOrder.getDeliveryDate().toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deliDate = LocalDate.parse(deliDateString, formatter);

            // Calculate the arrival date based on the sendDate and dateTrans from the selected vehicle sit

            // Set the arrivedDate value
            deliverDate.setValue(deliDate); // Assuming arrivedDateField is a DatePicker
         //set the desired date of the order
    }

    //we use 1 sellOrder and 1 list of sellOrderDetail cuz we only work with 1 sell order at the time


    
    @FXML
    void filterProduct(ActionEvent event){
        productValue.setText(listProduct.getValue()); //set the product name in textfield
        SellOrderDetail selectedDetail = sellOrderDetails.stream()              //search the detail in the siteOrderDetails of the SiteOrder
                .filter(sellOrderDetail -> sellOrderDetail.getPName().equals(productValue.getText()))
                .findFirst()
                .orElse(null);
        this.productID = selectedDetail.getProductID();       //get PID for searching in site
        if (selectedDetail != null) {
            quantityRequired.setText(String.valueOf(selectedDetail.getQuantity()));
        }
        if(siteId != 0 && productID != 0  ){
            quantityStore.setText(String.valueOf(siteServiceImp.getQuantityInSite(siteId,productID))); //using pid to check product available in site
        }
    }

    @FXML
    void filterSite(ActionEvent event) {
        Site quantityInStore = sites.stream()
                .filter(site -> site.getSname().equals(listSite.getValue()))
                .findFirst()
                .orElse(null);
        this.siteId = quantityInStore.getId();
        if(siteId != 0 && productID != 0  ){
            quantityStore.setText(String.valueOf(siteServiceImp.getQuantityInSite(siteId,productID)));
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
    void addAProduct(ActionEvent event){
        int quantityO = Integer.parseInt(quantityOrder.getText());
        int currentQuantity = Integer.parseInt(quantityStore.getText());
        int newQuantity;
        if(currentQuantity < quantityO){
            System.out.println("Không thể chọn số lượng đơn hàng lớn hơn kho");
            return ;
        }
        //###########################################################################################3
        if (siteId == 0 || productID == 0 || vehicelValue.getValue() == null || arrivedDate.getValue() == null) {
            System.out.println("Vui lòng chọn đủ thông tin cần thiết");
            return;
        }else{
         newQuantity = currentQuantity - quantityO;
    }

        quantityStore.setText(String.valueOf(newQuantity));
        quantityOrder.clear();
        Optional<SiteOrder> siteFilter = filterList.stream().filter(item -> item.getSiteName().equals(listSite.getValue())).findFirst();
        if (siteFilter.isPresent()) {
           Optional<SiteOrderDetail> productFilter = siteFilter.get().getSiteOrderDetails().stream() //get the product
                                                                                           .filter(item -> item.getPName().equals(listProduct.getValue())).findFirst();
            if(productFilter.isPresent()){
                    SiteOrderDetail existingItem = productFilter.get();
                    existingItem.setQuantity(existingItem.getQuantity() + quantityO);
                Optional<OrderView> filter = orderView.stream()
                        .filter(item -> item.getSName().equals(listSite.getValue()) && item.getProductName().equals(listProduct.getValue()))
                        .findFirst();
                OrderView existingItemView = filter.get();
                int updatedQuantity = Integer.parseInt(existingItemView.getQuantity().toString()) + quantityO;
                System.out.println(updatedQuantity);
                existingItemView.setQuantity(updatedQuantity);
                BigDecimal total = new BigDecimal(updatedQuantity);
                total = total.multiply(productServiceImp.getProductById(productID).getPrice());
                existingItemView.setPrice(total);

            //get the selected SiteOrder, then change 
            }else{
                SiteOrderDetail newProduct = new SiteOrderDetail();
                newProduct.setQuantity(quantityO);
                newProduct.setPName(listProduct.getValue());
                newProduct.setPPrice(productServiceImp.getProductById(productID).getPrice());
                newProduct.setPID(productID);
                siteFilter.get().getSiteOrderDetails().add(newProduct);
                Optional<SiteOrder> currOrder = templist.stream().filter(siteOrder -> siteOrder.getSiteName().equals(listSite.getValue())).findFirst();
                currOrder.get().getSiteOrderDetails().add(newProduct);
                BigDecimal total = new BigDecimal(quantityO);
                total = total.multiply(productServiceImp.getProductById(productID).getPrice());
                OrderView tableView = new OrderView(
                    listProduct.getValue(),
                    siteId,
                    listSite.getValue(),
                    quantityO,
                    total,
                    vehicelValue.getValue(),
                    arrivedDate.getValue().toString()
            );
            System.out.println(tableView);
            orderView.add(tableView);
            }
            
        } else {
            SiteOrderDetail newProduct = new SiteOrderDetail();
                newProduct.setQuantity(quantityO);
                newProduct.setPName(listProduct.getValue());
                newProduct.setPPrice(productServiceImp.getProductById(productID).getPrice());
                newProduct.setPID(productID);
                newProduct.setStatus(1);
                List<SiteOrderDetail> newSiteOrderDetails = new ArrayList<>();
                newSiteOrderDetails.add(newProduct);
                SiteOrder table = new SiteOrder();
                table.setSiteName(listSite.getValue());
                table.setDeliveryDate(arrivedDate.getValue().toString());
                table.setSiteOrderDetails(newSiteOrderDetails);
                BigDecimal total = new BigDecimal(quantityO);
                total = total.multiply(productServiceImp.getProductById(productID).getPrice());
                OrderView tableView = new OrderView(
                    listProduct.getValue(),
                    siteId,
                    listSite.getValue(),
                    quantityO,
                    total,
                    vehicelValue.getValue(),
                    arrivedDate.getValue().toString()
            );
             System.out.println(tableView);
            orderView.add(tableView);
            templist.add(table);
            
            //KBT THEM CAI GI TAI THANG LAM MODEL NGU VKL
            
        }
        filterList.clear();
        filterList.addAll(templist.stream()
                .filter(temp -> temp.getSiteOrderDetails().stream().anyMatch(temps -> temps.getPName().equals(productValue.getText())))
                .collect(Collectors.toList()));
        filterView.clear();
        filterView.addAll(orderView.stream()
                .filter(temp -> temp.getProductName().equals(productValue.getText()))
                .collect(Collectors.toList()));
    }

    @FXML
    void addAllOrder(ActionEvent event) throws IOException {
        if (siteId == 0 || productID == 0 || vehicelValue.getValue() == null || arrivedDate.getValue() == null) {
            System.out.println("Vui lòng chọn đủ thông tin cần thiết");
            return;
        }

        // Tạo một đối tượng CreateSiteOrder
        CreateSiteOrder createSiteOrder = new CreateSiteOrder(
                mainSellOrder.getOrderID(),     // Điền orderID vào đây
                new ArrayList<>(), // Tạo một danh sách trống để lưu siteIDs
                new ArrayList<>(), // Tạo một danh sách trống để lưu productIDs
                new ArrayList<>()  // Tạo một danh sách trống để lưu quantities
        );

        //chon site, sau do chon tung product trong site dc dat, sau do cap nhat trong kho cua site

        for (SiteOrder item : templist) {          //we take all order in temp list, in this, i assume we only work with site order, sell order is done
            createSiteOrder.getSiteIDs().add(item.getID()); //get the site id first, we put it in permutation order (site,product) and put it in the creatSiteOrder model/form
            //for example: site1,product1,10; site1,product2,20;...;siten,productn,0;
            siteOrderDetails = item.getSiteOrderDetails();
            for (SiteOrderDetail orderDetail : siteOrderDetails){
                createSiteOrder.getProductIDs().add(orderDetail.getPID()); //add productID to the createSiteOrder model/form
                siteServiceImp.updateQuantityInProductSite(item.getID(), orderDetail.getPID() , orderDetail.getQuantity());
                createSiteOrder.getQuantities().add(orderDetail.getQuantity());
            }
        }

        OrderToSiteServiceImp.createOrder(createSiteOrder);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/project/OrderToSitePage.fxml"));
        Parent parent = loader.load();
        pane.getChildren().clear();
        pane.getChildren().add(parent);
    }

   @FXML
    void onChangeVehicle(ActionEvent event) {
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
            String arrDateString = mainSellOrder.getSendDate().toString();
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
        System.out.println(siteNames);
        ObservableList<String> observableSiteNames = FXCollections.observableArrayList(siteNames);
        listSite.setItems(observableSiteNames);
    }
    
    public void loadOrder(){
        List<Integer> orderID = sellOrders.stream().map(SellOrder::getOrderID).collect(Collectors.toList());
        ObservableList<Integer> observableSellOrderNames = FXCollections.observableArrayList(orderID);
        orderCode.setItems(observableSellOrderNames);
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

        orderView = FXCollections.observableArrayList();
        


        table.setItems(filterView);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        siteServiceImp.setSiteRepository(siteRepository);
        productServiceImp.setProductRepository(productRepository);
        OrderToSiteServiceImp.setOrderSiteRepository(OrderToSiteRepository);
        sellOrderServiceImp.setSellOrderRepository(sellOrderRepository);
        filterView = FXCollections.observableArrayList();
        filterList = FXCollections.observableArrayList();
        templist = FXCollections.observableArrayList();
        this.sellOrders = sellOrderServiceImp.getAlls();
        this.sites = siteServiceImp.getAllSites();
        loadFilter();
        loadTable();
        loadOrder();
    }
    

}
