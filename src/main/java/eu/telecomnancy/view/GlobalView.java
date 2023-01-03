package eu.telecomnancy.view;

import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.Deck;
import eu.telecomnancy.model.DeckList;
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
    private ListView<Deck> deckListView;
    @FXML
    private VBox sidebar;

    private StageController stageController;


    public GlobalView(DeckList deckList,StageController stageController) {
        super(deckList);
        this.stageController = stageController;
    }

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        deckListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        deckListView.setCellFactory(param -> new DeckCell());
        deckListView.getItems().addAll(deckList.getDecks());
        
    }

    @FXML
    public void createDeck() {
        Stage stage = new Stage();
        System.out.println(getClass().getResource("PopUp.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
        loader.setControllerFactory(ic -> new PopUpView(deckList));
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
    
    public void toDeckView(){}

    @FXML
    public void seeMenu() {
        sidebar.setVisible(!sidebar.isVisible());
    }
    
    @Override
    public void react() {
        deckListView.getItems().clear();
        deckListView.getItems().addAll(deckList.getDecks());
        
    }

    public void setNodeVisibility(boolean visible, Node... node){
        for (Node n : node) {
            n.setVisible(visible);
            n.setManaged(visible);
        }
    }

    
}
