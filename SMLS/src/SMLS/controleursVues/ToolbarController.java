package SMLS.controleursVues;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class ToolbarController implements Initializable {
    private BookReturnCallback callback;

    public void setBookReturnCallback(BookReturnCallback callback) {
        this.callback = callback;
    }
    
    @FXML
    void loadAddAb(ActionEvent event) {
        LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/add_Abonnee.fxml"), "Ajouter Nouveau Abonnée", null);

    }
    
    @FXML
    void loadListAb(ActionEvent event) {
        LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/Abonnee_list.fxml"), "Afficher la liste des abonnée", null);

    }

    @FXML
    void loadAddBook(ActionEvent event) {
        LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/add_Ligne.fxml"), "Ajouter Nouvelle Ligne", null);

    }

    @FXML
    void loadAddMember(ActionEvent event) {
        LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/add_Station.fxml"), "Ajouter Nouvelle Station", null);

    }

    @FXML
    void loadBookTable(ActionEvent event) {
        LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/Ligne_list.fxml"), "Afficher la liste des Lignes", null);

    }

    @FXML
    void loadIssuedBookList(ActionEvent event) {
    	 LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/add_Arret.fxml"), "Ajouter Nouvau Arrêt", null);
    }

    @FXML
    void loadMemberTable(ActionEvent event) {
        LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/Station_list.fxml"), "Afficher la liste des stations", null);

    }

    @FXML
    void loadSettings(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
