package chattlesnake;

import com.sun.javafx.event.EventQueue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

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
    private KeyEvent enterPress;
    @FXML
    private Button loginButton;

    private boolean flag;
    private boolean loginAttempt = false;
    private User user;

    /**
     * Takes the information provided by the user in the text fields and calls this class's login function
     * @param event a mouse click on the "Log In" button
     */
    @FXML
    private void login(ActionEvent event) {
        String username = loginUsernameField.getText().trim();
        String password = loginPasswordField.getText().trim();

        if (username.isEmpty())
            loginUsernameField.requestFocus();
        else if (password.isEmpty())
            loginPasswordField.requestFocus();
        else
            login(username, password);
    }

    /**
     * Collects the information sent by the user and calls the newUser function in the ChatClientManager
     * If a new user was successfully created, calls this class's login function also
     * @param actionEvent a mouse click on the button
     */
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
            flag = false;
            Main.I_CCM.newUser( username, email, password );
            holdUp( 3 );
            if ( flag ) {
                login(username, password);
                System.out.println("Account Successfully Created!");
            }
        }
    }

    /**
     * Sends a login request to the ChatClientManager
     * @param username given username by the user
     * @param password given password by the user
     */
    private void login(String username, String password) {
        // Flag variable is reset by the server using the setFlag function
        if (!loginAttempt) {
            loginAttempt = true;
            flag = false;
            Main.I_CCM.login(username, password);
            holdUp(2);
            System.out.println(flag);
            Main.activeUser = user;
        } else {
            holdUp(1);
            loginAttempt = false;
        }

        if (flag) {
            Stage stage = (Stage) loginPane.getScene().getWindow();
            stage.close();

            try {
                Main.openChat();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("User ID: " + Main.activeUser.getID());
        }
    }

    /**
     * Displays the Create Account pane
     * @param event a mouse click on the "Create Account" button
     */
    @FXML
    private void createAccountPrompt(ActionEvent event) {
        accountPane.setVisible(true);
        accountUsernameField.setText( loginUsernameField.getText().trim() );
        accountPasswordField.setText( loginPasswordField.getText().trim() );
    }

    /**
     * Disables the visibility of the create account pane
     * @param actionEvent a mouse click on the button
     */
    public void backAction(ActionEvent actionEvent) { accountPane.setVisible(false); }

    /**
     * Terminates the program
     * @param actionEvent a mouse click on the button
     */
    public void exitProgram(ActionEvent actionEvent) { System.exit(0); }

    /**
     * The server's method of letting the controller know that login/account creation passed
     * @param flag a boolean sent by the server to update the pass variable
     */
    public void setFlag( boolean flag ) {
        this.flag = flag;
    }

    /**
     * Sets the user for the function, called by the ChatClientManager
     * @param user the user variable sent from the function
     */
    public void returnUser( User user ) { this.user = user; }

    /**
     * Pauses the system for a specified number of seconds
     * @param seconds
     */
    private void holdUp( int seconds ) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch ( Exception e ){
            e.printStackTrace();
        }

    }

    public void enterPress(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals((KeyCode.ENTER)))
            loginButton.fire();
    }

}
