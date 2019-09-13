package chattlesnake;

import java.net.Socket;

public class ChatClientManager {

    private Socket client;

    // Constructor
    ChatClientManager(){
        try {
            client = new Socket("http://localhost/chats/", 8000);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
