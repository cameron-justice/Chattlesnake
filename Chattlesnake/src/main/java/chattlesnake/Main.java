package chattlesnake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;


import java.time.LocalDateTime;

public class Main extends Application {

    static ChatClientManager I_CCM;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("Main.fxml"));

        Parent root = (Parent)loader.load();
        primaryStage.setTitle("Chattlesnake");
        //DisplayManager displayManager = new DisplayManager(loader.getController());
        Scene primaryScene = new Scene(root, 1280, 720);
        primaryScene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
        primaryStage.setScene(primaryScene);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
