package chattlesnake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class Main extends Application {

    // Singleton pattern for managers
    static public ChatClientManager I_CCM;
    static public DisplayManager I_DM;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("Main.fxml"));

        Parent root = (Parent)loader.load();
        primaryStage.setTitle("ChattleSnake");
        Scene primaryScene = new Scene(root, 1280, 720);
        primaryScene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
        primaryStage.setScene(primaryScene);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();

        // Manager Singletons
        I_CCM = new ChatClientManager();
        I_DM = new DisplayManager(loader.getController());

    }

    public static void main(String[] args) {
        launch(args);
    }
}
