package chattlesnake;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.scene.control.Alert;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChatClientManager {

    private Socket socket;
    private String msg;

    // Constructor
    ChatClientManager() throws URISyntaxException {

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

    public void

    // Transmits the client_id to the server to get all the groups the user is in
    // @Param: client_id; the id of the client user
    public void getGroups(int client_id){
        socket.emit("groups", client_id);
    }

    // Sets up event functions and emoitter listeners for Socket.IO
    // Socket.IO works by connecting the socket to a server and creating listeners for events.
    // When an event happens, the listener catches it and calls the "call" function dedicated to it.
    private void handleSocketEvents(){
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() { // This happens when the socket first connects to a server
            @Override
            public void call(Object... args) {
                System.out.println("Connected: " + socket.connected());
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() { // This happens when the socket disconnects from the server (or when the server forces disconnection)
            @Override
            public void call(Object... args) {
                System.out.println("Disconnected");
            }
        }).on("chatMessage", new Emitter.Listener() { // This happens when the server sends a message to the socket
            @Override
            public void call(Object... args) {
                JSONObject msg = (JSONObject) args[0];
                try {
                    String senderName = (String) msg.get("creator");
                    String msgBody = (String) msg.get("message_body");
                    LocalDateTime createDate = (LocalDateTime) msg.get("create_date"); // TODO: Make sure this doesn't break converting string to LocalDateTime
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).on("getGroupInfo", new Emitter.Listener() { // This happens when the server sends the information for a group that the user is in
            @Override
            public void call(Object... args) {
                JSONObject group = (JSONObject) args[0];
                int count = (int) args[1]; // Number of members in the group

                String name = (String) group.get("name");
                LocalDateTime create_date = (LocalDateTime) group.get("create_date");
                boolean passwd_required = (boolean) group.get("passwd_required");

                ArrayList<Integer> memberIDs = new ArrayList<Integer>(); // Hold the IDs of the members

                for(int i = 0; i < count; i++){
                    memberIDs.add((int) group.getJSONArray("members").get(i)); // Get the ID at i
                }

                memberIDs.forEach((ID) -> socket.emit("getUserInfo", ID)); // Request the info from the server for each member

            }
        }).on("getGroups", new Emitter.Listener() { // This happens when the server sends the array of all groups the user is in
            @Override
            public void call(Object... args) {
                JSONArray groups = (JSONArray) args[0];
                int count = (int) args[1];

                for(int i = 0; i < count; i++){
                    socket.emit("groupInfo", (int) groups.get(i)); // Request info for the groupId
                }
            }
        }).on("userInfo", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject user = (JSONObject) args[0];

                int userID = user.getInt("user_id");
                String name = user.getString("name");
                //TODO: User Pictures


                //TODO: Transmit to object that sets up userinfo

            }
        })
    }

}
