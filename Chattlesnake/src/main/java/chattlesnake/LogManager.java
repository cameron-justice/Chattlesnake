package chattlesnake;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.json.simple.JSONObject;
import java.time.LocalDateTime;

public class LogManager {
    public static void log(Message mess) throws FileNotFoundException {
        System.out.println("This is the branch section");

        JSONObject log = new JSONObject();
        int author_id = 0;
        int recipient_id = 0;
        String message_body = "";
        LocalDateTime create_date;

        author_id = mess.getAuthor_id();
        recipient_id = mess.getRecipient_id();
        message_body = mess.getMessage_body();
        create_date = mess.getCreate_date();

        PrintWriter pw = new PrintWriter("Log.txt");
        pw.write(log.toJSONString());
        pw.flush();
        pw.close();

    }
}