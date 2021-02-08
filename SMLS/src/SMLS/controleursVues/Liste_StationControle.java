package SMLS.controleursVues;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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

public class Liste_StationControle implements Initializable {
    ObservableList<Station> list ;
    StationDAO dao = new StationDAO();

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private TableView<Station> tableView;

    @FXML
    private TableColumn<Station, Integer> numSt;

    @FXML
    private TableColumn<Station, String> nomSt;

    @FXML
    void closeStage(ActionEvent event) {
        getStage().close();

    }

    @FXML
    void exportAsPDF(ActionEvent event) {
        List<List> printData = new ArrayList<>();
        String[] headers = {"   Numéro De Station   ", "Nom De Station"};
        printData.add(Arrays.asList(headers));
        for (Station st : list) {
            List<String> row = new ArrayList<>();
            row.add(st.getNumSt().toString());
            row.add(st.getNomSt());

            printData.add(row);
        }
        LibraryAssistantUtil.initPDFExprot(rootPane, contentPane, getStage(), printData);
    }

    @FXML
    void handleBookDeleteOption(ActionEvent event) throws SQLException {
        Station selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("Aucune station sélectionnée", "Veuillez sélectionner une station à supprimer.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression de station");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer la station " + selectedForDeletion.getNomSt() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Boolean result = dao.delete(selectedForDeletion);
            if (result) {
              
                list.remove(selectedForDeletion);
                
            } else {
                AlertMaker.showSimpleAlert("Échoué", selectedForDeletion.getNomSt() + " n'a pas pu être supprimé.");
            }
        } 
    }

    @FXML
    void handleBookEditOption(ActionEvent event) {
        Station selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            AlertMaker.showErrorMessage("Aucune station sélectionnée", "Veuillez sélectionner une station à modifier.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/add_Station.fxml"));
            Parent parent = loader.load();

            Ajouter_StationControle controller = (Ajouter_StationControle) loader.getController();
            controller.inflateUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("modifier la Station");
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
		list= FXCollections.observableArrayList(dao.findAll());
		System.out.println(list);
		tableView.setItems(list);
		numSt.setCellValueFactory(new PropertyValueFactory<>("numSt"));
		nomSt.setCellValueFactory(new PropertyValueFactory<>("nomSt"));
    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }

    private void initCol() {
		numSt.setCellValueFactory(new PropertyValueFactory<>("numSt"));
		nomSt.setCellValueFactory(new PropertyValueFactory<>("nomSt"));
    }

    private void loadData() {
        list.clear();
       list =  FXCollections.observableArrayList(dao.findAll());
        tableView.setItems(list);
    }
    
    public static class Station {
    	private SimpleIntegerProperty numSt;
    	private SimpleStringProperty nomSt;
    	
    	public Station(Integer numSt, String nomSt) {
    		super();
    		this.numSt = new SimpleIntegerProperty(numSt);
    		this.nomSt = new SimpleStringProperty(nomSt);
    	}

		public Integer getNumSt() {
			return numSt.get();
		}

		public String getNomSt() {
			return nomSt.get();
		}
    	
    	
    }
}
