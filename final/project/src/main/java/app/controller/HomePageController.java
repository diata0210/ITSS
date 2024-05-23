package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class HomePageController {

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
    void loadHome(ActionEvent event) {
        loadScreen("/app/project/HomePage.fxml");
    }

    @FXML
    void loadOrderFromSellMan(ActionEvent event) {
    }

    @FXML
    void loadSiteOrder(ActionEvent event) {
        loadScreen("/app/project/DetailOrderPage.fxml");
    }

    @FXML
    void loadSites(ActionEvent event) {}
}