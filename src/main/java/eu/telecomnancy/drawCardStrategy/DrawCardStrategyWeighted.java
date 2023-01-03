package eu.telecomnancy.drawCardStrategy;

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
            activeCard.setProbability(activeCard.getProbability()+(1-activeCard.getProbability()/2));
        }
    }

    public int nextCard(DeckModel deckModel) {
        // exemple
        int i;
        while (true) {
            i = (int) (Math.random() * deckModel.getCards().size());
            if ((float) Math.random() < deckModel.getCard(i).getProbability()) {
                return i;
            }
        }
    }
}
