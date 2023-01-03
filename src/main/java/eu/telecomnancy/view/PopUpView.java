package eu.telecomnancy.view;

import eu.telecomnancy.model.Deck;
import eu.telecomnancy.model.DeckList;
import eu.telecomnancy.observer.DeckListObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PopUpView extends DeckListObserver{

    @FXML
    private TextField deckName;
    @FXML
    private TextArea deckDescription;


    public PopUpView(DeckList deckList) {
        super(deckList);
    }


    @FXML
    public void createDeck(ActionEvent event){
        String name = deckName.getText();
        String description = deckDescription.getText();
        Deck newDeck = new Deck(name, description);
        deckList.addDeck(newDeck);
        Stage stage = (Stage) deckName.getScene().getWindow();
        stage.close();
    }

    @Override
    public void react() {
        
    }

    
}
