package SMLS.controleursVues;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import SMLS.accesBD.LigneDAO;
import SMLS.entites.Ligne;
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


public class Liste_LigneControle  implements Initializable{
    ObservableList<Ligne> list ;
    LigneDAO dao = new LigneDAO();


    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private TableView<Ligne> tableView;

    @FXML
    private TableColumn<Ligne, Integer> numLig;

    @FXML
    private TableColumn<Ligne, String> nomt;

    @FXML
    void closeStage(ActionEvent event) {
        getStage().close();

    }

    @FXML
    void exportAsPDF(ActionEvent event) {
        List<List> printData = new ArrayList<>();
        String[] headers = {"   Numéro De Ligne   ", "Terminus"};
        printData.add(Arrays.asList(headers));
        for (Ligne ligne : list) {
            List<String> row = new ArrayList<>();
            row.add(ligne.getNumLig().toString());
            row.add(ligne.getNomter());

            printData.add(row);
        }
        LibraryAssistantUtil.initPDFExprot(rootPane, contentPane, getStage(), printData);
    }
    

    @FXML
    void handleBookDeleteOption(ActionEvent event) throws SQLException {
        Ligne selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("Aucune ligne sélectionnée", "Veuillez sélectionner une ligne à supprimer.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression de ligne");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer la ligne " + selectedForDeletion.getNomter() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Boolean result = dao.delete(selectedForDeletion);
            if (result) {
              
                list.remove(selectedForDeletion);
                
            } else {
                AlertMaker.showSimpleAlert("Échoué", selectedForDeletion.getNomter() + " n'a pas pu être supprimé.");
            }
        } 
        
    }


    
    @FXML
    void handleBookEditOption(ActionEvent event) {
        Ligne selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            AlertMaker.showErrorMessage("Aucune ligne sélectionnée", "Veuillez sélectionner une ligne à modifier.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/add_Ligne.fxml"));
            Parent parent = loader.load();

            Ajouter_LigneControle controller = (Ajouter_LigneControle) loader.getController();
            controller.inflateUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("modifier la ligne");
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
		numLig.setCellValueFactory(new PropertyValueFactory<>("numLig"));
		nomt.setCellValueFactory(new PropertyValueFactory<>("nomter"));
    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }

    private void initCol() {
    	numLig.setCellValueFactory(new PropertyValueFactory<>("numLig"));
		nomt.setCellValueFactory(new PropertyValueFactory<>("nomter"));
    }

    private void loadData() {
        list.clear();
       list =  FXCollections.observableArrayList(dao.findAll());
        tableView.setItems(list);
    }


    public static class Ligne {
    	
    	private SimpleIntegerProperty numLig;
    	private SimpleStringProperty nomter;
    	
		public Ligne(Integer numLig, String nomter) {
			super();
			this.numLig = new SimpleIntegerProperty(numLig);
			this.nomter = new SimpleStringProperty(nomter);
		}

		public Integer getNumLig() {
			return numLig.get();
		}

		public String getNomter() {
			return nomter.get();
		}
				
    }


}

    

  

