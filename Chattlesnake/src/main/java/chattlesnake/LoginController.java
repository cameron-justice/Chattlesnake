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
        else
            login(username, password);
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

        if ( username.isEmpty() )
            accountUsernameField.requestFocus();
        else if ( password.isEmpty() )
            accountPasswordField.requestFocus();
        else if ( email.isEmpty() )
            emailField.requestFocus();
        else {
            if ( Main.I_CCM.newUser( username, email, password ) )
                login(username, password);
        }
    }

    public void backAction(ActionEvent actionEvent) {
        accountPane.setVisible(false);
    }

    private void login(String username, String password) {
        Main.activeUser = Main.I_CCM.login(username, password);
        //if ( Main.activeUser.getID() != 0) {
            Stage stage = (Stage) loginPane.getScene().getWindow();
            stage.close();

            try {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getClassLoader().getResource("Main.fxml"));
                Parent root = (Parent) loader.load();

                Stage primaryStage = new Stage();
                primaryStage.setTitle("ChattleSnake");
                Scene primaryScene = new Scene(root, 1280, 720);
                primaryScene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
                primaryStage.setScene(primaryScene);
                primaryStage.show();

                Main.I_DM = new DisplayManager(loader.getController());
            } catch ( final IOException e) {
                e.printStackTrace();
            }
        //}
    }
}
