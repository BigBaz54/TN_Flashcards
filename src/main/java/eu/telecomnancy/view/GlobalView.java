package eu.telecomnancy.view;

import eu.telecomnancy.model.Deck;
import eu.telecomnancy.model.DeckList;
import eu.telecomnancy.observer.DeckListObserver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;


public class GlobalView extends DeckListObserver implements Initializable{

    @FXML
    private ListView<Deck> deckListView;


    public GlobalView(DeckList deckList) {
        super(deckList);
    }

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        deckListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        deckListView.getItems().addAll(deckList.getDecks());
        
    }

    @FXML
    public void addDeck() {}
    @FXML
    public void removeDeck(){}


    @Override
    public void react() {
        
    }

    
}
