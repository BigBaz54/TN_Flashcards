package eu.telecomnancy.view;

import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.model.DeckListModel;
import eu.telecomnancy.observer.DeckListObserver;
import eu.telecomnancy.observer.DeckObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PopUpCardView extends DeckObserver {

    @FXML
    private TextField questionEdit;
    @FXML
    private TextField answerEdit;

    private DeckController deckController;


    public PopUpCardView(DeckModel deckModel,DeckController deckController) {
        super(deckModel);
        this.deckController = deckController;
    }


    @FXML
    public void createCard(ActionEvent event){
        String question = questionEdit.getText();
        String answer = answerEdit.getText();
        deckController.addCard(question, answer);
        Stage stage = (Stage) questionEdit.getScene().getWindow();
        stage.close();
    }

    
    public void react() {
        
    }

    
}
