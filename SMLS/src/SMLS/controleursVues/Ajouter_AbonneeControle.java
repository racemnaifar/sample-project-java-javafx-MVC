package SMLS.controleursVues;

import java.sql.SQLException;
import java.util.ArrayList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import SMLS.accesBD.AbonneeDAO;
import SMLS.controleursVues.Liste_AbonneeControle.Abonnee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class Ajouter_AbonneeControle {
	private AbonneeDAO dao= new AbonneeDAO();
    private Boolean isInEditMode = Boolean.FALSE;

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private JFXTextField numAb;

    @FXML
    private JFXTextField nomAb;

    @FXML
    private JFXTextField preAb;

    @FXML
    private JFXTextField adrAb;

    @FXML
    private JFXDatePicker dnAb;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    void addBook(ActionEvent event) throws SQLException {
    	String numA = numAb.getText();
        String nomA = nomAb.getText();
        String preA = preAb.getText();
        String adrA = adrAb.getText();
        String dnA = dnAb.getAccessibleText();

    	if (numA.isEmpty() || nomA.isEmpty()  || preA.isEmpty()  || adrA.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Données insuffisantes", "Veuillez entrer les données dans tous les champs.");
            return;
        }

        if (isInEditMode) {
            handleEditOperation();
            return;
        }

        if (dao.isLigneExists(Integer.parseInt(numA))) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Numéro d'Abonnée en double", "Un abonnée avec le même numéro d'abonnée existe.\nVeuillez utiliser un nouvel numéro");
            return;
        }

    	Abonnee c = new Abonnee(Integer.parseInt(numAb.getText()),nomAb.getText(), preAb.getText(), adrAb.getText(), java.sql.Date.valueOf(dnAb.getValue()) );
        boolean result = dao.create(c);
        if (result) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Nouveau Abonnée ajoutée", preA +" " + nomA + " a été ajouté");
            clearEntries();
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Impossible d'ajouter un neauvau abonnée", "Vérifiez toutes les entrées et essayez à nouveau");
        }
    }
    
    private void clearEntries() {
        numAb.clear();
        nomAb.clear();
        preAb.clear();
        adrAb.clear();
  
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void handleEditOperation() {

        Abonnee ln = new Abonnee(Integer.parseInt(numAb.getText()),nomAb.getText(), preAb.getText(), adrAb.getText(), java.sql.Date.valueOf(dnAb.getValue()) );
        if (dao.update(ln)) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Succès", "Mise à jour terminée");
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Échoué", "Impossible de mettre à jour les données");
        }
    }
    
    public void inflateUI(Abonnee l) {
    	numAb.setEditable(false);
        numAb.setText(l.getNumAb().toString());
        nomAb.setText(l.getNom());
        preAb.setText(l.getNom());
        adrAb.setText(l.getNom());
        dnAb.setPromptText(l.getDateNais().toString());
        isInEditMode = Boolean.TRUE;
    }
}
