package SMLS.controleursVues;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import SMLS.accesBD.ArretDAO;
import SMLS.accesBD.LigneDAO;
import SMLS.accesBD.StationDAO;
import SMLS.controleursVues.Liste_ArretControle.Arret;
import SMLS.controleursVues.Liste_LigneControle.Ligne;
import SMLS.entites.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Ajouter_ArretControle implements Initializable {
	StationDAO dao = new StationDAO();
	LigneDAO daol = new LigneDAO();
	ArretDAO daoa = new ArretDAO();
    private Boolean isInEditMode = Boolean.FALSE;
	private ObservableList<String> listeZ = FXCollections.observableArrayList("1","2","3","4","5");

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private JFXTextField numo;
    
    @FXML
    private JFXComboBox<String> nomLig;

    @FXML
    private JFXComboBox<String> nomSt;

    @FXML
    private JFXComboBox<String> numZ;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;


    @FXML
    void addBook(ActionEvent event) throws SQLException {
    	 String numAr =numo.getText() + "/" + nomLig.getValue().toString();

    	//if (numL == null || numS == null || numZo== null || numor ==null ) {
          //  AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Donn�es insuffisantes", "Veuillez entrer les donn�es dans tous les champs.");
            //return;
       // }

        if (isInEditMode) {
            handleEditOperation();
            return;
        }

        if (daoa.isArretExists(numAr)) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Num�ro d'Arr�t en double", "Un Arr�t avec le m�me num�ro d'Arr�t existe.\nVeuillez utiliser un nouvel num�ro");
            return;
        }

    	Arret c = new Arret(numAr , Integer.parseInt(nomLig.getValue()) ,Integer.parseInt(numZ.getValue()), Integer.parseInt(nomSt.getValue()));
        boolean result = daoa.create(c);
        if (result) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Nouveau Arr�t ajout�e", numAr + " a �t� ajout�");
            clearEntries();
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Impossible d'ajouter un nouveau Arr�t", "V�rifiez toutes les entr�es et essayez � nouveau");
        }
    }
    
    private void clearEntries() {
        numo.clear();
        nomLig.getSelectionModel().clearSelection();
        nomSt.getSelectionModel().clearSelection();
        numZ.getSelectionModel().clearSelection();
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		numZ.setItems(listeZ);
		nomLig.setItems(daol.findAllob());
		nomSt.setItems(dao.findAllob());
		
	}
	
    private void handleEditOperation() {
   	 String numAr =numo.getText() + "/" + nomLig.getValue().toString();

 	Arret ln = new Arret(numAr , Integer.parseInt(nomLig.getValue()) ,Integer.parseInt(numZ.getValue()), Integer.parseInt(nomSt.getValue()));

        if (daoa.update(ln)) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Succ�s", "Mise � jour termin�e");
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "�chou�", "Impossible de mettre � jour les donn�es");
        }
    }
    
    public void inflateUI(Arret l) {
        nomLig.getSelectionModel().select(l.getNumLig());
        nomSt.getSelectionModel().select(l.getNumSt());
        numZ.getSelectionModel().select(l.getNumZone());
        numo.setText(l.getNumAr());
        numo.setEditable(false);
        nomLig.setEditable(false);    
        isInEditMode = Boolean.TRUE;
    }

}
