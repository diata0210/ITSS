package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import app.repositories.AddProductsRepository;
import app.models.ProductSite;
public class AddProductsController implements Initializable {
    private int siteId;
    private AddProductsRepository repository = new AddProductsRepository();
    private ObservableList<ProductSite> Products;
    @FXML
    private TableView<ProductSite> table;

    @FXML
    private TableColumn<ProductSite, Integer> codeCol;

    @FXML
    private TableColumn<ProductSite, String> nameCol;

    @FXML
    private TableColumn<ProductSite, Integer> priceCol;

    @FXML
    private TableColumn<ProductSite, Integer> quantityCol; 

    @FXML
    private TableColumn<ProductSite, String> unitCol;

    @FXML
    private TextField search;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repository.loadData();
        setupTable();
    }

    private void setupTable() {
        Products = repository.getProducts();
        table.setItems(Products);
        table.getSortOrder().add(quantityCol);
        codeCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitCol.setCellValueFactory(new PropertyValueFactory<>("unit"));
        setupEditableQuantityCell();
    }

    private void setupEditableQuantityCell() {
        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityCol.setOnEditCommit(event -> {
            Integer newValue = event.getNewValue();
            if (newValue != null && newValue.intValue() > 0) {
                event.getRowValue().setQuantity(newValue.intValue());
                table.sort();
            }
            else{
                showAlert("Error", "Invalid Input", "Please enter a valid positive integer.");
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setQuantity(0);
                table.refresh();
            }
        });
    }
    
    private static void showAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    void pressedSave(MouseEvent event) {
    ObservableList<ProductSite> addProducts = FXCollections.observableArrayList();
    for (ProductSite product : Products) {
        product.setSiteId(siteId);
        if (product.getQuantity() != 0) {
            addProducts.add(product);
        }
    }
    repository.addProducts(addProducts);

    try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/project/SiteHomepage.fxml"));
        Parent homePage = loader.load();
        SiteHomePageController controller = loader.getController();
        controller.setUserId(3);
        controller.initialize(null, null);
        Scene scene = new Scene(homePage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }
}
