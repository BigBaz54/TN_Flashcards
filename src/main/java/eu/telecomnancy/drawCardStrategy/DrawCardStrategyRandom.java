package eu.telecomnancy.drawCardStrategy;

import eu.telecomnancy.model.DeckModel;

public class DrawCardStrategyRandom implements DrawCardStrategy {
    public void handleAnswer(boolean answer, DeckModel deckModel) {
        // Nothing to do
    }

    public int nextCard(DeckModel deckModel) {
        return (int) (Math.random() * deckModel.getCards().size());
    }
}
