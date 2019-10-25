package chattlesnake;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.scene.control.Alert;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

//TODO: All this stuff and more
// Decode user and send to display
// Decode messages and send to display



public class ChatClientManager {

    private Socket socket;
    private LoginController controller;

    // Constructor
    ChatClientManager(LoginController controller) throws URISyntaxException {
        try {
            this.controller = controller;
            socket = IO.socket("https://chattlesnake-web-server.herokuapp.com/");
            socket.connect();
            handleSocketEvents();
        }
        catch (Exception e){
            System.out.println("An error has occurred");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connection Error");
            alert.setHeaderText("An error has occurred while attempting to connect. Make sure you're connected to the internet");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }

    }

    /**
     * Attempts to verify the user information and log in
     * @param username given username by the user
     * @param password given password by the user
     * @return User if information correct, null if not correct
     */
    public void login(String username, String password){
        System.out.println("Loggin In");
        socket.emit("login", username, password, new Ack() {
            @Override
            public void call(Object... args) {
                // Get User from server
                boolean res = (boolean) args[1];
                if(res){
                    JSONObject jsonUser = (JSONObject) args[0];

                    int userID = jsonUser.getInt("user_id");
                    String name = jsonUser.getString("name");
                    LocalDate create_date = getDateFromString(jsonUser.getString("create_date"));

                    controller.returnUser(new User(userID, name, create_date));
                }
                controller.setFlag(res);
            }
        });
    }

    public void newUser(String username, String email, String password){
        String date = LocalDate.now().toString();
        socket.emit("newUser", username, password, email, date, new Ack() {
            @Override
            public void call(Object... args) {
                boolean res = (boolean) args[0];
                // Tell LoginController if the user was created
                controller.setFlag(res);

                // Give LoginController the user if it was created
                if(res){
                    User user  = new User((int) args[1], username, LocalDate.parse(date));;
                    controller.returnUser(user);
                }
            }
        });
    }

    public User getInfoOnUser(int user_id){
        final User[] user = new User[1]; // Java needs this workaround because why not

        socket.emit("userInfo", new Ack() {
            @Override
            public void call(Object... args) {
                JSONObject jsonUser = (JSONObject) args[0];

                int userID = jsonUser.getInt("user_id");
                String name = jsonUser.getString("name");
                LocalDate create_date = LocalDate.parse(jsonUser.getString("create_date"));
                //TODO: User Pictures?

                user[0] = new User(userID, name, create_date);
            }
        });

        return user[0];
    }

    // Transmits a Message object to the server for distribution
    // @Param: msg; The Message object to be transmitted
    public void sendMessage(Message msg) {
        // Holds a JSON version of the msg to send to the server
        JSONObject jsonMsg = new JSONObject();

        jsonMsg.put("client_id", msg.getAuthor_id());
        jsonMsg.put("recipient_id", msg.getAuthor_id());
        jsonMsg.put("message_body", msg.getMessage_body());
        jsonMsg.put("create_date", msg.getCreate_date());

        socket.emit("chatMessage", jsonMsg);

        try{
            Main.I_LM.log(msg);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Transmits the client_id to the server to get all groups the user is in
     * @param client_id the ID of the client user
     */
    public void getGroups(int client_id){
        socket.emit("getGroups", client_id);
    }

    /**
     * Disconnects the socket from the server
     */
    public void disconnect(){
        socket.disconnect();
    }

    // Sets up event functions and emitter listeners for Socket.IO
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
                JSONObject jsonMsg = (JSONObject) args[0];
                try {
                    int senderId = jsonMsg.getInt("creator");
                    String msgBody = jsonMsg.getString("message_body");
                    LocalDateTime createDate =  LocalDateTime.parse(jsonMsg.getString("create_date")); // TODO: Make sure this doesn't break converting string to LocalDateTime

                    // Make the message object
                    Message msg = new Message();
                    msg.setAuthor_id(senderId);
                    msg.setCreate_date(createDate);
                    msg.setMessage_body(msgBody);

                    // Send message for display
                    Main.I_DM.showMessage(msg);
                    Main.I_LM.log(msg);

                } catch (JSONException | FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).on("groupInfo", new Emitter.Listener() { // This happens when the server sends the information for a group that the user is in
            @Override
            public void call(Object... args) {
                JSONObject jsonGroup = (JSONObject) args[0];
                int count = (int) args[1]; // Number of members in the group

                int ID = jsonGroup.getInt("ID");
                String name = (String) jsonGroup.get("name");
                LocalDateTime create_date = LocalDateTime.parse(jsonGroup.getString("create_date"));

                LinkedList<Integer> memberIDs = new LinkedList<Integer>(); // Hold the IDs of the members

                // Get IDs from the JSONObject
                for(int i = 0; i < count; i++){
                    memberIDs.add((int) jsonGroup.getJSONArray("members").get(i)); // Get the ID at i
                }

                Group group = new Group(ID, name, create_date);

                // Get the server to send the user info for each ID in the group
                memberIDs.forEach(mid -> socket.emit("userInfo", new Ack() {
                    @Override
                    public void call(Object... args) {
                        JSONObject jsonUser = (JSONObject) args[0];

                        User user = new User(jsonUser.getInt("ID"), jsonUser.getString("name"), LocalDate.parse(jsonUser.getString("create_date")));
                        group.addMember(user);
                    }
                }));

                Main.I_DM.showGroup(group);

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
        });

        //TODO: Convert these to individual socket.on with Emitter.Listener functions for readability
    }

    private LocalDate getDateFromString(String dt){
        return LocalDate.of(Integer.parseInt(dt.substring(0,3)), Integer.parseInt(dt.substring(5,6)), Integer.parseInt(dt.substring(8, 9)));
    }

}
