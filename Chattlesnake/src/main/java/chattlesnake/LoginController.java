package chattlesnake;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class LoginController {

    @FXML
    private AnchorPane loginPane;
    @FXML
    private TextField loginUsernameField;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField accountUsernameField;
    @FXML
    private PasswordField accountPasswordField;
    @FXML
    private AnchorPane accountPane;




    @FXML
    private void login(ActionEvent event) throws URISyntaxException {
        String username = loginUsernameField.getText().trim();
        String password = loginPasswordField.getText().trim();

        if (username.isEmpty())
            loginUsernameField.requestFocus();
        else if (password.isEmpty())
            loginPasswordField.requestFocus();
        else {
            login(username, password);
            Stage stage = (Stage) loginPane.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void createAccountPrompt(ActionEvent event) throws Exception {
        accountPane.setVisible(true);
        accountUsernameField.setText( loginUsernameField.getText().trim() );
        accountPasswordField.setText( loginPasswordField.getText().trim() );
    }

    public void exitProgram(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void createAccount(ActionEvent actionEvent) {
        String username = accountUsernameField.getText().trim();
        String password = accountPasswordField.getText().trim();
        String email = emailField.getText().trim();

        if ( !(username.isEmpty() || password.isEmpty() || email.isEmpty()) )
            accountUsernameField.requestFocus();
        else if (password.isEmpty())
            accountPasswordField.requestFocus();
        else if (email.isEmpty())
            emailField.requestFocus();
        else {
            if (Main.I_CCM.newUser( username, email, password ))
                login(username, password);
        }
    }

    public void backAction(ActionEvent actionEvent) {
        accountPane.setVisible(false);
    }

    public void login(String username, String password) {

    }
}
