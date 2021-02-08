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
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Donn�es insuffisantes", "Veuillez entrer les donn�es dans tous les champs.");
            return;
        }

        if (isInEditMode) {
            handleEditOperation();
            return;
        }

        if (dao.isLigneExists(Integer.parseInt(numA))) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Num�ro d'Abonn�e en double", "Un abonn�e avec le m�me num�ro d'abonn�e existe.\nVeuillez utiliser un nouvel num�ro");
            return;
        }

    	Abonnee c = new Abonnee(Integer.parseInt(numAb.getText()),nomAb.getText(), preAb.getText(), adrAb.getText(), java.sql.Date.valueOf(dnAb.getValue()) );
        boolean result = dao.create(c);
        if (result) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Nouveau Abonn�e ajout�e", preA +" " + nomA + " a �t� ajout�");
            clearEntries();
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Impossible d'ajouter un neauvau abonn�e", "V�rifiez toutes les entr�es et essayez � nouveau");
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
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Succ�s", "Mise � jour termin�e");
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "�chou�", "Impossible de mettre � jour les donn�es");
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
