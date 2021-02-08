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


public class Liste_AbonneeControle  implements Initializable{
    ObservableList<Abonnee> list ;
    AbonneeDAO dao = new AbonneeDAO();


    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private TableView<Abonnee> tableView;

    @FXML
    private TableColumn<Abonnee, Integer> numAb;

    @FXML
    private TableColumn<Abonnee, String> nomAb;

    @FXML
    private TableColumn<Abonnee, String> preAb;

    @FXML
    private TableColumn<Abonnee, String> adrAb;

    @FXML
    private TableColumn<Abonnee, Date> dnAb;

    @FXML
    void closeStage(ActionEvent event) {
        getStage().close();

    }

    @FXML
    void exportAsPDF(ActionEvent event) {
        List<List> printData = new ArrayList<>();
        String[] headers = {"Numéro Abonnée", "Nom Abonnée", "Prénom Abonnée", "Adresse Abonnée", "Date Naissance Abonnée"};
        printData.add(Arrays.asList(headers));
        for (Abonnee ligne : list) {
            List<String> row = new ArrayList<>();
            row.add(ligne.getNumAb().toString());
            row.add(ligne.getNom());
            row.add(ligne.getPrenom());
            row.add(ligne.getAdrAb());
            row.add(ligne.getDateNais().toString());
            printData.add(row);
        }
        LibraryAssistantUtil.initPDFExprot(rootPane, contentPane, getStage(), printData);
    }
    

    @FXML
    void handleBookDeleteOption(ActionEvent event) throws SQLException {
        Abonnee selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("Aucun abonnée sélectionnée", "Veuillez sélectionner un abonnée à supprimer.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression de ligne");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer l'abonnée " + selectedForDeletion.getNom() + " " + selectedForDeletion.getPrenom() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Boolean result = dao.delete(selectedForDeletion);
            if (result) {
              
                list.remove(selectedForDeletion);
                
            } else {
                AlertMaker.showSimpleAlert("Échoué", selectedForDeletion.getNom() + " " + selectedForDeletion.getPrenom() + " n'a pas pu être supprimé.");
            }
        } 
        
    }


    
    @FXML
    void handleBookEditOption(ActionEvent event) {
        Abonnee selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            AlertMaker.showErrorMessage("Aucun abonnée sélectionnée", "Veuillez sélectionner un abonnée à modifier.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/add_Abonnee.fxml"));
            Parent parent = loader.load();

            Ajouter_AbonneeControle controller = (Ajouter_AbonneeControle) loader.getController();
            controller.inflateUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("modifier un abonnée");
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
		numAb.setCellValueFactory(new PropertyValueFactory<>("numAb"));
		nomAb.setCellValueFactory(new PropertyValueFactory<>("nom"));
		preAb.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		adrAb.setCellValueFactory(new PropertyValueFactory<>("adrAb"));
		dnAb.setCellValueFactory(new PropertyValueFactory<>("dateNais"));
    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }


    private void loadData() {
        list.clear();
       list =  FXCollections.observableArrayList(dao.findAll());
        tableView.setItems(list);
    }


    public static class Abonnee {
    	
    	private SimpleIntegerProperty numAb;
    	private SimpleStringProperty nom;
    	private SimpleStringProperty prenom;
    	private SimpleStringProperty adrAb;
    	private SimpleObjectProperty<Date> dateNais;
    	
    	
		public Abonnee(Integer numAb, String nom, String prenom , String adrAb , Date dateNais) {
			super();
			this.numAb = new SimpleIntegerProperty(numAb);
			this.nom = new SimpleStringProperty(nom);
			this.prenom = new SimpleStringProperty(prenom);
			this.adrAb = new SimpleStringProperty(adrAb);
			this.dateNais = new SimpleObjectProperty<Date>(dateNais);

		}


		public Integer getNumAb() {
			return numAb.get();
		}


		public String getNom() {
			return nom.get();
		}


		public String getPrenom() {
			return prenom.get();
		}


		public String getAdrAb() {
			return adrAb.get();
		}


		public Date getDateNais() {
			return dateNais.get();
		}

		
				
    }


}

    

  

