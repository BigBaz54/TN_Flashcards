package eu.telecomnancy.controller;

import eu.telecomnancy.model.CardModel;

public class CardController {
    private CardModel cardModel;

    public CardController(CardModel cardModel) {
        this.cardModel = cardModel;
    }
    
    public void setQuestion(String question) {
        cardModel.setQuestion(question);
    }

    public void setAnswer(String answer) {
        cardModel.setAnswer(answer);
    }
}
