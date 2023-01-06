package eu.telecomnancy.controller;

import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.Media;

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

    public String getQuestion() {
        return cardModel.getQuestion();
    }

    public String getAnswer() {
        return cardModel.getAnswer();
    }

    public Media getMedia() {
        return cardModel.getMedia();
    }

    public void setMedia(Media media) {
        cardModel.setMedia(media);
    }
}
