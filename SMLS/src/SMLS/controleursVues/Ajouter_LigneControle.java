package SMLS.controleursVues;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import SMLS.accesBD.LigneDAO;
import SMLS.controleursVues.Liste_LigneControle.Ligne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class Ajouter_LigneControle {
	private LigneDAO dao= new LigneDAO();
    private Boolean isInEditMode = Boolean.FALSE;

	@FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private JFXTextField numLig;

    @FXML
    private JFXTextField nomt;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    void addBook(ActionEvent event) throws SQLException {

        String numL = numLig.getText();
        String nt = nomt.getText();

    	if (numL.isEmpty() || nt.isEmpty() ) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Données insuffisantes", "Veuillez entrer les données dans tous les champs.");
            return;
        }

        if (isInEditMode) {
            handleEditOperation();
            return;
        }

        if (dao.isLigneExists(Integer.parseInt(numL))) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Numéro de ligne en double", "Une ligne avec le même numéro de ligne existe.\nVeuillez utiliser un nouvel numéro");
            return;
        }

    	Ligne c = new Ligne(Integer.parseInt(numLig.getText()),nomt.getText());
        boolean result = dao.create(c);
        if (result) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Nouvelle ligne ajoutée", nt + " a été ajouté");
            clearEntries();
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Impossible d'ajouter une nouvelle ligne", "Vérifiez toutes les entrées et essayez à nouveau");
        }
    }
    
    private void clearEntries() {
        numLig.clear();
        nomt.clear();
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void handleEditOperation() {

        Liste_LigneControle.Ligne ln = new Liste_LigneControle.Ligne(Integer.parseInt(numLig.getText()),nomt.getText());
        if (dao.update(ln)) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Succès", "Mise à jour terminée");
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Échoué", "Impossible de mettre à jour les données");
        }
    }
    
    public void inflateUI(Ligne l) {
    	numLig.setEditable(false);
        numLig.setText(l.getNumLig().toString());
        nomt.setText(l.getNomter());
        isInEditMode = Boolean.TRUE;
    }
}
