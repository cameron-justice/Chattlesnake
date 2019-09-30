package chattlesnake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    // Singleton pattern for managers
    static public ChatClientManager I_CCM;
    static public DisplayManager I_DM;

    static public User activeUser;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getClassLoader().getResource("Login.fxml"));
        Parent root = (Parent)loader.load();

        primaryStage.setTitle("ChattleSnake");
        Scene primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

        // Manager Singletons
        I_CCM = new ChatClientManager();
//        I_DM = new DisplayManager(loader.getController());


    }

    public static void main(String[] args) {
        launch(args);
    }
}
