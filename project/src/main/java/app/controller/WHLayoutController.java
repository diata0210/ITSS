package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WHLayoutController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadScreen("/app/project/HomePage.fxml");
    }

    @FXML
    void loadHome(ActionEvent event) {
        loadScreen("/app/project/HomePage.fxml");
    }

//    @FXML
//    void loadOrderFromSellMan(ActionEvent event) {
//    }

    @FXML
    void loadSiteOrder(ActionEvent event) {
        loadScreen("/app/project/DSDHPage.fxml");

    }

    @FXML
    void loadSiteOrderChecked(ActionEvent event) {loadScreen("/app/project/DSDHDKPage.fxml");}
}