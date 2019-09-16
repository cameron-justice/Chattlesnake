package chattlesnake;

public class DisplayManager {

    public void showMessage(Message msg) {
        int author_id = msg.getAuthor_id();
        int recipient_id = msg.getRecipient_id();
        String message = msg.getMessage_body();
        String time = msg.getCreate_date().toLocalTime().toString();

        System.out.println("From: " + author_id);
        System.out.println("To: " + recipient_id);
        System.out.println(message);
        System.out.println("Message sent at: " + time);
    }

    public void showGroup(Group group) {


    }
}
