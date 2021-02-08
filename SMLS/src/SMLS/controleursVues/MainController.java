package SMLS.controleursVues;

import com.google.common.reflect.AbstractInvocationHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import SMLS.accesBD.AbonneeDAO;
import SMLS.accesBD.LigneDAO;
import SMLS.accesBD.SConnection;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MainController implements Initializable, BookReturnCallback{
	AbonneeDAO dao = new AbonneeDAO();
	private static final String NO_SUCH_BOOK_AVAILABLE = "Aucun Abonnée disponible";
    private PieChart bookChart;
    private PieChart memberChart;
    @FXML
    private StackPane rootPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private JFXTabPane mainTabPane;

    @FXML
    private Tab bookIssueTab;

    @FXML
    private HBox book_info;

    @FXML
    public JFXTextField bookIDInput;

    @FXML
    private StackPane bookInfoContainer;

    @FXML
    private Text bookName;

    @FXML
    private Text bookAuthor;

    @FXML
    private Text bookStatus;

    @FXML
    private Text bookdatenais;
    
    @FXML
    private HBox member_info;

    @FXML
    private JFXTextField memberIDInput;

    @FXML
    private StackPane memberInfoContainer;

    @FXML
    private Text memberName;

    @FXML
    private Text memberMobile;

    @FXML
    private JFXButton btnIssue;

    @FXML
    private Tab renewTab;

    @FXML
    public JFXTextField bookID;

    @FXML
    private JFXButton renewButton;

    @FXML
    private JFXButton submissionButton;

    @FXML
    private HBox submissionDataContainer;

    @FXML
    private Text memberNameHolder;

    @FXML
    private Text memberEmailHolder;

    @FXML
    private Text memberContactHolder;

    @FXML
    private Text bookNameHolder;

    @FXML
    private Text bookAuthorHolder;

    @FXML
    private Text bookPublisherHolder;

    @FXML
    private Text issueDateHolder;

    @FXML
    private Text numberDaysHolder;

    @FXML
    private Text fineInfoHolder;

    @FXML
    private JFXHamburger hamburger;

    public String getbookIDInput(){
        return bookIDInput.getText();
    }
    
    @FXML
    void handleIssueButtonKeyPress(KeyEvent event) {

    }

    @FXML
    void handleMenuAddArret(ActionEvent event) {
      	 LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/add_Arret.fxml"), "Ajouter Nouvau Arrêt", null);

    }

    @FXML
    void handleMenuAddLige(ActionEvent event) {
        LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/add_Ligne.fxml"), "Ajouter Nouvelle Ligne", null);

    }

    @FXML
    void handleMenuAddStation(ActionEvent event) {
    	 LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/add_Station.fxml"), "Ajouter Nouvelle Station", null);
    }

    
    @FXML
    void handleMenuAddAbonnee(ActionEvent event) {
   	 LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/add_Abonnee.fxml"), "Ajouter Nouveau Abonnée", null);

    }

    @FXML
    void handleMenuViewAbonnee(ActionEvent event) {
      	 LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/Abonnee_list.fxml"), "Afficher la liste des Abonnées", null);

    }
    @FXML
    void handleMenuViewArret(ActionEvent event) {
    	 LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/Arret_list.fxml"), "Afficher la liste des Arréts", null);
    }

    @FXML
    void handleMenuViewLigne(ActionEvent event) {
        LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/Ligne_list.fxml"), "Afficher la liste des Lignes", null);
    }

    @FXML
    void handleMenuViewStation(ActionEvent event) {
    	LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/Station_list.fxml"), "Afficher la liste des Stations", null);
    }


    @FXML
    void handleMenuClose(ActionEvent event) {
        getStage().close();

    }

    @FXML
    void handleMenuFullScreen(ActionEvent event) {
        Stage stage = getStage();
        stage.setFullScreen(!stage.isFullScreen());
    }

    @FXML
    void handleMenuSettings(ActionEvent event) {

    }

    @FXML
    void handleMenuViewBook(ActionEvent event) {

    }

    @FXML
    void handleMenuViewMemberList(ActionEvent event) {

    }
    
    void clearBookCache() {
        bookName.setText("");
        bookAuthor.setText("");
        bookStatus.setText("");
        bookdatenais.setText("");
    }

    void clearMemberCache() {
        memberName.setText("");
        memberMobile.setText("");
    }

    @FXML
    void loadBookInfo(ActionEvent event) throws SQLException {
    	clearBookCache();
        enableDisableGraph(false);

        int id = Integer.parseInt(bookIDInput.getText());
		Connection cnx= SConnection.getInstance();
		PreparedStatement stmt= cnx.prepareStatement("select * from abonnee where numAb=?");
		stmt.setInt(1,id);
		ResultSet rs= stmt.executeQuery();
        Boolean flag = false;
        try {
            if (rs.next()) {
                bookName.setText(rs.getString("nom"));
                bookAuthor.setText(rs.getString("prenom"));
                bookStatus.setText(rs.getString("adrAb"));
                bookdatenais.setText(rs.getDate("dateNais").toString());
                flag = true;
            }

            if (!flag) {
                bookName.setText(NO_SUCH_BOOK_AVAILABLE);
            } else {
                memberIDInput.requestFocus();
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void loadBookInfo2(ActionEvent event) {

    }

    @FXML
    void loadIssueOperation(ActionEvent event) {
     	 LibraryAssistantUtil.loadWindow(getClass().getResource("../vues/add_Abonnement.fxml"), "Ajouter Nouvelle Abonnement", null);

    }

    @FXML
    void loadMemberInfo(ActionEvent event) {

    }

    @FXML
    void loadRenewOp(ActionEvent event) {

    }

    @FXML
    void loadSubmissionOp(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	initDrawer();
        initGraphs();
        initComponents();
    }

    private void initDrawer() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/toolbar.fxml"));
            VBox toolbar = loader.load();
            drawer.setSidePane(toolbar);
            ToolbarController controller = loader.getController();
            controller.setBookReturnCallback(this); 
        } catch (IOException ex) {
        	//System.out.println("ERROR");
            //Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            drawer.toggle();
        });
        drawer.setOnDrawerOpening((event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            drawer.toFront();
        });
        drawer.setOnDrawerClosed((event) -> {
            drawer.toBack();
            task.setRate(task.getRate() * -1);
            task.play();
        });
    }
    
    private void initGraphs() {
    	
        bookChart = new PieChart(dao.getAbonneeGraphStatistics());
        memberChart = new PieChart(dao.getAbonneeGraphStatistics());
        bookInfoContainer.getChildren().add(bookChart);
        memberInfoContainer.getChildren().add(memberChart);

        bookIssueTab.setOnSelectionChanged((Event event) -> {
            clearIssueEntries();
            if (bookIssueTab.isSelected()) {
                refreshGraphs();
            }
        });
    }
	
    private void refreshGraphs() {
        bookChart.setData(dao.getAbonneeGraphStatistics());
        memberChart.setData(dao.getAbonneeGraphStatistics());
    }
    
    private void clearIssueEntries() {
        bookIDInput.clear();
        memberIDInput.clear();
        bookName.setText("");
        bookAuthor.setText("");
        bookStatus.setText("");
        memberMobile.setText("");
        memberName.setText("");
        enableDisableGraph(true);
    }
    
    private void enableDisableGraph(Boolean status) {
        if (status) {
            bookChart.setOpacity(1);
            memberChart.setOpacity(1);
        } else {
            bookChart.setOpacity(0);
            memberChart.setOpacity(0);
        }
    }
    
    @Override
    public void loadBookReturn(String bookID) {
        this.bookID.setText(bookID);
        mainTabPane.getSelectionModel().select(renewTab);
        loadBookInfo2(null);
        getStage().toFront();
        if (drawer.isOpened()) {
            drawer.close();
        }
    }
    
    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }
    
    private void initComponents() {
        mainTabPane.tabMinWidthProperty().bind(rootAnchorPane.widthProperty().divide(mainTabPane.getTabs().size()).subtract(15));
    }

   
    
}
