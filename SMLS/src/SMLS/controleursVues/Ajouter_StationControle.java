package SMLS.controleursVues;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import SMLS.accesBD.StationDAO;
import SMLS.controleursVues.Liste_LigneControle.Ligne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import SMLS.controleursVues.Liste_StationControle.Station;

public class Ajouter_StationControle {
    StationDAO dao = new StationDAO();
    private Boolean isInEditMode = Boolean.FALSE;

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private JFXTextField numSt;

    @FXML
    private JFXTextField nomSt;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    void addBook(ActionEvent event) throws SQLException {
        String numS = numSt.getText();
        String ns = nomSt.getText();

    	if (numS.isEmpty() || ns.isEmpty() ) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Données insuffisantes", "Veuillez entrer les données dans tous les champs.");
            return;
        }

        if (isInEditMode) {
            handleEditOperation();
            return;
        }

        if (dao.isStationExists(Integer.parseInt(numS))) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Numéro de Station en double", "Une Station avec le même numéro de station existe.\nVeuillez utiliser un nouvel numéro");
            return;
        }

    	Station c = new Station(Integer.parseInt(numSt.getText()),nomSt.getText());
        boolean result = dao.create(c);
        if (result) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Nouvelle Station ajoutée", ns + " a été ajouté");
            clearEntries();
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Impossible d'ajouter une nouvelle Station", "Vérifiez toutes les entrées et essayez à nouveau");
        }
    }
    
    private void clearEntries() {
        numSt.clear();
        nomSt.clear();
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
    private void handleEditOperation() {

        Liste_StationControle.Station ln = new Liste_StationControle.Station(Integer.parseInt(numSt.getText()),nomSt.getText());
        if (dao.update(ln)) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Succès", "Mise à jour terminée");
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Échoué", "Impossible de mettre à jour les données");
        }
    }
    
    public void inflateUI(Station l) {
    	numSt.setEditable(false);
        numSt.setText(l.getNumSt().toString());
        nomSt.setText(l.getNomSt());
        isInEditMode = Boolean.TRUE;

    }

}
