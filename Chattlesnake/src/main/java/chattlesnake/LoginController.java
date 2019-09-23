package chattlesnake;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URISyntaxException;

public class LoginController {

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
