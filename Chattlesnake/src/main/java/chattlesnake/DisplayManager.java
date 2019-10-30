package chattlesnake;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
        try {
            wait();
        } catch (Exception e){
            e.printStackTrace();
        }

        int author_id = msg.getAuthor_id();
        int recipient_id = msg.getRecipient_id();


        //System.out.println("From: " + author_id);
        System.out.println("Here: " + Main.I_RM.getUser(author_id).getName());

        System.out.println("From: " + author_id);

        //System.out.println("To: " + recipient_id);
        //System.out.println("Message: " + message);

        // Converts the time to 12-hour format
        LocalDateTime cd = msg.getCreate_date();
        LocalTime time = cd.toLocalTime().truncatedTo(ChronoUnit.SECONDS);
        String stringTime = time.toString();
        System.out.println("Message sent at: " + time.toString());


        String username = getUsername(author_id);
        Text id = new Text("[" + Integer.toString(author_id) + "] " + username + "@" + stringTime + ": ");
        //id.setStyle("-fx-font-weight: bold");
        id.setFont(Font.font(null, FontWeight.BOLD, 18));
        id.setFill(Color.ORANGERED);
        Text message = new Text(msg.getMessage_body() + '\n');
        message.setFont(Font.font(null, /*FontWeight.NORMAL,*/ 18));

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller.messageDisplayArea.getChildren().addAll(id, message);
                controller.chatScroll.setVvalue(1);
            }
        });
    }

    private String getUsername(int id) {
        if (id == Main.activeUser.getID())
            return Main.activeUser.getName();
        else {
            return Main.I_RM.getUsername(id);
        }
    }


    public void showGroup(Group group) {


    }

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
    public void clearChat() { controller.messageDisplayArea.getChildren().clear(); }
}
