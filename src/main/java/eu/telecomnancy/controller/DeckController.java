package eu.telecomnancy.controller;

import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;

public class DeckController {
    private DeckModel deckModel;

    public DeckController(DeckModel deckModel) {
        this.deckModel = deckModel;
    }

    public void setName(String name) {
        deckModel.setName(name);
    }

    public void setDescription(String description) {
        deckModel.setDescription(description);
    }

    public void addCard(String question, String answer) {
        deckModel.addCard(question, answer);
    }

    public void removeCard(int i) {
        deckModel.removeCard(i);
    }

    public void answeredRight() {
        CardModel currentCard = deckModel.getCard(deckModel.getActiveCard());
        currentCard.getStatCard().setNbTimesCorrect(currentCard.getStatCard().getNbTimesCorrect() + 1);
    }

    public void answeredWrong() {
        CardModel currentCard = deckModel.getCard(deckModel.getActiveCard());
        currentCard.getStatCard().setNbTimesWrong(currentCard.getStatCard().getNbTimesWrong() + 1);
    }

    public void handleAnswer(boolean goodAnswer, DrawCardStrategy drawCardStrategy) {
        drawCardStrategy.handleAnswer(goodAnswer, deckModel);
    }

    public void nextCard(DrawCardStrategy drawCardStrategy) {
        deckModel.setActiveCard(drawCardStrategy.nextCard(deckModel));
    }
}
