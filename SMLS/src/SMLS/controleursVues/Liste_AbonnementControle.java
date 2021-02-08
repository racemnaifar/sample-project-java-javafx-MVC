package SMLS.controleursVues;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.derby.catalog.GetProcedureColumns;

import SMLS.accesBD.AbonneeDAO;
import SMLS.accesBD.AbonnementDAO;
import SMLS.accesBD.LigneDAO;
import SMLS.entites.Ligne;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Liste_AbonnementControle  implements Initializable{
    ObservableList<Abonnement> list ;
    AbonnementDAO dao = new AbonnementDAO();


    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private TableView<Abonnement> tableView;

    @FXML
    private TableColumn<Abonnement, Integer> numAbon;

    @FXML
    private TableColumn<Abonnement, Date> dated;

    @FXML
    private TableColumn<Abonnement, Date> datef;


    @FXML
    void closeStage(ActionEvent event) {
        getStage().close();

    }

    @FXML
    void exportAsPDF(ActionEvent event) {
        List<List> printData = new ArrayList<>();
        String[] headers = {"Num�ro Abonnement", "Date D�but", "Date Fin", "Num�ro Abonn�e"};
        printData.add(Arrays.asList(headers));
        for (Abonnement ligne : list) {
            List<String> row = new ArrayList<>();
            row.add(ligne.getNumAbon().toString());
            row.add(ligne.getDateDeb().toString());
            row.add(ligne.getDateFin().toString());
            row.add(ligne.getNumAb().toString());
            printData.add(row);
        }
        LibraryAssistantUtil.initPDFExprot(rootPane, contentPane, getStage(), printData);
    }
    

    @FXML
    void handleBookDeleteOption(ActionEvent event) throws SQLException {
        Abonnement selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("Aucune abonnement s�lectionn�e", "Veuillez s�lectionner une abonnement � supprimer.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression d'Abonnement");
        alert.setContentText("�tes-vous s�r de vouloir supprimer l'abonnement " + selectedForDeletion.getNumAbon() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Boolean result = dao.delete(selectedForDeletion);
            if (result) {
              
                list.remove(selectedForDeletion);
                
            } else {
                AlertMaker.showSimpleAlert("�chou�", selectedForDeletion.getNumAbon()  + " n'a pas pu �tre supprim�.");
            }
        } 
        
    }


    
    @FXML
    void handleBookEditOption(ActionEvent event) {
        Abonnement selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            AlertMaker.showErrorMessage("Aucune abonnement s�lectionn�e", "Veuillez s�lectionner une abonnement � modifier.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/add_Abonnement.fxml"));
            Parent parent = loader.load();

            Ajouter_AbonnementControle controller = (Ajouter_AbonnementControle) loader.getController();
            controller.inflateUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("modifier un abonn�e");
            stage.setScene(new Scene(parent));
            stage.show();
            LibraryAssistantUtil.setStageIcon(stage);

            stage.setOnHiding((e) -> {
                handleRefresh(new ActionEvent());
            });

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleRefresh(ActionEvent event) {
    	 loadData();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // initCol();
        //loadData();
		list= FXCollections.observableArrayList(dao.findAll());
		System.out.println(list);
		tableView.setItems(list);
		numAbon.setCellValueFactory(new PropertyValueFactory<>("numAbon"));
		dated.setCellValueFactory(new PropertyValueFactory<>("dateDeb"));
		datef.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
		
    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }


    private void loadData() {
        list.clear();
       list =  FXCollections.observableArrayList(dao.findAll());
        tableView.setItems(list);
    }


    public static class Abonnement {
    	
    	private SimpleIntegerProperty numAbon;
    	private SimpleObjectProperty<Date> dateDeb;
    	private SimpleObjectProperty<Date> dateFin;
    	private SimpleIntegerProperty numAb;
    	
    	
		public Abonnement(Integer numAbon, Date dateDeb, Date dateFin , Integer numAb) {
			super();
			this.numAbon = new SimpleIntegerProperty(numAbon);
			this.dateDeb = new SimpleObjectProperty<Date>(dateDeb);
			this.dateFin = new SimpleObjectProperty<Date>(dateFin);
			this.numAb = new SimpleIntegerProperty(numAb);			
		}


		public Integer getNumAbon() {
			return numAbon.get();
		}


		public Date getDateDeb() {
			return dateDeb.get();
		}


		public Date getDateFin() {
			return dateFin.get();
		}


		public Integer getNumAb() {
			return numAb.get();
		}
				
    }


}

    

  

