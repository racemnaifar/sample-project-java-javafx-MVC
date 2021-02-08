package SMLS;
	
import SMLS.Main;
import javafx.application.Application;
import javafx.stage.Stage;
import SMLS.controleursVues.LibraryAssistantUtil;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("vues/Abonnement_list.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        
        stage.setTitle("SMLS");

        LibraryAssistantUtil.setStageIcon(stage);

        new Thread(() -> {
       //     ExceptionUtil.init();
         //   DatabaseHandler.getInstance();
        }).start();
    }

	public static void main(String[] args) {
		launch(args);

	}
}
