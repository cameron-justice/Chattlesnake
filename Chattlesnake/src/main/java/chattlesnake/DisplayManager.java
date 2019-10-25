package chattlesnake;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;

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

        String time = msg.getCreate_date().toLocalTime().truncatedTo(ChronoUnit.SECONDS).toString();

        System.out.println("From: " + author_id);
        System.out.println("To: " + recipient_id);
        //System.out.println("Message: " + message);

        // Converts the time to 12-hour format
        try {
            SimpleDateFormat hour24 = new SimpleDateFormat("H:mm:ss");
            SimpleDateFormat hour12 = new SimpleDateFormat("h:mm:ss a");
            Date dateObj = hour24.parse(time);
            System.out.println("Message sent at: " + hour12.format(dateObj));
        } catch (final ParseException e) {
            e.printStackTrace();
        }

        String username = getUsername(author_id);
        Text id = new Text("[" + Integer.toString(author_id) + "] " + username + ": ");
        //id.setStyle("-fx-font-weight: bold");
        id.setFont(Font.font(null, FontWeight.BOLD, 18));
        id.setFill(Color.ORANGERED);
        Text message = new Text(msg.getMessage_body() + '\n');
        message.setFont(Font.font(null, /*FontWeight.NORMAL,*/ 18));

        controller.messageDisplayArea.getChildren().addAll(id, message);
        controller.chatScroll.setVvalue(1);
    }

    private String getUsername(int id) {
        if (id == Main.activeUser.getID())
            return Main.activeUser.getName();
        else
            return "Some Fuckup";
    }


    public void showGroup(Group group) {


    }
}
