package chattlesnake;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.json.simple.JSONObject;
import java.time.LocalDateTime;

public class LogManager {
    public void log(Message mess) throws FileNotFoundException {

        JSONObject log = new JSONObject();
        int author_id = 0;
        int recipient_id = 0;
        String message_body = "";
        LocalDateTime create_date;

        author_id = mess.getAuthor_id();
        recipient_id = mess.getRecipient_id();
        message_body = mess.getMessage_body();
        create_date = mess.getCreate_date();

        log.put("Author: ", author_id);
        log.put("Recipient: ", recipient_id);
        log.put("Message Body: ", message_body);
        log.put("Date: ", create_date);

        PrintWriter pw = new PrintWriter("Log.txt");
        pw.write(log.toJSONString());
        pw.flush();
        pw.close();

    }
}