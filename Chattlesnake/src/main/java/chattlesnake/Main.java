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

import java.time.LocalDateTime;

public class Main extends Application {

    // Singleton pattern for managers
    static public ChatClientManager I_CCM;
    static public DisplayManager I_DM;
    static public LogManager I_LM = new LogManager();
    static public RelationsManager I_RM = new RelationsManager();

    static public Scene scene;

    static public User activeUser;
    static public FXMLLoader loader = new FXMLLoader();
    static private Parent root;

    @Override
    public void start(Stage loginStage) throws Exception{
        loader.setLocation(getClass().getClassLoader().getResource("Login.fxml"));
        Parent root = (Parent)loader.load();

        loginStage.setTitle("ChattleSnake");
        Scene loginScene = new Scene(root);
        loginStage.setScene(loginScene);
        loginStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        loginStage.initStyle(StageStyle.UNDECORATED);
        loginStage.show();

        // Manager Singletons
        I_CCM = new ChatClientManager(loader.getController());
    }

    public static void openChat() throws Exception {
        loader = new FXMLLoader();
        loader.setLocation(Main.class.getClassLoader().getResource("Main.fxml"));

        root = (Parent)loader.load();
        Stage mainStage = new Stage();
        mainStage.setTitle("ChattleSnake");
        Scene mainScene = new Scene( root, 1280, 720 );
        scene = mainScene;

        mainStage.setScene(mainScene);
        mainStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        mainStage.show();

        I_DM = new DisplayManager( loader.getController() );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
