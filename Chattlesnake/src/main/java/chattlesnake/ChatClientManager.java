package chattlesnake;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.scene.control.Alert;

import java.net.URISyntaxException;

public class ChatClientManager {

    private Socket socket;

    // Constructor
    ChatClientManager() throws URISyntaxException {
        try {
            socket = IO.socket("http://localhost:3000/");
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

    // Sets up event functions and emoitter listeners for Socket.IO
    // Socket.IO works by connecting the socket to a server and creating listeners for events.
    // When an event happens, the listener catches it and calls the "call" function dedicated to it.
    private void handleSocketEvents(){
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Connected");
                System.out.println("Conected: " + socket.connected());
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Disconnected");
            }
        });
    }

}
