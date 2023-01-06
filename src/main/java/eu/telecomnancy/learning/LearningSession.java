package eu.telecomnancy.learning;

import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.model.CardModel;
import javafx.scene.layout.BorderPane;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class LearningSession extends Learning {
    private HashMap<Integer, Integer> cardsToLearn;


    public LearningSession(DeckController deckController, DrawCardStrategy drawCardStrategy) {
        this.deckController = deckController;
        this.drawCardStrategy = drawCardStrategy;
        this.deckController.updateStatDeck();
        deckController.getDeckModel().getCards().forEach(card->{cardsToLearn.put(card.getIdCard(), 0);});

    }
    @Override
    public void nextCard(boolean goodAnswer) {
        Long timeSpent = System.currentTimeMillis() - beginLastCard;
        updateConcreteLearning(goodAnswer);
        updateStatLearning(goodAnswer, timeSpent);
        deckController.updateStatCard(goodAnswer, timeSpent);
        deckController.handleAnswer(goodAnswer, drawCardStrategy);
        boolean badCard = true;
        while (badCard) {
            deckController.nextCard(drawCardStrategy);
            if (cardsToLearn.get(deckController.getDeckModel().getActiveCard()) < 2) {
                badCard = false;
            }
        }
        beginLastCard = System.currentTimeMillis();

    }

    @Override
    public boolean isFinished() {
        AtomicBoolean isFinished = new AtomicBoolean(true);
        cardsToLearn.values().forEach(value->{
            if(value!=2){
                isFinished.set(false);
            }
            });
        return isFinished.get();
    }

    @Override
    public void updateConcreteLearning(boolean goodAnswer) {
        int index = deckController.getDeckModel().getActiveCard();
        if(goodAnswer){
            cardsToLearn.put(index, cardsToLearn.get(index)+1);
        }
        else{
            cardsToLearn.put(index, 0);
        }
    }
}
