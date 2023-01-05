package eu.telecomnancy.view;

import eu.telecomnancy.GenerateQuestion;
import eu.telecomnancy.GenerateResponse;
import eu.telecomnancy.model.CardModel;
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

import java.io.IOException;
import java.util.ArrayList;

public class PopUpCardView extends DeckObserver {

    @FXML
    private TextField questionEdit;
    @FXML
    private TextField answerEdit;
    @FXML
    private TextField nbCardEdit;

    private DeckController deckController;


    public PopUpCardView(DeckModel deckModel,DeckController deckController) {
        super(deckModel);
        this.deckController = deckController;
    }
    @FXML
    public void generateAnswer(ActionEvent actionEvent) throws IOException {
        String question = questionEdit.getText();
        GenerateResponse generateResponse = new GenerateResponse();
        String response = generateResponse.getResponse(question);
        answerEdit.setText(response);
    }
    @FXML
    public void generateCard(ActionEvent actionEvent) throws IOException {
        String nbCard = nbCardEdit.getText();
        int nbCardInt = Integer.parseInt(nbCard);
        GenerateQuestion generateQuestion = new GenerateQuestion();
        ArrayList<CardModel> newCards= generateQuestion.generateQuestion(deckModel,nbCardInt);
        for (CardModel card : newCards){
            deckController.addCard(card.getQuestion(),card.getAnswer());
        }
        Stage stage = (Stage) questionEdit.getScene().getWindow();
        stage.close();
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
