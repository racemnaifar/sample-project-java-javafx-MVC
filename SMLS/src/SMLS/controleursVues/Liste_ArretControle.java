package SMLS.controleursVues;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.logging.Level;
import java.util.logging.Logger;

import SMLS.accesBD.ArretDAO;
import SMLS.accesBD.LigneDAO;
import SMLS.accesBD.StationDAO;
import SMLS.controleursVues.Liste_LigneControle.Ligne;
import javafx.beans.property.SimpleIntegerProperty;
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

public class Liste_ArretControle implements Initializable{
	public ArretDAO dao= new ArretDAO();
	public LigneDAO daol= new LigneDAO();
	public StationDAO daos= new StationDAO();
    ObservableList<Arret> list ;
    

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private TableView<Arret> tableView;

    @FXML
    private TableColumn<String, Arret> numAr;

    @FXML
    private TableColumn<Integer, Arret> nomL;

    @FXML
    private TableColumn<Integer, Arret> numZ;

    @FXML
    private TableColumn<Integer, Arret> nomSt;

    @FXML
    void closeStage(ActionEvent event) {
        getStage().close();

    }

    @FXML
    void exportAsPDF(ActionEvent event) {
    	 List<List> printData = new ArrayList<>();
         String[] headers = {"   Numéro d'Arrêt  ", "Nom de Ligne" , "Numéro de Zone" , "Nom de Station"};
         printData.add(Arrays.asList(headers));
         for (Arret ligne : list) {
             List<String> row = new ArrayList<>();
             row.add(ligne.getNumAr());
             row.add(ligne.getNumLig().toString());
             row.add(ligne.getNumZone().toString());
            row.add(ligne.getNumSt().toString());



             printData.add(row);
         }
         LibraryAssistantUtil.initPDFExprot(rootPane, contentPane, getStage(), printData);
    }

    @FXML
    void handleBookDeleteOption(ActionEvent event) throws SQLException {
        Arret selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("Aucun Arrét sélectionnée", "Veuillez sélectionner un arrét à supprimer.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression de l'Arrét");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer l'Arrét " + selectedForDeletion.getNumAr() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Boolean result = dao.delete(selectedForDeletion);
            if (result) {
              
                list.remove(selectedForDeletion);
                
            } else {
                AlertMaker.showSimpleAlert("Échoué", selectedForDeletion.getNumAr() + " n'a pas pu être supprimé.");
            }
        } 
    }

    @FXML
    void handleBookEditOption(ActionEvent event) {
    	 Arret selectedForEdit = tableView.getSelectionModel().getSelectedItem();
         if (selectedForEdit == null) {
             AlertMaker.showErrorMessage("Aucun Arrét sélectionnée", "Veuillez sélectionner un arrét à modifier.");
             return;
         }
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/add_Arret.fxml"));
             Parent parent = loader.load();

             Ajouter_ArretControle controller = (Ajouter_ArretControle) loader.getController();
             controller.inflateUI(selectedForEdit);

             Stage stage = new Stage(StageStyle.DECORATED);
             stage.setTitle("modifier l'Arrét");
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		list= FXCollections.observableArrayList(dao.findAll());
		System.out.println(list);
		tableView.setItems(list);
		numAr.setCellValueFactory(new PropertyValueFactory<>("numAr"));
		nomL.setCellValueFactory(new PropertyValueFactory<>("numLig"));
		numZ.setCellValueFactory(new PropertyValueFactory<>("numZone"));
		nomSt.setCellValueFactory(new PropertyValueFactory<>("numSt"));
	}
	
    private void loadData() {
        list.clear();
       list =  FXCollections.observableArrayList(dao.findAll());
        tableView.setItems(list);
    }
	
    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }
	
	public static class Arret {
		
		private SimpleStringProperty numAr;
		private SimpleIntegerProperty numZone;
		private SimpleIntegerProperty numLig;
		private SimpleIntegerProperty numSt;

		
		public Arret(String numAr, Integer numZone, Integer numLig,
				Integer numSt) {
			super();
			this.numAr =  new SimpleStringProperty(numAr);
			this.numZone =  new SimpleIntegerProperty(numZone);
			this.numLig =  new SimpleIntegerProperty(numLig);
			this.numSt =  new SimpleIntegerProperty(numSt);
		}
		

		public Arret() {
			// TODO Auto-generated constructor stub
		}

		public String getNumAr() {
			return numAr.get();
		}

		public Integer getNumZone() {
			return numZone.get();
		}

		public Integer getNumLig() {
			return numLig.get();
		}

		public Integer getNumSt() {
			return numSt.get();
		}
		
		
		
		
	}

}
