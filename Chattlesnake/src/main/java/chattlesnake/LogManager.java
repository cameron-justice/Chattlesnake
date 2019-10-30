package chattlesnake;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LogManager {

    private FileWriter fw;
    private PrintWriter pw;

    public LogManager() throws IOException {
        fw = new FileWriter("Log.txt", true);
        pw = new PrintWriter(fw);

    }

    public void log(Message mess) {

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

        pw.append(log.toString() + '\n');
        pw.flush();

    }
}