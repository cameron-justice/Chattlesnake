package chattlesnake;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static chattlesnake.DisplayManager.*;

public class MainController implements Initializable {

    @FXML
    private javafx.scene.layout.Pane primaryPane;
    @FXML
    private javafx.scene.control.MenuItem exitMenuItem;
    @FXML
    private javafx.scene.control.MenuItem fullscreenMenuItem;
    @FXML
    private javafx.event.ActionEvent sendMessage;
    @FXML
    public TextArea messageDisplayArea;


    @FXML
    private void exitMenuItemAction(){
        // Get the stage
        Stage stage = (Stage) primaryPane.getScene().getWindow();

        stage.close();
    }

    @FXML
    private void fullscreenMenuItemAction(){
        Stage stage = (Stage) primaryPane.getScene().getWindow();

        // Switch current setting
        stage.setFullScreen( !stage.isFullScreen() );

    }


    @FXML
    private void sendMessage(ActionEvent event){
        //System.out.println("Hello World");

        //DisplayManager disp = new DisplayManager();
        Message msg = new Message(6969,420, "Hello there bro palarooni", LocalDateTime.now());
        //disp.showMessage(msg);

        DisplayManager displayManager = new DisplayManager(this);
        displayManager.showMessage(msg);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Nothing
    }
}




