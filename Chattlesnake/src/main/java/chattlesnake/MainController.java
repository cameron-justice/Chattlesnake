package chattlesnake;

import chattlesnake.ChatClientManager;
import chattlesnake.DisplayManager;
import chattlesnake.Message;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.net.URISyntaxException;
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
    public ScrollPane chatScroll;
    @FXML
    private Button sendButton;
    @FXML
    private javafx.scene.control.TextField username;
    @FXML
    private javafx.scene.control.PasswordField passwordField;


    @FXML
    private void exitMenuItemAction(){
        // Get the stage
        System.exit(0);
    }

    @FXML
    private void fullscreenMenuItemAction(){
        Stage stage = (Stage) primaryPane.getScene().getWindow();
        stage.setMaximized(true);

    }


    @FXML
    private void sendMessage(ActionEvent event){

        System.out.println("Yah Yeet.");

        if (!messageSendArea.getText().trim().isEmpty()) {
            String toSend = messageSendArea.getText().trim();
            messageSendArea.clear();
            Message msg = new Message(6969, toSend, LocalDateTime.now());
            Main.I_DM.showMessage(msg);
            //chatScroll.setVvalue(1);
        }
        messageSendArea.requestFocus();
    }

    public void postMessage(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals((KeyCode.ENTER))) {
            sendButton.fire();
            messageSendArea.clear();
        }
    }

    @FXML
    private void setLogin(ActionEvent event) throws URISyntaxException {
        String userid = username.getText();
        String password = passwordField.getText();
        ChatClientManager logIn = new ChatClientManager();
        logIn.login(userid, password);
        System.out.println("Login successful");
    }
    @FXML
    private void createAccount(ActionEvent event) throws URISyntaxException {
        String userid = username.getText();
        String password = passwordField.getText();
        ChatClientManager user = new ChatClientManager();
        //user.newUser(userid, password);
        System.out.println("Account created");
    }
}
