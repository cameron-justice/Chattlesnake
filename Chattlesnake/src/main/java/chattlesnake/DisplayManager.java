package chattlesnake;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

/**
 * The DisplayManager class is responsible for taking the messages/groups being
 * passed by the server and displaying them in the correct text field
 */
public class DisplayManager {

    private MainController controller;
    public DisplayManager(MainController controller) {
        this.controller = controller;
    }

    public void showMessage(Message msg) {
        int author_id = msg.getAuthor_id();
        int recipient_id = msg.getRecipient_id();
        String message = msg.getMessage_body();
        String time = msg.getCreate_date().toLocalTime().truncatedTo(ChronoUnit.SECONDS).toString();


        System.out.println("From: " + author_id);
        System.out.println("To: " + recipient_id);
        System.out.println("Message: " + message);

        // Converts the time to 12-hour format
        try {
            SimpleDateFormat hour24 = new SimpleDateFormat("H:mm:ss");
            SimpleDateFormat hour12 = new SimpleDateFormat("h:mm:ss a");
            Date dateObj = hour24.parse(time);
            System.out.println("Message sent at: " + hour12.format(dateObj));
        } catch (final ParseException e) {
            e.printStackTrace();
        }


        controller.messageDisplayArea.appendText(Integer.toString(author_id) + ": ");
        controller.messageDisplayArea.appendText(message);
    }

    public void showGroup(Group group) {


    }
}
