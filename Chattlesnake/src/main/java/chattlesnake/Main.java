package chattlesnake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
        primaryStage.setTitle("Chattlesnake");
        Scene primaryScene = new Scene(root, 1280, 720);
        primaryStage.setScene(primaryScene);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        //primaryStage.show();

        /// Testing Area for Socket connection to Webserver
        ChatClientManager client = new ChatClientManager();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
