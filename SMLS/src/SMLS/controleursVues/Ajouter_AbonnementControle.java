package SMLS.controleursVues;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import SMLS.accesBD.AbonneeDAO;
import SMLS.accesBD.AbonnementDAO;
import SMLS.controleursVues.Liste_AbonneeControle.Abonnee;
import SMLS.controleursVues.Liste_AbonnementControle.Abonnement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class Ajouter_AbonnementControle {
	
	private AbonnementDAO dao= new AbonnementDAO();
    private Boolean isInEditMode = Boolean.FALSE;

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private JFXTextField numAbon;

    @FXML
    private JFXDatePicker dfin;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;
    @FXML
    void addBook(ActionEvent event) throws SQLException, IOException {
    	String numAbn = numAbon.getText();
        String df = dfin.getAccessibleText();

    	if (numAbn.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Donn�es insuffisantes", "Veuillez entrer les donn�es dans tous les champs.");
            return;
        }

        if (isInEditMode) {
            handleEditOperation();
            return;
        }

        if (dao.isLigneExists(Integer.parseInt(numAbn))) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Num�ro d'Abonnement en double", "Une abonnement avec le m�me num�ro d'abonnement existe.\nVeuillez utiliser un nouvel num�ro");
            return;
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		MainController mc = new MainController();
    	Abonnement c = new Abonnement(Integer.parseInt(numAbon.getText()),java.sql.Date.valueOf(format.format(date)),java.sql.Date.valueOf(dfin.getValue()), Integer.parseInt(mc.getbookIDInput()));
        boolean result = dao.create(c);
        if (result) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Nouveau Abonnement ajout�e", numAbn + " a �t� ajout�");
            clearEntries();
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Impossible d'ajouter une nouvelle  abonnement", "V�rifiez toutes les entr�es et essayez � nouveau");
        }
    }
    
    private void clearEntries() {
        numAbon.clear();
        dfin.setValue(null);
  
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void handleEditOperation() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		MainController mc = new MainController();
    	Abonnement ln = new Abonnement(Integer.parseInt(numAbon.getText()),java.sql.Date.valueOf(format.format(date)),java.sql.Date.valueOf(dfin.getValue()), Integer.parseInt(mc.bookID.getText()));
        if (dao.update(ln)) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Succ�s", "Mise � jour termin�e");
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "�chou�", "Impossible de mettre � jour les donn�es");
        }
    }
    
    public void inflateUI(Abonnement l) {
    	numAbon.setEditable(false);
        numAbon.setText(l.getNumAbon().toString());  	
        dfin.setPromptText(l.getDateFin().toString());
        isInEditMode = Boolean.TRUE;
    }
    


}
