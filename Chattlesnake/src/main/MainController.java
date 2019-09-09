package main;

import javafx.fxml.FXML;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class MainController {

    @FXML
    private javafx.scene.layout.Pane primaryPane;
    @FXML
    private javafx.scene.control.MenuItem exitMenuItem;

    @FXML
    private void exitMenuItemAction(){
        // Get the stage
        Stage stage = (Stage) primaryPane.getScene().getWindow();

        stage.close();
    }
}
