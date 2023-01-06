package eu.telecomnancy.learning;

import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;

import java.util.ArrayList;
import java.util.HashMap;

public class LearningSession extends Learning {
    public HashMap<Integer, Integer> cardsToLearn = new HashMap<>();


    public LearningSession(DeckController deckController, DrawCardStrategy drawCardStrategy) {
        this.deckController = deckController;
        this.drawCardStrategy = drawCardStrategy;
        this.deckController.updateStatDeck();
        deckController.getDeckModel().getCards().forEach(card->{cardsToLearn.put(card.getIdCard(), 0);});
        this.beginLastCard = System.currentTimeMillis();

    }

    @Override
    public boolean isFinished() {
        boolean isFinished = true;
        for (Integer i : this.cardsToLearn.values()) {
            if (i < 2) {
                isFinished = false;
            }
        }
        return isFinished;
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

    public ArrayList<Integer> getToLearn() {
        ArrayList<Integer> toLearn = new ArrayList<>();
        for (Integer i : this.cardsToLearn.keySet()) {
            if (this.cardsToLearn.get(i) < 2) {
                toLearn.add(i);
            }
        }
        System.out.println(toLearn);
        return toLearn;
    }
}
