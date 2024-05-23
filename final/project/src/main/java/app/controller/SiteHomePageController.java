package app.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.math.BigDecimal;
import app.models.ProductSite;
import app.repositories.implement.SiteHomePageRepositoryImp;
import app.repositories.SiteHomePageRepository;
public class SiteHomePageController implements Initializable{
    private int userId;
    private SiteHomePageRepository repository = new SiteHomePageRepositoryImp();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repository.loadData(userId);
        setData();
    }
    private void setData(){
        name.setText(repository.getName());
        code.setText("" + repository.getCode());
        address.setText(repository.getAddress());
        airDelivery.setText("" + repository.getAirDelivery());
        shipDelivery.setText("" + repository.getShipDelivery());
        table.setItems(repository.getSiteProducts());
        codeCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitCol.setCellValueFactory(new PropertyValueFactory<>("unit"));
    }
    private Parent oldContent;
    @FXML
    private Pane centerPane;

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
    private TableColumn<ProductSite, BigDecimal> priceCol;

    @FXML
    private TableColumn<ProductSite, Integer> quantityCol;

    @FXML
    private TableColumn<ProductSite, String> unitCol;
    @FXML
    void pressedAddProducts(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/project/AddProducts.fxml"));
            Parent newContent = loader.load();
            oldContent = (Pane)centerPane.getChildren().get(0);
            centerPane.getChildren().clear();
            centerPane.getChildren().add(newContent);
            AddProductsController controller = loader.getController();
            controller.setSiteId(repository.getCode());
            controller.initialize(null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void pressedHome(MouseEvent event) {
        if (oldContent != null) {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(oldContent);
            repository.loadData(userId);
            setData();
        }
        
    }

    public void setUserId(int userId){
        this.userId = userId;
    }
}
