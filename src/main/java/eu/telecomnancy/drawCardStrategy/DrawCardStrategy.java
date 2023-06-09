package eu.telecomnancy.drawCardStrategy;

import eu.telecomnancy.learning.Learning;
import eu.telecomnancy.model.DeckModel;

public interface DrawCardStrategy {
    public void handleAnswer(boolean answer, DeckModel deckModel);
    public int nextCard(DeckModel deckModel, Learning learning);
}
