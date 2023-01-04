package eu.telecomnancy.view;

import java.io.IOException;

import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.observer.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class CardView implements Observer {

    private CardModel card;

    @FXML
    public BorderPane root;

    @FXML
    private Label question;
    @FXML
    private Label answer;
    @FXML
    private TextField questionEdit;
    @FXML
    private TextField answerEdit;
 
    public CardView(CardModel card, DeckController deckController, StageController stageController) {
        this.card = card;
        card.addObserver(this);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CardCell.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(root);
 
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
 
        question.setText(card.getQuestion());
        answer.setText(card.getAnswer());
        questionEdit.setText(card.getQuestion());
        answerEdit.setText(card.getAnswer());
    }

    @Override
    public void react() {
        question.setText(card.getQuestion());
        answer.setText(card.getAnswer());
        questionEdit.setText(card.getQuestion());
        answerEdit.setText(card.getAnswer());
    
        
    }
 
}
 


