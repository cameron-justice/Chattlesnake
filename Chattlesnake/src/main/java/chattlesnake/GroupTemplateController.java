package chattlesnake;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class GroupTemplateController {

    @FXML
    FlowPane groupFlow;
    @FXML
    TextFlow userName;
    @FXML
    TextFlow timeLastText;

    GroupTemplateController(String username, String time){
        groupFlow = new FlowPane();
        userName = new TextFlow(new Text(username));
        timeLastText = new TextFlow(new Text(time));

        groupFlow.getChildren().addAll(userName, timeLastText);


    }
}
