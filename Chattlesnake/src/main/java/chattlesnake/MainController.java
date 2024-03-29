package chattlesnake;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public ListView profileSection;
    @FXML
    private javafx.scene.layout.AnchorPane primaryPane;
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
    private Stage mainStage;

    private ObservableList<Group> profileObservableList;

    public MainController() {
         profileObservableList = FXCollections.observableArrayList();
    }

    @FXML
    private void exitMenuItemAction(){
        // Get the stage
        System.exit(0);
    }

    @FXML
    private void sendMessage(ActionEvent event){

        if (!messageSendArea.getText().trim().isEmpty()) {
            String toSend = messageSendArea.getText().trim();
            messageSendArea.clear();
            Message msg = new Message(Main.activeUser.getID(), 50, toSend, LocalDateTime.now());
            Main.I_DM.showMessage(msg);
            Main.I_CCM.sendMessage(msg);
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

    public void setStage(Stage stage){
        mainStage = stage;
    }

    public void clearChat(ActionEvent actionEvent) {
        Main.I_DM.clearChat();
    }

    public void fullscreenMenuItemAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        profileObservableList.addAll(new Group(69420, "TestGroup", LocalDateTime.now()));
        profileSection.setItems(profileObservableList);
        profileSection.setCellFactory(studentListView -> new GroupListViewCell());
    }
}
