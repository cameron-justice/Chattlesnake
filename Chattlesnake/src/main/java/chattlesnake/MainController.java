package chattlesnake;

import javafx.fxml.FXML;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.time.LocalDateTime;

public class MainController {


    @FXML
    private javafx.scene.layout.Pane primaryPane;
    @FXML
    private javafx.scene.control.MenuItem exitMenuItem;
    @FXML
    private javafx.scene.control.MenuItem fullscreenMenuItem;
    @FXML
    private javafx.event.ActionEvent sendMessage;
    @FXML
    public TextFlow messageDisplayArea;
    @FXML
    public TextArea messageSendArea;
    @FXML
    private javafx.scene.input.KeyEvent postMessage;
    @FXML
    private ScrollPane chatScroll;
    @FXML
    private Button sendButton;

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

        System.out.println("Yah Yeet.");

        DisplayManager displayManager = new DisplayManager(this);
        String toSend;
        Message msg;
        if (!messageSendArea.getText().isEmpty()) {
            toSend = messageSendArea.getText().trim();
            messageSendArea.clear();
            msg = new Message(6969, toSend, LocalDateTime.now());
            displayManager.showMessage(msg);
            chatScroll.setVvalue(1);
        }
        messageSendArea.requestFocus();
    }

    public void postMessage(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals((KeyCode.ENTER)))
            sendButton.fire();
    }
}