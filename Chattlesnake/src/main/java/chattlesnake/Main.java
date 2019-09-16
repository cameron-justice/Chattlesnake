package chattlesnake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class Main extends Application {

    static ChatClientManager I_CCM;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
        primaryStage.setTitle("Chattlesnake");
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
