package eu.telecomnancy.view;

import java.net.URL;
import java.util.ResourceBundle;

import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.observer.DeckObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;

public class DeckView extends DeckObserver implements Initializable{

    @FXML
    private VBox sidebar;
    @FXML
    private Label pageName;

    private DeckController deckController;
    private StageController stageController;

    @FXML
    private ListView<CardModel> cardListView;


    public DeckView(DeckModel deckModel, DeckController deckController, StageController stageController) {
        super(deckModel);
        this.deckController = deckController;
        this.stageController = stageController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pageName.setText(deckModel.getName());
        cardListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        cardListView.setCellFactory(param -> new CardCell(deckController,stageController));
        cardListView.getItems().addAll(deckModel.getCards());
        
    } 


    public void react() {
        
    }

    // Méthodes du Top Menu

    @FXML
    public void addCard(){}

    @FXML
    public void removeCard(){
    }

    @FXML
    public void editName(){}

    @FXML
    public void editDescription(){}


    // Méthodes du sidebar Menu
    @FXML
    public void seeMenu() {
        sidebar.setVisible(!sidebar.isVisible());
    }
    
    @FXML
    public void toGlobalView(){
        System.out.println("toGlobalView");
        stageController.setGlobalView();
    }

    @FXML
    public void toStatsView(){}

    @FXML
    public void toSettingsView(){}


     
}
