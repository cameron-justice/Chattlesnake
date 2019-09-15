package chattlesnake;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.scene.control.Alert;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.net.URISyntaxException;

public class ChatClientManager {

    private Socket socket;
    private String msg;

    // Constructor
    ChatClientManager(String message) throws URISyntaxException {
        msg = message;

        try {
            socket = IO.socket("https://chattlesnake-web-server.herokuapp.com/");
            socket.connect();

            handleSocketEvents();
        }
        catch (Exception e){
            System.out.println("An error has occurred");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connection Error");
            alert.setHeaderText("An error has occurred while attempting to connect");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }

    }

    // Transmits a Message object to the server for distribution
    // @Param: msg; The Message object to be transmitted
    public void sendMessage(Message msg){
        // Holds a JSON version of the msg to send to the server
        JSONObject jsonMsg = new JSONObject();

        jsonMsg.put("client_id", msg.getAuthor_id());
        jsonMsg.put("recipient_id", msg.getAuthor_id());
        jsonMsg.put("message_body", msg.getMessage_body());
        jsonMsg.put("create_date", msg.getCreate_date());

        socket.emit("chatMessage", jsonMsg);
    }

    // Transmits the client_id to the server to get all the groups the user is in
    // @Param: client_id; the id of the client user
    public List getGroups(int client_id){

    }

    // Sets up event functions and emoitter listeners for Socket.IO
    // Socket.IO works by connecting the socket to a server and creating listeners for events.
    // When an event happens, the listener catches it and calls the "call" function dedicated to it.
    private void handleSocketEvents(){
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() { // This happens when the socket first connects to a server
            @Override
            public void call(Object... args) {
                System.out.println("Conected: " + socket.connected());

                if(msg != ""){
                    socket.emit("chatMessage", msg);
                }
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() { // This happens when the socket disconnects from the server (or when the server forces disconnection)
            @Override
            public void call(Object... args) {
                System.out.println("Disconnected");
            }
        }).on("chatMessage", new Emitter.Listener() { // This happens when the server sends a message to the socket
            @Override
            public void call(Object... args) {
                // TODO: Decode the JSON object and give it to the application to show
                JSONObject msg = (JSONObject) args[0];
                try {
                    String senderName = (String) msg.get("creator");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).on("groupInfo", new Emitter.Listener() { // This happens when the server sends the information for a group that the user is in
            @Override
            public void call(Object... args) {

            }
        });
    }

}
