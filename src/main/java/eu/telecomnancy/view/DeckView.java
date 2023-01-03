package eu.telecomnancy.view;

import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.observer.DeckObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class DeckView extends DeckObserver{

    @FXML
    private VBox sidebar;
    @FXML
    private ListView<CardModel> cardListView;


    public DeckView(DeckModel deck) {
        super(deck);
    }

    @Override
    public void react() {
        
    }

    // Méthodes du Top Menu

    @FXML
    public void addCard(){}
    @FXML
    public void removeCard(){}
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
        
    }
    @FXML
    public void toStatsView(){
        
    }
    @FXML
    public void toSettingsView(){
        
    }



    
}
