package SMLS.controleursVues;

import java.io.IOException;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import SMLS.Main;
import SMLS.entites.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginControle {
	public static Utilisateur user;

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
    	String nomUtil= "admin";
    	String mp= "admin";
    	System.out.println(event.getSource());
    	System.out.println(event.getTarget());
    	System.out.println(event.getEventType());
    	if(username.getText().equals(mp) && (password.getText().equals(nomUtil)))
        {
    	 user=new Utilisateur(nomUtil, mp);
         LibraryAssistantUtil.loadWindow(getClass().getResource("vues/member_add.fxml"), "Menu", null);

        }
    	else
    		if(username.getText().equals("user") && (password.getText().equals("user")))
            {
            // BorderPane root = (BorderPane)FXMLLoader.load(Main.class.getResource("vues/MenuPrincipal.fxml"));

             //Scene scene = new Scene(root,600,600);
             //Main.primaryStage.setTitle("Menu Principal");
             //Main.primaryStage.setScene(scene);
             //Main.primaryStage.show();
            }
        else
        	if(username.getText().equals("") ||(password.getText().equals("")))
        {
                username.getStyleClass().add("wrong-credentials");
                password.getStyleClass().add("wrong-credentials");
        }
        else
        {
            username.getStyleClass().add("wrong-credentials");
            password.getStyleClass().add("wrong-credentials");
        }
    }
    
    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }
    
    private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
    }
    

}
