package chattlesnake;

import javafx.fxml.FXML;

import javafx.stage.Stage;
import javafx.scene.Scene;
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
    private javafx.event.ActionEvent setLogin;
    @FXML
    private javafx.event.ActionEvent createAccount;
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
        user.newUser(userid, password);
        System.out.println("Account created");
    }


}




