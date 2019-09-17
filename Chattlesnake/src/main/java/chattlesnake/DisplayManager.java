package chattlesnake;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DisplayManager {

    public void showMessage(Message msg) {
        int author_id = msg.getAuthor_id();
        int recipient_id = msg.getRecipient_id();
        String message = msg.getMessage_body();
        String time = msg.getCreate_date().toLocalTime().truncatedTo(ChronoUnit.SECONDS).toString();

        System.out.println("From: " + author_id);
        System.out.println("To: " + recipient_id);
        System.out.println("Message: " + message);

        try {
            SimpleDateFormat hour24 = new SimpleDateFormat("H:mm:ss");
            SimpleDateFormat hour12 = new SimpleDateFormat("h:mm:ss a");
            Date dateObj = hour24.parse(time);
            System.out.println("Message sent at: " + hour12.format(dateObj));
        } catch (final ParseException e) {
            e.printStackTrace();
        }




    }

    public void showGroup(Group group) {


    }
}
