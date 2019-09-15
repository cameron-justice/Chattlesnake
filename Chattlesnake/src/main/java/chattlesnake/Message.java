package chattlesnake;

import java.time.LocalDateTime;

public class Message {

    private int author_id;
    private int recipient_id;
    private String message_body;
    private LocalDateTime create_date;

    Message(){

    }

    Message(int author_id, int recipient_id, String message_body, LocalDateTime create_date){
        this.author_id = author_id;
        this.recipient_id = recipient_id;
        this.message_body = message_body;
        this.create_date = create_date;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(int recipient_id) {
        this.recipient_id = recipient_id;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }

    public LocalDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }
}
