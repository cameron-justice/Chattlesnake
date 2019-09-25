package chattlesnake;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class LoginController {

    @FXML
    private AnchorPane loginPane;
    @FXML
    private TextField username;
    @FXML
    private PasswordField passwordField;




    @FXML
    private void login(ActionEvent event) throws URISyntaxException {
        String userid = username.getText().trim();
        String password = passwordField.getText().trim();

        if (userid.isEmpty())
            username.requestFocus();
        else if (password.isEmpty())
            passwordField.requestFocus();
        else {
            Main.I_CCM.login(userid, password);
            Stage stage = (Stage) loginPane.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void createAccount(ActionEvent event) throws URISyntaxException {
        String userid = username.getText().trim();
        String password = passwordField.getText().trim();
        Main.I_CCM.newUser(userid, password);
    }

    public void exitProgram(ActionEvent actionEvent) {
        System.exit(0);
    }
}
