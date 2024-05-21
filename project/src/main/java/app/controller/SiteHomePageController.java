package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import app.models.SiteHomePageModel;
import app.models.ProductSite;
public class SiteHomePageController implements Initializable{
    private int userId;
    private SiteHomePageModel model = new SiteHomePageModel();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model.loadData(userId);
        setData();
    }
    private void setData(){
        name.setText(model.getName());
        code.setText("" + model.getCode());
        address.setText(model.getAddress());
        airDelivery.setText("" + model.getAirDelivery());
        shipDelivery.setText("" + model.getShipDelivery());
        table.setItems(model.getSiteProducts());
        codeCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }
    @FXML
    private Text address;

    @FXML
    private Text airDelivery;

    @FXML
    private Text code;

    @FXML
    private Text name;

    @FXML
    private TextField search;

    @FXML
    private Text shipDelivery;

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
    void pressedAddProducts(ActionEvent event) {

    }

    @FXML
    void pressedEdit(MouseEvent event) {

    }

    @FXML
    void pressedHome(ActionEvent event) {

    }

    @FXML
    void pressedSearch(MouseEvent event) {

    }
    public void setUserId(int userId){
        this.userId = userId;
    }
}
