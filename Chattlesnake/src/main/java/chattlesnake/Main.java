package chattlesnake;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import org.json.JSONObject;

public class Main extends Application {

    private Socket socket; // Primary connection to the server

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
        primaryStage.setTitle("Chattlesnake");
        Scene primaryScene = new Scene(root, 1280, 720);
        primaryStage.setScene(primaryScene);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();

        /// Testing Area for Socket.IO connection to Webserver

        // This creates a Socket object that is connected to the URL passed
        socket = IO.socket("http://localhost:8000");
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void handeSocketEvents(){
        final JSONObject json = new JSONObject();

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {

            }
        });
    }
}
