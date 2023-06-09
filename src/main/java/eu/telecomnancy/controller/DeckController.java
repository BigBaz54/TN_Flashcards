package eu.telecomnancy.controller;

import java.util.ArrayList;
import java.util.Date;

import eu.telecomnancy.CardTag;
import eu.telecomnancy.Tag;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.learning.Learning;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.Media;

public class DeckController {
    private DeckModel deckModel;

    public DeckController(DeckModel deckModel) {
        this.deckModel = deckModel;
    }

    public DeckModel getDeckModel() {
        return deckModel;
    }
    
    public void setName(String name) {
        deckModel.setName(name);
    }

    public String getName() {
        return deckModel.getName();
    }

    public void setDescription(String description) {
        deckModel.setDescription(description);
    }

    public String getDescription() {
        return deckModel.getDescription();
    }

    public CardModel getCard(int i) {
        return deckModel.getCard(i);
    }

    public CardModel getActiveCard() {
        return deckModel.getCard(deckModel.getActiveCard());
    }

    public void addCard(String question, String answer) {
        deckModel.addCard(question, answer);
    }
    public void addCard(String question, String answer,Media media) {
        deckModel.addCard(question,answer, media);
    }
    public void addCard(String question, String answer, Media media, ArrayList<CardTag> tags) {
        deckModel.addCard(question,answer, media,tags);
    }

    public void removeCard(int card) {
        deckModel.removeCard(card);
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

    public void nextCard(DrawCardStrategy drawCardStrategy, Learning learning) {
        deckModel.setActiveCard(drawCardStrategy.nextCard(deckModel, learning));
    }

    public void updateStatCard(boolean goodAnswer, Long timeSpent) {
        CardModel currentCard = deckModel.getCard(deckModel.getActiveCard());
        currentCard.getStatCard().setNbTimesSeen(currentCard.getStatCard().getNbTimesSeen() + 1);
        if (goodAnswer) {
            answeredRight();
        } else {
            answeredWrong();
        }
        currentCard.getStatCard().getTimesSpent().add(timeSpent);
    }

    public void updateStatDeck() {
        deckModel.getStatDeck().setNbTimesOpened(deckModel.getStatDeck().getNbTimesOpened() + 1);
        deckModel.getStatDeck().setLastOpened(new Date());
    }

}
