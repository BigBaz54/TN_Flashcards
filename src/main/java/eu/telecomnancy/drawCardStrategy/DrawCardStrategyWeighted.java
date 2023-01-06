package eu.telecomnancy.drawCardStrategy;

import eu.telecomnancy.learning.Learning;
import eu.telecomnancy.learning.LearningSession;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;

public class DrawCardStrategyWeighted implements DrawCardStrategy {
    public void handleAnswer(boolean answer, DeckModel deckModel) {
        CardModel activeCard = deckModel.getCards().get(deckModel.getActiveCard());
        if (answer) {
            // exemple
            activeCard.setProbability(activeCard.getProbability()/2);
        } else {
            // exemple
            activeCard.setProbability(activeCard.getProbability()+((1-activeCard.getProbability())/2));
        }
        boolean up = true;
        for (int i = 0; i < deckModel.getCards().size(); i++) {
            if (deckModel.getCard(i).getProbability() > 0.5) {
                up = false;
            }
        }
        if (up) {
            for (int i = 0; i < deckModel.getCards().size(); i++) {
                deckModel.getCard(i).setProbability(deckModel.getCard(i).getProbability()*2);
            }
        }
    }

    public int nextCard(DeckModel deckModel, Learning learning) {
        // exemple
        int i;
        if (learning instanceof LearningSession) {
            while (true) {
                i = (int) (Math.random() * ((LearningSession) learning).getToLearn().size());
                if ((float) Math.random() < deckModel.getCard(i).getProbability()) {
                    return i;
                }
            }
        }
        while (true) {
            i = (int) (Math.random() * deckModel.getCards().size());
            if ((float) Math.random() < deckModel.getCard(i).getProbability()) {
                return i;
            }
        }
    }
}
