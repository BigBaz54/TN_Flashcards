package eu.telecomnancy.view;

import eu.telecomnancy.controller.DeckListController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.DeckListModel;
import eu.telecomnancy.observer.DeckListObserver;
import eu.telecomnancy.view.DeckCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GlobalView extends DeckListObserver implements Initializable{

    @FXML
    private ListView<DeckModel> deckListView;

    private StageController stageController;
    private DeckListController deckListController;

    @FXML
    private VBox sidebar;


    public GlobalView(DeckListModel deckListView, DeckListController deckListController, StageController stageController) {
        super(deckListView);
        this.deckListController = deckListController;
        this.stageController = stageController;
    }

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        deckListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        deckListView.setCellFactory(param -> new DeckCell());
        deckListView.getItems().addAll(deckListModel.getDecks());
        
    }

    // Top Menu

    @FXML
    public void createDeck() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
        loader.setControllerFactory(ic -> new PopUpView(deckListModel));
        try {
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void removeDeck(){}

    @FXML
    public void exportDeck(){}

    @FXML
    public void importDeck(){}
    
    public void toDeckView(){
        stageController.setDeckView();
    }
    
    @Override
    public void react() {
        deckListView.getItems().clear();
        deckListView.getItems().addAll(deckListModel.getDecks());
        
    }

    // Sidebar Menu
    @FXML
    public void seeMenu() {
        sidebar.setVisible(!sidebar.isVisible());
    }
    @FXML
    public void toGlobalView(){
        
    }
    @FXML
    public void toStatsView(){
        
    }
    @FXML
    public void toSettingsView(){
        
    }

    public void setNodeVisibility(boolean visible, Node... node){
        for (Node n : node) {
            n.setVisible(visible);
            n.setManaged(visible);
        }
    }

    
}
