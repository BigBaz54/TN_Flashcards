package eu.telecomnancy.view;

import java.io.IOException;

import eu.telecomnancy.controller.CardController;
import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.observer.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class CardView {

    private CardModel card;
    private DeckModel deck;

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
    @FXML
    private Button delete;

    private Mode mode;
    private DeckController deckController;
    private CardController cardController;
 
    public CardView(CardModel card, DeckModel deck, DeckController deckController, Mode mode) {
        this.card = card;
        this.deck = deck;
        this.deckController = deckController;
        this.cardController = new CardController(card);
        this.mode = mode;

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

        // Changement de vue en fonction du mode
        if (mode == Mode.VIEW){
            setNodeVisibility(false,delete,answerEdit,questionEdit);
            setNodeVisibility(true,answer,question);
        }else{
            setNodeVisibility(false,answer,question);
            setNodeVisibility(true,delete,answerEdit,questionEdit);
        }

        // Listeners
        questionEdit.textProperty().addListener((observable, oldValue, newValue) -> {
            cardController.setQuestion(newValue);
        });
        answerEdit.textProperty().addListener((observable, oldValue, newValue) -> {
            cardController.setAnswer(newValue);
        });


    }

    @FXML
    public void removeCard(){
        deckController.removeCard(deck.getCards().indexOf(card));
    }




    public void setNodeVisibility(boolean visible, Node... node){
        for (Node n : node) {
            n.setVisible(visible);
            n.setManaged(visible);
        }
    }
    
 
}
 


